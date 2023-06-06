package com.mfort.user.common.annotation.validator;


import com.mfort.user.common.annotation.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;


public class PasswordValidator implements ConstraintValidator<Password, String> {

    String passwordRegex = "^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-]).{8,}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || Pattern.matches(passwordRegex, value);
    }
}

