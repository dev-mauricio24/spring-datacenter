package com.datacenter.eud.course.course_spring.persistence.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.eud.course.course_spring.persistence.entities.EmployeeEntity;

@Repository
public interface EmployeeQueriesReository
		extends CrudRepository<EmployeeEntity, Long>, PagingAndSortingRepository<EmployeeEntity, Long> {
	
	// Empleados que pertenecen al departamento 50 o 60
	List<EmployeeEntity> findByDepartmentIdIn(List<Long> departmentIds);
	// Empleados cuyo nombre tiene 3 letras
	Page<EmployeeEntity> findByFirstNameLikeOrderByFirstNameAscLastNameAsc(String like, Pageable pageable);
	// Empleados por año específico de ingreso
	List<EmployeeEntity> findByHireDateBetweenOrderByHireDateDesc(Date startDate, Date endDate);
	// Empleados que pertenecen al departamento 50 y su cargo es SH_CLERK
	Page<EmployeeEntity> findByDepartmentIdAndJobIdOrderBySalaryAsc(Long departmentId, String jobId, Pageable pageable);
	
	List<EmployeeEntity> findByFirstNameStartingWithAndLastNameEndingWithOrderByHireDateDesc(String sbFirstName, String sbLastName);
	
	List<EmployeeEntity> findBySalaryGreaterThanAndCommissionPctGreaterThanOrCommissionPctIsNull(double salary, double commissionPct);
	
	List<EmployeeEntity> findByFirstNameAndLastName(String firstName, String lastName);
	Page<EmployeeEntity> findByManagerIdOrderByFirstNameAscLastNameAsc(Long managerId, Pageable pageable);
	
	List<EmployeeEntity> findByFirstNameLikeOrderByDepartmentIdAsc(String letter);
	
	Page<EmployeeEntity> findByPhoneNumberStartingWithOrderByFirstNameAscLastNameAscDepartmentIdDesc(String prefix, Pageable pageable);
}
