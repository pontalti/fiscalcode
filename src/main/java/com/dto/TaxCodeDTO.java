package com.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class TaxCodeDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Tax code is mandatory")
	@NotNull(message = "Tax code is mandatory")
	private String taxCode;

}
