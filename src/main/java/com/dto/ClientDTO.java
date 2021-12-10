package com.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.validation.EnumGenderPattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ClientDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Name is mandatory")
	@NotNull(message = "Name is mandatory")
	private String name;
	
	@NotBlank(message = "Surname is mandatory")
	@NotNull(message = "Surname is mandatory")
	private String surname;
	
	@NotBlank(message = "Dob is mandatory")
	@NotNull(message = "Dob is mandatory")
	private String dateOfBirth;
	
	@NotBlank(message = "Place Of Birth is mandatory")
	@NotNull(message = "Place Of Birth is mandatory")
	private String placeOfBirth;
	
	@NotNull(message = "Gender is mandatory")
    @EnumGenderPattern(anyOf = {Gender.M, Gender.F})
	private Gender gender;
	
	@NotBlank(message = "State is mandatory")
	@NotNull(message = "State is mandatory")
	private String state;
	
	public ClientDTO() {
		super();
		this.name 			= "";
		this.surname 		= "";
		this.dateOfBirth 	= "";
		this.placeOfBirth	= "";
		this.state			= "";
		this.gender			= null;
	}

}
