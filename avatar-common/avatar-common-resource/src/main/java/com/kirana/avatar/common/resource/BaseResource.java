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
package com.kirana.avatar.common.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.leandreck.endpoints.annotations.TypeScriptTemplatesConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.common.dto.BaseDTO;
import com.kirana.avatar.common.dto.PagingAndFilterRequest;
import com.kirana.avatar.common.dto.PagingAndFilterResponse;

/**
 * @author __ArunPrakash__
 *
 */
@TypeScriptTemplatesConfiguration(useSuffixes = false)
@RequestMapping(value= {"/api"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface BaseResource<DTO extends BaseDTO> {

	//@Secured("ROLE_ADMIN")
	@GetMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public Collection<DTO> getAll();

	@GetMapping(value= {"/with-includes-only-non-deleted"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public Collection<DTO> getAllExceptDeleted();

	@GetMapping(value= {"/{id}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public DTO get(@PathVariable("id") Long id);

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public DTO save(@Valid @RequestBody DTO resource);
	
	@DeleteMapping(value= {"/{ids}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public Boolean delete(@PathVariable("ids") List<Long> ids);

	@PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public DTO update(@Valid @RequestBody DTO resource);

	@PostMapping(value= {"/with-filter-and-paging"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public PagingAndFilterResponse<DTO> getResourceByFilterAndPaging(@Valid @RequestBody PagingAndFilterRequest pagingAndFilterRequest);

	@PostMapping(value= {"/with-filter-and-paging/with-includes-only-non-deleted"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public PagingAndFilterResponse<DTO> getResourceByFilterAndPagingExceptDeleted(@Valid @RequestBody PagingAndFilterRequest pagingAndFilterRequest);
}
