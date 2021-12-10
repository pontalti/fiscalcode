package com.validation;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.dto.Gender;

public class EnumGenderPatternValidator implements ConstraintValidator<EnumGenderPattern, Gender>{
	
	private Gender[] subset;
	
	public EnumGenderPatternValidator() {
		super();
	}
	
    @Override
    public void initialize(EnumGenderPattern constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(Gender value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
