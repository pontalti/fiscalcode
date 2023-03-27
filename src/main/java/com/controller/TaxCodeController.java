package com.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ClientDTO;
import com.dto.TaxCodeDTO;
import com.exception.NotSuchCityException;
import com.service.FiscalCodeCalculatorService;
import com.service.FiscalCodeExtractorService;
import com.util.Util;

@RestController
public class TaxCodeController {

	@Autowired
	private FiscalCodeCalculatorService fccService;
	
	@Autowired
	private FiscalCodeExtractorService fceService;
	
	public TaxCodeController() {
		super();
	}
	
	@GetMapping("/")
	public String home() {
		return "Home!!!!!";
	}
 
	@GetMapping(path = "/discoverTaxCodeDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ClientDTO> getTaxCode(@RequestBody(required = true) @Valid TaxCodeDTO taxCodeDTO) {
		ClientDTO dto = this.fceService.reverseFC(taxCodeDTO.getTaxCode());
		ResponseEntity<ClientDTO> response = new ResponseEntity<ClientDTO>(dto, HttpStatus.OK);
		return response;
	}
	
	@GetMapping(path = "/discoverTaxCode", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<TaxCodeDTO> getCreateTaxCode(@RequestBody(required = true) @Valid ClientDTO clientDto) {
		ResponseEntity<TaxCodeDTO> response = null;
		try {
			TaxCodeDTO dto = this.fccService.calculateFC(	clientDto.getName(), 
															clientDto.getSurname(), 
															clientDto.getGender().toString().charAt(0), 
															Util.convertStringDateToLocalDate(clientDto.getDateOfBirth()), 
															clientDto.getPlaceOfBirth(),
															clientDto.getState());
			response = new ResponseEntity<TaxCodeDTO>(dto, HttpStatus.OK);
		} catch (NotSuchCityException e) {
			response = new ResponseEntity<TaxCodeDTO>(HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}
		return response;
	}

}
