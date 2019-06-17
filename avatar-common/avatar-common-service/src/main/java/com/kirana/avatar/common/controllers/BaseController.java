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
package com.kirana.avatar.common.controllers;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import com.kirana.avatar.common.dto.BaseDTO;
import com.kirana.avatar.common.dto.PagingAndFilterRequest;
import com.kirana.avatar.common.dto.PagingAndFilterResponse;
import com.kirana.avatar.common.resource.BaseResource;
import com.kirana.avatar.common.service.BaseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
public class BaseController<Service extends BaseService<DTO>, DTO extends BaseDTO> implements BaseResource<DTO> {
	private Service service;

	public BaseController(Service service) {
		super();
		this.service = service;
	}

	@ApiOperation(value = "Get all the records for this resource. Result includes deleted resources, so need admin role", response = Collection.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully get all the records for this resource"),
			@ApiResponse(code = 500, message = "Error while getting all the records for this resource")
		}
	)
	@Override
	public Collection<DTO> getAll() {
		log.debug("Getting List");
		Collection<DTO> list = service.getAll(true);
		log.debug("Get List Done. List size {}", list.size());
		return list;
	}

	@ApiOperation(value = "Get all the records for this resource except deleted.", response = Collection.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully get all the records for this resource"),
			@ApiResponse(code = 500, message = "Error while getting all the records for this resource")
		}
	)
	@Override
	public Collection<DTO> getAllExceptDeleted() {
		log.debug("Getting Non Deleted List");
		Collection<DTO> list = service.getAll(false);
		log.debug("Get Non Deleted List Done. List size {}", list.size());
		return list;
	}

	@ApiOperation(value = "Get the resource for given resource id", response = BaseDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully get the resource for given resource id"),
			@ApiResponse(code = 500, message = "Error while getting the resource for given resource id")
		}
	)
	@Override
	public DTO get(Long id) {
		log.debug("Getting by id {}", id);
		DTO resource = service.get(id);
		log.debug("Get by id {} done {}", id, resource);
		return resource;
	}

	@ApiOperation(value = "Create a resource for given input", response = BaseDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully Created a resource"),
			@ApiResponse(code = 500, message = "Error while creating resource for given input")
		}
	)
	@Override
	public DTO save(@Valid DTO resource) {
		log.debug("Saving {}", resource);
		resource = service.save(resource);
		log.debug("Saved {}", resource);
		return resource;
	}

	@ApiOperation(value = "Delete a resource for given resource ids", response = Boolean.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully deleted a resource for given resource id"),
			@ApiResponse(code = 500, message = "Error while deleting a resource for given resource id")
		}
	)
	@Override
	public Boolean delete(List<Long> ids) {
		log.debug("Deleting {}", ids);
		boolean status = service.delete(ids);
		log.debug("Deleted {} status {}", ids, status);
		return status;
	}

	@ApiOperation(value = "Update the given resource", response = BaseDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully updated the given resource"),
			@ApiResponse(code = 500, message = "Error while updating the given resource")
		}
	)
	@Override
	public DTO update(@Valid DTO resource) {
		log.debug("Updating {}", resource);
		resource = service.update(resource);
		log.debug("Updated {}", resource);
		return resource;
	}

	@ApiOperation(value = "Get the resources for given filter and paging criteria. Result includes deleted resources, so need admin role", 
			response = PagingAndFilterResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully get the resources for given filter and paging criteria"),
			@ApiResponse(code = 500, message = "Error while getting the resources for given filter and paging criteria")
		}
	)
	@Override
	public PagingAndFilterResponse<DTO> getResourceByFilterAndPaging(
			@Valid PagingAndFilterRequest pagingAndFilterRequest) {
		log.debug("Getting Resource By Filter And Paging {}", pagingAndFilterRequest);
		PagingAndFilterResponse<DTO> responseDTO = service.getResourceByFilterAndPaging(pagingAndFilterRequest, true);
		log.debug("Get Resource By Filter And Paging {} done with result {}", pagingAndFilterRequest, responseDTO);
		return responseDTO;
	}

	@ApiOperation(value = "Get the resources for given filter and paging criteria. Result does includes deleted resources", 
			response = PagingAndFilterResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully get the resources for given filter and paging criteria"),
			@ApiResponse(code = 500, message = "Error while getting the resources for given filter and paging criteria")
		}
	)
	@Override
	public PagingAndFilterResponse<DTO> getResourceByFilterAndPagingExceptDeleted(
			@Valid PagingAndFilterRequest pagingAndFilterRequest) {
		log.debug("Getting Non Deleted Resource By Filter And Paging {}", pagingAndFilterRequest);
		PagingAndFilterResponse<DTO> responseDTO = service.getResourceByFilterAndPaging(pagingAndFilterRequest, false);
		log.debug("Get Non Deleted Resource By Filter And Paging {} done with result {}", pagingAndFilterRequest, responseDTO);
		return responseDTO;
	}
}
