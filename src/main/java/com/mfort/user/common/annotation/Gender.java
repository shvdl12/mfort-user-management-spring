package com.mfort.user.common.annotation;


import com.mfort.user.common.annotation.validator.GenderValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
public @interface Gender {
    String message() default "성별은 Femail, Male 중 하나 이여야 합니다.";

    Class[] groups() default {};

    Class[] payload() default {};
}


