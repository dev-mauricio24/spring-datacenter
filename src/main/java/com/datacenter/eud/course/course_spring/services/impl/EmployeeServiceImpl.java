package com.datacenter.eud.course.course_spring.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.datacenter.eud.course.course_spring.dto.EmployeeDTO;
import com.datacenter.eud.course.course_spring.dto.PageParamRequestDTO;
import com.datacenter.eud.course.course_spring.dto.PageResponseDTO;
import com.datacenter.eud.course.course_spring.persistence.entities.EmployeeEntity;
import com.datacenter.eud.course.course_spring.persistence.repositories.EmployeeRepository;
import com.datacenter.eud.course.course_spring.services.EmployeeService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	@NonNull
	private EmployeeRepository employeeRepository;

	@Override
	public void execute() {
//		Pageable pageable = PageRequest.of(0, 20);

		Pageable pageable = PageRequest.of(0, 20, Sort.by(Order.asc("lastName")));
		Page<EmployeeEntity> page = this.employeeRepository.findAll(pageable);

		System.out.println("Total elementos " + page.getTotalElements());
		System.out.println("Size " + page.getSize());
		System.out.println("Número total de paginas " + page.getTotalPages());

		page.get().forEach(e -> {
			System.out.println(e.getFirstName() + " " + e.getLastName());
		});

		/*
		 * Iterable<EmployeeEntity> list =
		 * this.repository.findAll(Sort.by(Order.asc("firstName")));
		 * Iterable<EmployeeEntity> list =
		 * this.repository.findAll(Sort.by(Order.asc("firstName"),
		 * Order.desc("lastName"))); Iterable<EmployeeEntity> list =
		 * this.repository.findAll(Sort.by("firstName asc", "lastName desc"));
		 * Iterable<EmployeeEntity> list =
		 * this.repository.findAll(Sort.by(Direction.ASC, "firstName"));
		 * 
		 * 
		 * for(EmployeeEntity e : list) { System.out.println(e.getFirstName() + " " +
		 * e.getLastName()); }
		 */

	}

	@Override
	public void executeQuery() {
		// TODO Auto-generated method stub

	}

	@Override
	public PageResponseDTO<EmployeeDTO> getAll(PageParamRequestDTO page) {
		
		Page<EmployeeEntity> entities = this.employeeRepository.findAll(
			PageRequest.of(page.getSize(), page.getPageSize(), Sort.by(Order.asc("firstName"))));
		
//		PageResponseDTO<EmployeeDTO> response = new PageResponseDTO<EmployeeDTO>();
//		
//		response.setCurrentPage(page.getSize());
//		response.setCurrentRows(entities.getNumberOfElements());
//		response.setTotalPages(entities.getTotalPages());
//		response.setTotalRows(entities.getTotalElements());
//		response.setData(entities.getContent().stream()
//				.map(e -> castToEmployeeDTO(e)).toList());
//		
//		return response;
		
		PageResponseDTO<EmployeeDTO> response = PageResponseDTO.<EmployeeDTO>builder()
				.currentPage(page.getSize())
				.currentRows(entities.getNumberOfElements())
				.totalPages(entities.getTotalPages())
				.totalRows(entities.getTotalElements())
				.data(entities.getContent().stream()
				.map(e -> castToEmployeeDTO(e)).toList())
				.build();
		
		return response;

	}

	private EmployeeDTO castToEmployeeDTO(EmployeeEntity e) {
		EmployeeDTO dto = EmployeeDTO.builder().departmentId(e.getDepartmentId())
				.salary(e.getSalary()).jobId(e.getJobId()).firstName(e.getFirstName())
				.lastName(e.getLastName()).build();
		
		return dto;
	}

}
