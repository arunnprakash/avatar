/*******************************************************************************
 *
 * Copyright (c) 2019 GranaTech Limited
 *
 * All information contained herein is, and remains the property of GranaTech
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to GranaTech and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from GranaTech
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.authorization.service.impl;


import static com.kirana.avatar.authorization.model.User_.ID;
import static com.kirana.avatar.authorization.model.User_.FIRST_NAME;
import static com.kirana.avatar.authorization.model.User_.LAST_NAME;
import static com.kirana.avatar.authorization.model.User_.MOBILE_NUMBER;
import static com.kirana.avatar.authorization.model.User_.SUSPENDED;
import static com.kirana.avatar.authorization.model.User_.USER_NAME;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kirana.avatar.master.dto.AssetDTO;
import com.kirana.avatar.master.dto.GenderDTO;
import com.kirana.avatar.master.dto.LanguageDTO;
import com.kirana.avatar.master.dto.VillageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirana.avatar.authorization.dto.RoleDTO;
import com.kirana.avatar.authorization.dto.UserDTO;
import com.kirana.avatar.master.dto.WareHouseDTO;
import com.kirana.avatar.master.feign.AssetClient;
import com.kirana.avatar.master.feign.AssetTypeClient;
import com.kirana.avatar.master.feign.GenderClient;
import com.kirana.avatar.master.feign.LanguageClient;
import com.kirana.avatar.master.feign.VillageClient;
import com.kirana.avatar.master.feign.WareHouseClient;
import com.kirana.avatar.authorization.mapper.UserMapper;
import com.kirana.avatar.authorization.model.BuyerAgentMapping;
import com.kirana.avatar.authorization.model.Role;
import com.kirana.avatar.authorization.model.SellerAgentMapping;
import com.kirana.avatar.authorization.model.SellerAgentTruckDriverMapping;
import com.kirana.avatar.authorization.model.TruckDriverWareHouseMapping;
import com.kirana.avatar.authorization.model.User;
import com.kirana.avatar.authorization.model.UserAsset;
import com.kirana.avatar.authorization.model.UserRole;
import com.kirana.avatar.authorization.repositories.BuyerAgentMappingRepository;
import com.kirana.avatar.authorization.repositories.SellerAgentMappingRepository;
import com.kirana.avatar.authorization.repositories.SellerAgentTruckDriverMappingRepository;
import com.kirana.avatar.authorization.repositories.TruckDriverWareHouseMappingRepository;
import com.kirana.avatar.authorization.repositories.RoleRepository;
import com.kirana.avatar.authorization.repositories.UserAssetRepository;
import com.kirana.avatar.authorization.repositories.UserRepository;
import com.kirana.avatar.authorization.repositories.UserRoleRepository;
import com.kirana.avatar.authorization.service.UserService;
import com.kirana.avatar.authorization.specifications.UserSpecification;
import com.kirana.avatar.authorization.specifications.SellerAgentMappingSpecification;
import com.kirana.avatar.authorization.specifications.BuyerAgentMappingSpecification;
import com.kirana.avatar.authorization.specifications.SellerAgentTruckDriverMappingSpecification;
import com.kirana.avatar.authorization.specifications.TruckDriverWareHouseMappingSpecification;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.dto.UserInfo;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.jwt.TokenProvider;
import com.kirana.avatar.common.jwt.config.JwtConfig;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;

import lombok.extern.slf4j.Slf4j;
/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, UserDTO, UserMapper, UserRepository, UserSpecification> implements UserService, UserDetailsService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private UserRoleRepository userRoleRepository;
	private AssetClient assetClient;
	private UserAssetRepository userAssetRepository;
	private AssetTypeClient assetTypeClient;
	private WareHouseClient wareHouseClient;
	private BuyerAgentMappingRepository buyerAgentMappingRepository;
	private SellerAgentMappingRepository sellerAgentMappingRepository;
	private SellerAgentMappingSpecification sellerAgentMappingSpecification;
	private BuyerAgentMappingSpecification buyerAgentMappingSpecification;
	private SellerAgentTruckDriverMappingRepository sellerAgentTruckDriverMappingRepository;
	private SellerAgentTruckDriverMappingSpecification sellerAgentTruckDriverMappingSpecification;
	private TruckDriverWareHouseMappingRepository truckDriverWareHouseMappingRepository;
	private TruckDriverWareHouseMappingSpecification truckDriverWareHouseMappingSpecification;
	private UserMapper userMapper;
	private UserSpecification userSpecification;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private JwtConfig jwtConfig;
	private ObjectMapper objectMapper;
	private LanguageClient languageClient;
	private VillageClient villageClient;
	private GenderClient genderClient;
	private TokenProvider tokenProvider;
	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserSpecification userSpecification,
			LanguageClient languageClient, VillageClient villageClient, GenderClient genderClient,
			RoleRepository roleRepository, UserRoleRepository userRoleRepository,
			AssetClient assetClient, UserAssetRepository userAssetRepository,
			AssetTypeClient assetTypeClient, 
			WareHouseClient wareHouseClient, 
			SellerAgentMappingRepository sellerAgentMappingRepository, SellerAgentMappingSpecification sellerAgentMappingSpecification,
			SellerAgentTruckDriverMappingRepository sellerAgentTruckDriverMappingRepository, SellerAgentTruckDriverMappingSpecification sellerAgentTruckDriverMappingSpecification, 
			BuyerAgentMappingRepository buyerAgentMappingRepository, BuyerAgentMappingSpecification buyerAgentMappingSpecification,
			TruckDriverWareHouseMappingRepository truckDriverWareHouseMappingRepository, TruckDriverWareHouseMappingSpecification truckDriverWareHouseMappingSpecification,
			BCryptPasswordEncoder bCryptPasswordEncoder, JwtConfig jwtConfig, ObjectMapper objectMapper,
			TokenProvider tokenProvider) {
		super(userRepository, userMapper, userSpecification);
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.userSpecification = userSpecification;
		this.roleRepository = roleRepository;
		this.userRoleRepository = userRoleRepository;
		this.assetClient = assetClient;
		this.userAssetRepository = userAssetRepository;
		this.assetTypeClient = assetTypeClient;
		this.wareHouseClient = wareHouseClient;
		this.sellerAgentMappingRepository = sellerAgentMappingRepository;
		this.buyerAgentMappingRepository = buyerAgentMappingRepository;
		this.sellerAgentMappingSpecification = sellerAgentMappingSpecification;
		this.buyerAgentMappingSpecification = buyerAgentMappingSpecification;
		this.sellerAgentTruckDriverMappingRepository = sellerAgentTruckDriverMappingRepository;
		this.sellerAgentTruckDriverMappingSpecification = sellerAgentTruckDriverMappingSpecification;
		this.truckDriverWareHouseMappingRepository = truckDriverWareHouseMappingRepository;
		this.truckDriverWareHouseMappingSpecification = truckDriverWareHouseMappingSpecification;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.jwtConfig = jwtConfig;
		this.objectMapper = objectMapper;
		this.languageClient = languageClient;
		this.villageClient = villageClient;
		this.genderClient = genderClient;
		this.tokenProvider = tokenProvider;
	}

	@Override
	protected User beforeSave(UserDTO userDTO, User model) {
		String encryptedPassword = bCryptPasswordEncoder.encode(jwtConfig.getDefaultPassword());
		model.setPassword(encryptedPassword);
		model.setSuspended(false);
		return model;
	}

	@Override
	protected User beforeUpdate(UserDTO userDTO, final User model) {
		model.setPreferredLanguage((Long)userDTO.getPreferredLanguage().get(ID));
		model.setGender((Long)userDTO.getGender().get(ID));
		model.setVillage((Long)userDTO.getVillage().get(ID));
		return model;
	}

	@Override
	protected User afterSave(UserDTO userDTO, User model) {
		if (null != userDTO.getRoles() && !userDTO.getRoles().isEmpty()) {
			for (RoleDTO roleDTO : userDTO.getRoles()) {
				Role role = roleRepository.findById(roleDTO.getId()).get();
				UserRole userRole = new UserRole();
				userRole.setUser(model);
				userRole.setRole(role);
				userRoleRepository.save(userRole);
			}
		}
		if (null != userDTO.getAssets() && !userDTO.getAssets().isEmpty()) {
			//Todo
			/*
			 * for (AssetDTO assetDTO : userDTO.getAssets()) { Asset asset =
			 * assetMapper.toModel(assetDTO); AssetType assetType =
			 * assetTypeRepository.getOne(asset.getAssetType().getId());
			 * asset.setAssetType(assetType); asset = assetRepository.save(asset); UserAsset
			 * userAsset = new UserAsset(); userAsset.setUser(model);
			 * userAsset.setAsset(asset); userAssetRepository.save(userAsset); }
			 */
		}
		if (userHasRole("SELLER_AGENT")) {
			UserInfo currentUser = getCurrentlyLoggedInUser();
			User sellerAgent = userRepository.findByUserNameOrMobileNumber(currentUser.getMobileNumber(), currentUser.getMobileNumber()).get();
			if (hasRole(userDTO.getRoles(), "SELLER")) {
				User seller = userRepository.findById(model.getId()).get();
				SellerAgentMapping sellerAgentMapping = new SellerAgentMapping();
				sellerAgentMapping.setSeller(seller);
				sellerAgentMapping.setSellerAgent(sellerAgent);
				sellerAgentMappingRepository.save(sellerAgentMapping);
			} else if (hasRole(userDTO.getRoles(), "SELLER_TRUCK_DRIVER")) {
				User truckDriver = userRepository.findById(model.getId()).get();
				SellerAgentTruckDriverMapping sellerAgentTruckDriverMapping = new SellerAgentTruckDriverMapping();
				sellerAgentTruckDriverMapping.setSellerAgent(sellerAgent);
				sellerAgentTruckDriverMapping.setTruckDriver(truckDriver);
				sellerAgentTruckDriverMappingRepository.save(sellerAgentTruckDriverMapping);
			}
		}
		if (userHasRole("BUYER_AGENT")) {
			UserInfo currentUser = getCurrentlyLoggedInUser();
			User buyerAgent = userRepository.findByUserNameOrMobileNumber(currentUser.getMobileNumber(), currentUser.getMobileNumber()).get();
			User buyer = userRepository.findById(model.getId()).get();
			BuyerAgentMapping buyerAgentMapping = new BuyerAgentMapping();
			buyerAgentMapping.setBuyer(buyer);
			buyerAgentMapping.setBuyerAgent(buyerAgent);
			buyerAgentMappingRepository.save(buyerAgentMapping);
		}
		return userRepository.findById(model.getId()).get();
	}

	private boolean hasRole(List<RoleDTO> roles, String roleName) {
		return roles.stream().anyMatch((role) -> {
			return role.getRoleName().equalsIgnoreCase(roleName);
		});
	}

	@Override
	protected User afterUpdate(UserDTO userDTO, User model) {
		if (null != userDTO.getRoles() && !userDTO.getRoles().isEmpty()) {
			for (RoleDTO roleDTO : userDTO.getRoles()) {
				Role role = roleRepository.findById(roleDTO.getId()).get();
				Optional<UserRole> userRoleO = userRoleRepository.findByUserAndRole(model, role);
				if (!userRoleO.isPresent()) {
					UserRole userRole = new UserRole();
					userRole.setUser(model);
					userRole.setRole(role);
					userRoleRepository.save(userRole);
				}
			}
		}
		if (null != userDTO.getAssets() && !userDTO.getAssets().isEmpty()) {
			//Todo
			/*
			 * for (AssetDTO assetDTO : userDTO.getAssets()) { Asset asset = null; if (null
			 * != assetDTO.getId()) { asset = assetRepository.getOne(assetDTO.getId()); }
			 * else { asset = assetMapper.toModel(assetDTO); } AssetType assetType =
			 * assetTypeRepository.getOne(asset.getAssetType().getId());
			 * asset.setAssetType(assetType); asset = assetRepository.save(asset); UserAsset
			 * userAsset = new UserAsset(); userAsset.setUser(model);
			 * userAsset.setAsset(asset); userAssetRepository.save(userAsset); }
			 */
		}
		model = userRepository.findById(model.getId()).get();
		return model;
	}

	@Override
	protected Specification<User> getSpecification(FilterCriteria filter, Specification<User> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(USER_NAME)) {
			Specification<User> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? userSpecification.hasUserName(itemValue) : spec.or(userSpecification.hasUserName(itemValue));
			}
			log.debug("Adding specification {} {}", USER_NAME, spec);
			specification = specification.and(spec);
		} else if (itemName.equalsIgnoreCase(FIRST_NAME)) {
			Specification<User> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? userSpecification.hasFirstName(itemValue) : spec.or(userSpecification.hasFirstName(itemValue));
			}
			log.debug("Adding specification {} {}", FIRST_NAME, spec);
			specification = specification.and(spec);
		} else if (itemName.equalsIgnoreCase(LAST_NAME)) {
			Specification<User> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? userSpecification.hasLastName(itemValue) : spec.or(userSpecification.hasLastName(itemValue));
			}
			log.debug("Adding specification {} {}", LAST_NAME, spec);
			specification = specification.and(spec);
		} else if (itemName.equalsIgnoreCase(MOBILE_NUMBER)) {
			Specification<User> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? userSpecification.hasMobileNumber(itemValue) : spec.or(userSpecification.hasMobileNumber(itemValue));
			}
			log.debug("Adding specification {} {}", MOBILE_NUMBER, spec);
			specification = specification.and(spec);
		} else if (itemName.equalsIgnoreCase(SUSPENDED)) {
			Specification<User> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				boolean suspended = Boolean.parseBoolean(itemValue);
				spec = (spec == null) ? userSpecification.hasSuspended(suspended) : spec.or(userSpecification.hasSuspended(suspended));
			}
			log.debug("Adding specification {} {}", SUSPENDED, spec);
			specification = specification.and(spec);
		} else {
			throw ApiException.implementationNotFound();
		}
		return specification;
	}

	@Override
	public UserDTO findByUserNameOrMobileNumber(String userNameOrMobileNumber) {
		User user = userRepository
				.findByUserNameOrMobileNumber(userNameOrMobileNumber, userNameOrMobileNumber)
				.orElseThrow(() -> 
					new UsernameNotFoundException("User not found with username or mobilenumber : " + userNameOrMobileNumber)
				);
		log.debug("User :: {}", user);
		UserDTO userDTO = userMapper.toDTO(user);
		return userDTO.toBuilder()
				.preferredLanguage(getPreferredLanguage(user))
				.village(getVillage(user))
				.gender(getGender(user))
				.build(); 
	}

	@Override
	public UserDetails loadUserByUsername(String userNameOrMobileNumber) throws UsernameNotFoundException {
		User user = userRepository
				.findByUserNameOrMobileNumber(userNameOrMobileNumber, userNameOrMobileNumber)
				.orElseThrow(() -> 
					new UsernameNotFoundException("User not found with username or mobilenumber : " + userNameOrMobileNumber)
				);
		log.debug("User Found with username or mobilenumber {}", userNameOrMobileNumber);
		// Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
		// So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
		String roles = user.getRoles().stream().map(Role::getRoleName).collect(joining(","));
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
		
		// The "UserInfo" class is provided by common and represents a model class for user to be returned by UserDetailsService
		// And used by auth manager to verify and check user authentication.
		UserInfo userinfo = UserInfo.create()
				.password(user.getPassword())
				.username(user.getUserName())
				.mobileNumber(user.getMobileNumber())
				.authorities(grantedAuthorities)
				.build();
		String token = tokenProvider.generateToken(userinfo);
		return UserInfo.create(userinfo).userToken(token).build();
	}

	@Override
	public Map<String, Long> sellersDailyGrowthRate(Integer depth) {
		return getDailyGrowthRate(depth, "SELLER");
	}

	@Override
	public Map<String, Long> sellersMonthlyGrowthRate(Integer depth) {
		return getMonthlyGrowthRate(depth, "SELLER");
	}

	@Override
	public Map<String, Long> sellersYearlyGrowthRate(Integer depth) {
		return getYearlyGrowthRate(depth, "SELLER");
	}

	@Override
	public Map<String, Long> buyersDailyGrowthRate(Integer depth) {
		return getDailyGrowthRate(depth, "BUYER");
	}

	@Override
	public Map<String, Long> buyersMonthlyGrowthRate(Integer depth) {
		return getMonthlyGrowthRate(depth, "BUYER");
	}

	@Override
	public Map<String, Long> buyersYearlyGrowthRate(Integer depth) {
		return getYearlyGrowthRate(depth, "BUYER");
	}

	private Map<String, Long> getYearlyGrowthRate(Integer depth, String roleName) {
		ZonedDateTime todayDate = ZonedDateTime.now();
		ZonedDateTime startDate = stripOutTime(todayDate);
		startDate = startDate.withDayOfYear(1);
		startDate = startDate.minusYears(depth - 1);
		Specification<User> specification = Specification.where(userSpecification.hasDeleted(false));
		specification = specification.and(userSpecification.hasRoleName(roleName));
		specification = specification.and(userSpecification.hasCreatedDateBetween(startDate , todayDate));
		List<User> users = userRepository.findAll(specification);
		Map<String, Long> actualResult = users.stream().collect(groupingBy(
				user -> {return String.valueOf(user.getCreatedDate().getYear());},
				counting()
				));
		Map<String, Long> formerResult = new LinkedHashMap<>();
		for (int i = 0; i < depth; i++) {
			String year = String.valueOf(startDate.getYear());
			if (actualResult.containsKey(year)) {
				formerResult.put(year, actualResult.get(year));
			} else {
				formerResult.put(year, 0L);
			}
			startDate = startDate.plusYears(1);
		}
		return formerResult;
	}

	private Map<String, Long> getMonthlyGrowthRate(Integer depth, String roleName) {
		ZonedDateTime todayDate = ZonedDateTime.now();
		ZonedDateTime startDate = stripOutTime(todayDate);
		startDate = startDate.withDayOfMonth(1);
		startDate = startDate.minusMonths(depth - 1);
		Specification<User> specification = Specification.where(userSpecification.hasDeleted(false));
		specification = specification.and(userSpecification.hasRoleName(roleName));
		specification = specification.and(userSpecification.hasCreatedDateBetween(startDate , todayDate));
		List<User> users = userRepository.findAll(specification);
		Map<String, Long> actualResult = users.stream().collect(groupingBy(
				user -> {return user.getCreatedDate().getMonth().name();},
				counting()
				));
		Map<String, Long> formerResult = new LinkedHashMap<>();
		for (int i = 0; i < depth; i++) {
			String monthName = startDate.getMonth().name();
			if (actualResult.containsKey(monthName)) {
				formerResult.put(monthName, actualResult.get(monthName));
			} else {
				formerResult.put(monthName, 0L);
			}
			startDate = startDate.plusMonths(1);
		}
		return formerResult;
	}

	private Map<String, Long> getDailyGrowthRate(Integer depth, String roleName) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		ZonedDateTime todayDate = ZonedDateTime.now();
		ZonedDateTime startDate = stripOutTime(todayDate);
		startDate = startDate.minusDays(depth - 1);
		Specification<User> specification = Specification.where(userSpecification.hasDeleted(false));
		specification = specification.and(userSpecification.hasRoleName(roleName));
		specification = specification.and(userSpecification.hasCreatedDateBetween(startDate , todayDate));
		List<User> users = userRepository.findAll(specification);
		Map<String, Long> actualResult = users.stream().collect(groupingBy(
				user -> {return formatter.format(user.getCreatedDate());},
				counting()
				));
		Map<String, Long> formerResult = new LinkedHashMap<>();
		for (int i = 0; i < depth; i++) {
			String date = formatter.format(startDate);
			if (actualResult.containsKey(date)) {
				formerResult.put(date, actualResult.get(date));
			} else {
				formerResult.put(date, 0L);
			}
			startDate = startDate.plusDays(1);
		}
		return formerResult;
	}

	private ZonedDateTime stripOutTime(ZonedDateTime date) {
		date = date.minusHours(date.getHour());
		date = date.minusMinutes(date.getMinute());
		date = date.minusSeconds(date.getSecond());
		date = date.minusNanos(date.getNano());
		return date;
	}

	@Override
	public UserDTO getSellerAgentForSeller(Long sellerId) {
		Specification<SellerAgentMapping> specification = Specification.where(sellerAgentMappingSpecification.hasDeleted(false));
		specification = specification.and(sellerAgentMappingSpecification.hasSellerId(sellerId));
		return sellerAgentMappingRepository
			.findOne(specification)
			.map(SellerAgentMapping::getSellerAgent)
			.map(userMapper::toDTO)
			.orElseThrow(ApiException::resourceNotFound);
	}

	@Override
	public UserDTO getBuyerAgentForBuyer(Long buyerId) {
		Specification<BuyerAgentMapping> specification = Specification.where(buyerAgentMappingSpecification.hasDeleted(false));
		specification = specification.and(buyerAgentMappingSpecification.hasBuyerId(buyerId));
		return buyerAgentMappingRepository
			.findOne(specification)
			.map(BuyerAgentMapping::getBuyerAgent)
			.map(userMapper::toDTO)
			.orElseThrow(ApiException::resourceNotFound);
	}

	@Override
	public UserDTO getTruckDriverForSellerAgent(Long sellerAgentId) {
		Specification<SellerAgentTruckDriverMapping> specification = Specification.where(sellerAgentTruckDriverMappingSpecification.hasDeleted(false));
		specification = specification.and(sellerAgentTruckDriverMappingSpecification.hasSellerAgentId(sellerAgentId));
		return sellerAgentTruckDriverMappingRepository
			.findOne(specification)
			.map(SellerAgentTruckDriverMapping::getTruckDriver)
			.map(userMapper::toDTO)
			.orElseThrow(ApiException::resourceNotFound);
	}

	@Override
	public UserDTO getSellerAgentForTruckDriver(Long truckDriverId) {
		Specification<SellerAgentTruckDriverMapping> specification = Specification.where(sellerAgentTruckDriverMappingSpecification.hasDeleted(false));
		specification = specification.and(sellerAgentTruckDriverMappingSpecification.hasTruckDriverId(truckDriverId));
		return sellerAgentTruckDriverMappingRepository
			.findOne(specification)
			.map(SellerAgentTruckDriverMapping::getSellerAgent)
			.map(userMapper::toDTO)
			.orElseThrow(ApiException::resourceNotFound);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getWareHouseForTruckDriver(Long truckDriverId) {
		Specification<TruckDriverWareHouseMapping> specification = Specification.where(truckDriverWareHouseMappingSpecification.hasDeleted(false));
		specification = specification.and(truckDriverWareHouseMappingSpecification.hasTruckDriverId(truckDriverId));
		return truckDriverWareHouseMappingRepository
			.findOne(specification)
			.map(TruckDriverWareHouseMapping::getWareHouse)
			.map(wareHouseClient::get)
			.map(warehouseDTO -> objectMapper.convertValue(warehouseDTO, Map.class))
			.orElseThrow(ApiException::resourceNotFound);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getPreferredLanguage(User user) {
		return userRepository.findById(user.getId())
				.map(User::getPreferredLanguage)
				.map(languageClient::get)
				.map(languageDTO->objectMapper.convertValue(languageDTO, Map.class))
				.orElseThrow(ApiException::resourceNotFound);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getVillage(User user) {
		return userRepository.findById(user.getId())
				.map(User::getVillage)
				.map(villageClient::get)
				.map(villageDTO->objectMapper.convertValue(villageDTO, Map.class))
				.orElseThrow(ApiException::resourceNotFound);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getGender(User user) {
		return userRepository.findById(user.getId())
				.map(User::getGender)
				.map(genderClient::get)
				.map(genderDTO->objectMapper.convertValue(genderDTO, Map.class))
				.orElseThrow(ApiException::resourceNotFound);
	}

	@Override
	protected UserDTO afterLoad(UserDTO resource, User model) {
		return resource;
	}
}
