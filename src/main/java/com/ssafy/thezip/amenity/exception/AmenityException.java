package com.ssafy.thezip.amenity.exception;

import com.ssafy.thezip.common.exception.ConflictException;
import com.ssafy.thezip.common.exception.ErrorCode;
import com.ssafy.thezip.interest_area.exception.InterestAreaErrorCode;

public class AmenityException {
    public static class AmenityConflictException extends ConflictException {
        public AmenityConflictException(InterestAreaErrorCode errorCode, String value) {
            super(new ErrorCode<>(errorCode.getCode(), errorCode.getMessage(), value));
        }
    }
}

