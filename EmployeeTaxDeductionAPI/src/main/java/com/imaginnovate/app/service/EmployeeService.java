package com.imaginnovate.app.service;

import java.util.List;

import com.imaginnovate.app.entity.Employee;
import com.imaginnovate.app.entity.EmployeeDTO;


public interface EmployeeService {
	
	//save operation
	Employee saveEmployee(Employee employee);
	
	
	//fetch Operation
	List<EmployeeDTO> fetchEmployeeswithTaxDetails();
	
	

}
