package com.imaginnovate.app.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

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

	@NotBlank(message = "First name should not be null or empty.")
	private String first_name;
	
	@NotBlank(message = "Last name should not be null or empty.")
	private String last_name;
	
	@NotBlank(message = "Email should not be null or empty")
	@Email(message = "Email should be valid.")
	private String email;
	
	@PastOrPresent(message = "The date should not be the future.")
	@NotNull(message = "Date of join should not be null.")
    @JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateOfJoin;
	
	@NotNull(message ="salary shoul	d not be null.")
	private Double salary;
	
	@NotNull(message = "Phone number should not be null")
	@ElementCollection
	private List<Long> phoneNums;

}
