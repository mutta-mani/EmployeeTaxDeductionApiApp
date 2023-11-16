package com.imaginnovate.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.app.entity.Employee;
import com.imaginnovate.app.service.EmployeeService;

@RestController
public class EmployeeController {
	
	//Save operation
	
	@Autowired
	EmployeeService employeeService;
	
	
    @PostMapping("/saveemp")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee emp) {
    	return new ResponseEntity<>(employeeService.saveEmployee(emp), HttpStatus.CREATED);
    }
}
