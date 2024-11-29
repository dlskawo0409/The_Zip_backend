package com.ssafy.thezip.apartment.exception;

import com.ssafy.thezip.common.exception.ErrorCode;

import org.apache.coyote.BadRequestException;

public class ApartmentException {

    public static class HouseDealBadRequestException extends BadRequestException {
        public HouseDealBadRequestException(ApartmentErrorCode errorCode) {
            super(String.valueOf(new ErrorCode<>(errorCode.getCode(), errorCode.getMessage())));
        }
    }
}
