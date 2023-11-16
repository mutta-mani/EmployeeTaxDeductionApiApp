package com.imaginnovate.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imaginnovate.app.entity.Employee;
import com.imaginnovate.app.repository.EmployeeRepository;

@Service
public class Employeeserviceimpl implements EmployeeService {

	@Autowired
	EmployeeRepository repo;
	
	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
		return repo.save(employee);
	}

}
