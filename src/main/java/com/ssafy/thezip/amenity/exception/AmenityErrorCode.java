package com.ssafy.thezip.amenity.exception;

public enum AmenityErrorCode {
    DUPLICATE_AMENITY("01000", "이미 등록된 편의시설입니다.");

    private final String code;
    private final String message;

    AmenityErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
