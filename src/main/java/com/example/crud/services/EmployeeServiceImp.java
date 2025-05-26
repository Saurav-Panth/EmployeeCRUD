package com.example.crud.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.crud.entity.Employee;
import com.example.crud.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImp implements EmployeeService{
	
	private EmployeeRepository employeeRepo;
	public EmployeeServiceImp(EmployeeRepository employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	@Override
	public List<Employee> allEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeRepo.save(employee);
		
	}

	@Override
	public Employee findUsingId(long id) {
		return employeeRepo.findAllById(id);
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepo.deleteById(id);
		
	}

	@Override
	public Page<Employee> findPaginated(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
		return employeeRepo.findAll(pageable);
	}
	
}
