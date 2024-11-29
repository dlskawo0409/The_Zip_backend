package com.ssafy.thezip.dormitory.exception;

import lombok.Getter;

@Getter
public enum DormitoryErrorCode {

    ILLEGAL_RENT_AND_YEARLY_RENT_NULL("06000", "월세나 1년치 월세 중 하나는 반드시 존재해야합니다.");

    private final String code;
    private final String message;

    DormitoryErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
