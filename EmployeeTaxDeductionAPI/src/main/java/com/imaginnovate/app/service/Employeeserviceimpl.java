package com.imaginnovate.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imaginnovate.app.entity.Employee;
import com.imaginnovate.app.entity.EmployeeDTO;
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
	public List<EmployeeDTO> fetchEmployeeswithTaxDetails() {
		// TODO Auto-generated method stub
		List<EmployeeDTO> allEmployees = new ArrayList<>();
		Iterable<Employee> emps=  repo.findAll();
		
		for(Employee emp: emps) {
			EmployeeDTO empwithtaxdetails = new EmployeeDTO();
			Map<String, Double> map= calculateIncometaxforEmp(emp);
			double finalTax= map.get("FinalTax");
			double totalIncome = map.get("TotalIncome");
			double cessamount = calculateCESSamoutforEmp(finalTax, totalIncome);
			empwithtaxdetails.setEmployee(emp);
			empwithtaxdetails.setTotalSalary(totalIncome);
			empwithtaxdetails.setTaxamount(finalTax);
			empwithtaxdetails.setCessamount(cessamount);
			allEmployees.add(empwithtaxdetails);
			}
		return allEmployees;
	}
	
	public Map<String, Double> calculateIncometaxforEmp(Employee emp){
		Map<String, Double> map = new HashMap<String, Double>();
	    double finaltax; 
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
	    Date finYrEndDate = null;
		try {
			finYrEndDate = sdf.parse("2024/03/31");

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    Date dateofJoin = emp.getDateOfJoin();
		try {
			dateofJoin = sdf.parse(sdf.format(emp.getDateOfJoin()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    // days diff between financial year end date and joining date
	    long diffInMillies = finYrEndDate.getTime() - dateofJoin.getTime();
	    long diff = diffInMillies / (1000 * 60 * 60 * 24);
	    double totalincome = (double) (diff*emp.getSalary()/30);
	    if(totalincome<=250000)
			finaltax=0;
		else if(totalincome<=500000)
			finaltax= (totalincome-250000)*0.05;
		else if(totalincome <= 1000000)
			finaltax= (totalincome-500000)*0.1 + 12500;
		else
			finaltax=(totalincome-1000000)*0.2 +37500;
	    map.put("TotalIncome", totalincome);
	    map.put("FinalTax", finaltax);
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
		return totalcessamt;
		
	}

}
