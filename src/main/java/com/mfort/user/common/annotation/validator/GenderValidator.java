package com.mfort.user.common.annotation.validator;


import com.mfort.user.common.annotation.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class GenderValidator implements ConstraintValidator<Gender, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || "Female".equalsIgnoreCase(value) || "Male".equalsIgnoreCase(value);
    }
}

