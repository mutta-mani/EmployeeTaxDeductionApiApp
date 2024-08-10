package com.imaginnovate.app.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
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
	private int employeeId;

	@NotBlank(message = "First name should not be null or empty.")
	private String firstName;
	
	@NotBlank(message = "Last name should not be null or empty.")
	private String lastName;
	
	@NotBlank(message = "Email should not benull or empty")
	@Email(message = "Email should be valid.")
	private String email;
	
	@NotBlank(message = "Doj should not be null or empty.")
    @JsonFormat(pattern = "yyyy-mm-dd")
	private String doj;	
	
	@NotNull(message = "Salary should not be null or empty.")
	@Positive(message = "Salary should be positive value")
	private Double salary;
	
@NotBlank(message = "Phone no should not be null or empty")
@Pattern(regexp = "^[6-9]\\d{9}$", message= "phoneNumber count should be 10 digits and starts with 6,7,8,9 digit")
private String phoneNumbers;

}
