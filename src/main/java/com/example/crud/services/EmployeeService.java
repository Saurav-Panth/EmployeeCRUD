package com.example.crud.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.crud.entity.Employee;

public interface EmployeeService {
	
	List<Employee> allEmployees();
	
	void addEmployee(Employee employee);
	
	Employee findUsingId(long id);
	
	void deleteEmployee(Long id);
	
	Page<Employee> findPaginated(int pageNumber,int pageSize);
}
