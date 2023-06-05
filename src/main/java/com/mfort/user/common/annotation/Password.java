package com.mfort.user.common.annotation;


import com.mfort.user.common.annotation.validator.PasswordValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default "비밀번호는 숫자, 문자, 특수 문자를 포함한 8자리 이상 이어야 합니다.";

    Class[] groups() default {};

    Class[] payload() default {};
}


