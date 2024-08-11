package com.imaginnovate.app.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imaginnovate.app.entity.Employee;
import com.imaginnovate.app.entity.EmployeeResponseDTO;
import com.imaginnovate.app.exception.EmployeeNotFoundException;
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

	@Override 
	public EmployeeResponseDTO fetchEmployeewithTaxDetails(int empId) throws EmployeeNotFoundException{
		// TODO Auto-generated method stub
		Employee emp = repo.findByEmployeeId(empId);
		if(Objects.nonNull(emp)) {
		
		
			EmployeeResponseDTO empwithtaxdetails = new EmployeeResponseDTO();
			Map<String, Double> map= calculateIncometaxforEmp(emp);
			double finalTax= map.get("FinalTax");
			double totalIncome = map.get("TotalIncome");
			double cessamount = calculateCESSamoutforEmp(finalTax, totalIncome);
			empwithtaxdetails.setEmployee(emp);
			empwithtaxdetails.setTotalSalary(totalIncome);
			empwithtaxdetails.setTaxamount(finalTax);
			empwithtaxdetails.setCessamount(cessamount);
			return empwithtaxdetails;
		}
		else {
			throw new EmployeeNotFoundException("Employee not there for given Id " +empId);
		}
		
	}
	
	public Map<String, Double> calculateIncometaxforEmp(Employee emp){
		Map<String, Double> map = new HashMap<String, Double>();
	    double finaltax; 

	    LocalDate dateOfjoin = LocalDate.parse(emp.getDoj());
	    LocalDate finYrEndDate  = LocalDate.parse("2025-03-31");
	    long diffInDays = ChronoUnit.DAYS.between(dateOfjoin, finYrEndDate);


	    double totalincome = (double) (Math.round(diffInDays*emp.getSalary()/30));
	    if(totalincome<=250000)
			finaltax=0;
		else if(totalincome<=500000)
			finaltax= (totalincome-250000)*0.05;
		else if(totalincome <= 1000000)
			finaltax= (totalincome-500000)*0.1 + 12500;
		else
			finaltax=(totalincome-1000000)*0.2 +37500;
	    
	    map.put("TotalIncome", totalincome);
	    map.put("FinalTax", (double) Math.round(finaltax));
		return map;
		
	}
	
	public double calculateCESSamoutforEmp(double taxincome, double totalincome) {
		
		double totalcessamt;
		
		if(totalincome <= 2500000) {
			totalcessamt = taxincome*0.04;
		}
		else {
			totalcessamt = taxincome*0.04 + (totalincome-2500000)*0.02;

			
		}
		return Math.round(totalcessamt);
		
	}

}
