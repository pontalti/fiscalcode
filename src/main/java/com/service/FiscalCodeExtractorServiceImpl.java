package com.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dto.ClientDTO;
import com.dto.Gender;
import com.entity.Italy;
import com.repository.ItalyRepository;
import com.util.Util;

@Service
public class FiscalCodeExtractorServiceImpl implements FiscalCodeExtractorService{

	private final ItalyRepository repository;
	
	public FiscalCodeExtractorServiceImpl(ItalyRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public ClientDTO reverseFC(String fiscalCode) {
		char sex = 'M';
		
		String day = fiscalCode.substring(9, 11);
		int numDay = Integer.parseInt(day);
		if (numDay > 31) {
			sex = 'F';
			numDay -= 40;
		}
		
		Month month = FiscalCodeExtractorService.extractMonth(fiscalCode);
		int theYear = FiscalCodeExtractorService.extractYear(fiscalCode);
		
		LocalDate date = LocalDate.of(theYear, month, numDay);
		String birthDate = Util.convertLocaldateToFormatedDate(date);
		String cityCode = fiscalCode.substring(11, 15);
		
		Optional<Italy> city = this.repository.findByCode(cityCode);
		ClientDTO dto = null;
		if(city.isPresent()) {
			dto = ClientDTO
						.builder()
						.name(null)
						.surname(null)
						.dateOfBirth(birthDate)
						.gender(Gender.valueOf(String.valueOf(sex)))
						.state(city.get().getProv())
						.placeOfBirth(city.get().getCityName())
						.build();
		}else {
			dto = ClientDTO
					.builder()
					.name(null)
					.surname(null)
					.dateOfBirth(birthDate)
					.gender(Gender.valueOf(String.valueOf(sex)))
					.state("")
					.placeOfBirth("")
					.build();
		}
		return dto;
	}

}
