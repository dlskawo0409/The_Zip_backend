package com.ssafy.thezip.interest_area.exception;

import lombok.Getter;

@Getter
public enum InterestAreaErrorCode {
    DUPLICATE_DONG_CODE("07000", "이미 관심 지역으로 지정되어 있습니다.");

    private final String code;
    private final String message;

    InterestAreaErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
