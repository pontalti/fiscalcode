package com.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.validation.EnumGenderPattern;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

}
