package com.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TaxCodeDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Tax code is mandatory")
	@NotNull(message = "Tax code is mandatory")
	private String taxCode;
	
	public TaxCodeDTO() {
		super();
		this.taxCode = "";
	}

}
