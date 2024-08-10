package com.imaginnovate.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.app.entity.Employee;
import com.imaginnovate.app.entity.EmployeeResponseDTO;
import com.imaginnovate.app.exception.EmployeeNotFoundException;
import com.imaginnovate.app.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	
	@Autowired
	EmployeeService employeeService;
	
	// Saving employee
    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee emp) {
    	return new ResponseEntity<>(employeeService.saveEmployee(emp), HttpStatus.OK);
    }
    
    //Fetching emp with taxdetails
    @GetMapping("/employees/{employeeId}/tax-deductions")
    public ResponseEntity<EmployeeResponseDTO> fetchEmployees(@PathVariable Integer employeeId) throws EmployeeNotFoundException{
   EmployeeResponseDTO employeeTaxdtails =employeeService.fetchEmployeewithTaxDetails(employeeId);
		return new ResponseEntity<>(employeeTaxdtails, HttpStatus.OK);
    }
 }
