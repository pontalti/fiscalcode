package com.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dto.TaxCodeDTO;
import com.entity.Italy;
import com.exception.NotSuchCityException;
import com.repository.ItalyRepository;

@Service
public class FiscalCodeCalculatorServiceImpl implements FiscalCodeCalculatorService{

	private final ItalyRepository repository;
	
	public FiscalCodeCalculatorServiceImpl(ItalyRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public TaxCodeDTO calculateFC(String name, String surname, char sex, LocalDate dob, String placeOfBirth, String state) throws NotSuchCityException {
		Optional<Italy> city = this.repository.findByCityName(placeOfBirth, state);
		if ( city.isEmpty()) {
			throw new NotSuchCityException();
		} else {
			return FiscalCodeCalculatorService.calculateFC(name, surname, sex, dob, city.get());
		}
	}

}