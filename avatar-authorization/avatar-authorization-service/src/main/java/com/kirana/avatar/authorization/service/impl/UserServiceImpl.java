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

import com.kirana.avatar.authorization.dto.AssetDTO;
import com.kirana.avatar.authorization.dto.RoleDTO;
import com.kirana.avatar.authorization.dto.UserDTO;
import com.kirana.avatar.authorization.mapper.AssetMapper;
import com.kirana.avatar.authorization.mapper.UserMapper;
import com.kirana.avatar.authorization.model.Asset;
import com.kirana.avatar.authorization.model.AssetType;
import com.kirana.avatar.authorization.model.Role;
import com.kirana.avatar.authorization.model.User;
import com.kirana.avatar.authorization.model.UserAsset;
import com.kirana.avatar.authorization.model.UserRole;
import com.kirana.avatar.authorization.repositories.AssetRepository;
import com.kirana.avatar.authorization.repositories.AssetTypeRepository;
import com.kirana.avatar.authorization.repositories.GenderRepository;
import com.kirana.avatar.authorization.repositories.LanguageRepository;
import com.kirana.avatar.authorization.repositories.RoleRepository;
import com.kirana.avatar.authorization.repositories.UserAssetRepository;
import com.kirana.avatar.authorization.repositories.UserRepository;
import com.kirana.avatar.authorization.repositories.UserRoleRepository;
import com.kirana.avatar.authorization.repositories.VillageRepository;
import com.kirana.avatar.authorization.service.UserService;
import com.kirana.avatar.authorization.specifications.UserSpecification;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.dto.UserInfo;
import com.kirana.avatar.common.exception.ApiException;
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
	private LanguageRepository languageRepository;
	private VillageRepository villageRepository;
	private GenderRepository genderRepository;
	private RoleRepository roleRepository;
	private UserRoleRepository userRoleRepository;
	private AssetRepository assetRepository;
	private UserAssetRepository userAssetRepository;
	private AssetTypeRepository assetTypeRepository;
	private UserMapper userMapper;
	private AssetMapper assetMapper;
	private UserSpecification userSpecification;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private JwtConfig jwtConfig;
	
	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserSpecification userSpecification,
			LanguageRepository languageRepository, VillageRepository villageRepository, GenderRepository genderRepository,
			RoleRepository roleRepository, UserRoleRepository userRoleRepository,
			AssetRepository assetRepository, UserAssetRepository userAssetRepository, AssetMapper assetMapper,
			AssetTypeRepository assetTypeRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder, JwtConfig jwtConfig) {
		super(userRepository, userMapper, userSpecification);
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.userSpecification = userSpecification;
		this.languageRepository = languageRepository;
		this.villageRepository = villageRepository;
		this.genderRepository = genderRepository;
		this.roleRepository = roleRepository;
		this.userRoleRepository = userRoleRepository;
		this.assetRepository = assetRepository;
		this.userAssetRepository = userAssetRepository;
		this.assetTypeRepository = assetTypeRepository;
		this.assetMapper = assetMapper;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.jwtConfig = jwtConfig;
	}

	@Override
	protected User beforeSave(User model) {
		String encryptedPassword = bCryptPasswordEncoder.encode(jwtConfig.getDefaultPassword());
		model.setPassword(encryptedPassword);
		model.setSuspended(false);
		model.setRoles(null);
		model.setAssets(null);
		return model;
	}

	@Override
	protected User beforeUpdate(UserDTO userDTO, final User model) {
		model.setRoles(null);
		model.setAssets(null);
		languageRepository
				.findById(userDTO.getPreferredLanguage().getId())
				.map(preferredLanguage -> {
					model.setPreferredLanguage(preferredLanguage);
					return model;
				})
				.orElseThrow(ApiException::resourceNotFound);
		genderRepository
				.findById(userDTO.getGender().getId())
				.map(gender -> {
					model.setGender(gender);
					return model;
				})
				.orElseThrow(ApiException::resourceNotFound);
		return villageRepository
				.findById(userDTO.getVillage().getId())
				.map(village -> {
					model.setVillage(village);
					return model;
				})
				.orElseThrow(ApiException::resourceNotFound);
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
			for (AssetDTO assetDTO : userDTO.getAssets()) {
				Asset asset = assetMapper.toModel(assetDTO);
				AssetType assetType = assetTypeRepository.getOne(asset.getAssetType().getId());
				asset.setAssetType(assetType);
				asset = assetRepository.save(asset);
				UserAsset userAsset = new UserAsset();
				userAsset.setUser(model);
				userAsset.setAsset(asset);
				userAssetRepository.save(userAsset);
			}
		}
		return userRepository.findById(model.getId()).get();
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
			for (AssetDTO assetDTO : userDTO.getAssets()) {
				Asset asset = null;
				if (null != assetDTO.getId()) {
					asset = assetRepository.getOne(assetDTO.getId());
				} else {
					asset = assetMapper.toModel(assetDTO);
				}
				AssetType assetType = assetTypeRepository.getOne(asset.getAssetType().getId());
				asset.setAssetType(assetType);
				asset = assetRepository.save(asset);
				UserAsset userAsset = new UserAsset();
				userAsset.setUser(model);
				userAsset.setAsset(asset);
				userAssetRepository.save(userAsset);
			}
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
		return userMapper.toDTO(user);
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
		return UserInfo.create()
				.password(user.getPassword())
				.username(user.getUserName())
				.mobileNumber(user.getMobileNumber())
				.authorities(grantedAuthorities)
				.build();
		
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
		Map<String, Long> formerResult = new LinkedHashMap<String, Long>();
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
		Map<String, Long> formerResult = new LinkedHashMap<String, Long>();
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
		Map<String, Long> formerResult = new LinkedHashMap<String, Long>();
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
}
