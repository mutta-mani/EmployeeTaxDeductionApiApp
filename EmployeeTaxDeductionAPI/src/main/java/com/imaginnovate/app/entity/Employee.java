package com.imaginnovate.app.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long emp_ID;

	@NotNull(message = "First name should not be null.")
	private String first_name;
	
	@NotNull(message = "Last name should not be null.")
	private String last_name;
	
	@NotNull(message = "Email should not be null.")
	@Email(message = "Email should be valid.")
	private String email;
	
	@NotNull(message = "Date of join should not be null.")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private Date dateOfJoin;
	
	@NotNull(message ="salary should not be null.")
	private Double salary;
	
	@NotNull(message = "Phone number should not be null.")
	@ElementCollection
	private List<Integer> phoneNums;

}
