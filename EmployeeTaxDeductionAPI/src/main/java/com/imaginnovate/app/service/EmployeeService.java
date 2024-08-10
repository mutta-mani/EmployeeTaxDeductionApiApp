package com.imaginnovate.app.service;

import java.util.List;

import com.imaginnovate.app.entity.Employee;
import com.imaginnovate.app.entity.EmployeeResponseDTO;
import com.imaginnovate.app.exception.EmployeeNotFoundException;


public interface EmployeeService {
	
	//save operation
	Employee saveEmployee(Employee employee);
	
	
	//fetch Operation

	EmployeeResponseDTO fetchEmployeewithTaxDetails(int empId) throws EmployeeNotFoundException;
	
	

}
