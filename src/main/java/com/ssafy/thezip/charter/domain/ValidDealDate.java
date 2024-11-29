package com.ssafy.thezip.charter.domain;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DealDateValidator.class)
@Target({ ElementType.TYPE }) // 클래스 수준에 적용
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDealDate {
    String message() default "Invalid deal date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
