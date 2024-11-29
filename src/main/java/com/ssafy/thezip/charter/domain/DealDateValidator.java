package com.ssafy.thezip.charter.domain;

import com.ssafy.thezip.charter.dto.request.CharterInsertDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.YearMonth;

public class DealDateValidator implements ConstraintValidator<ValidDealDate, CharterInsertDTO> {

    @Override
    public void initialize(ValidDealDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(CharterInsertDTO dto, ConstraintValidatorContext context) {
        int dealYear = dto.getDealYear();
        int dealMonth = dto.getDealMonth();
        int dealDay = dto.getDealDay();

        // 연도의 범위 검사 (예: 1900 ~ 2100년 사이)
        if (dealYear < 1900 || dealYear > 2100) {
            return false;
        }

        // 월의 범위 검사 (1 ~ 12 사이)
        if (dealMonth < 1 || dealMonth > 12) {
            return false;
        }

        // 연도와 월을 기반으로 해당 월의 최대 일 수 계산
        YearMonth yearMonth = YearMonth.of(dealYear, dealMonth);
        int maxDayOfMonth = yearMonth.lengthOfMonth();

        // 일의 범위 검사 (1 ~ 해당 월의 최대 일 수 사이)
        return dealDay >= 1 && dealDay <= maxDayOfMonth;
    }
}
