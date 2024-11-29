package com.ssafy.thezip.apartment.exception;

import lombok.Getter;

@Getter
public enum ApartmentErrorCode {

    DONGCODE_REQUIRED("06000", "동코드는 필수로 입력해야합니다.");


    private final String code;
    private final String message;

    ApartmentErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
