package com.ssafy.thezip.interest_area.exception;

import com.ssafy.thezip.common.exception.ConflictException;
import com.ssafy.thezip.common.exception.ErrorCode;
import com.ssafy.thezip.member.exception.MemberErrorCode;

public class InterestAreaException {

    public static class InterestAreaConflictException extends ConflictException {
        public InterestAreaConflictException(InterestAreaErrorCode errorCode, String value) {
            super(new ErrorCode<>(errorCode.getCode(), errorCode.getMessage(), value));
        }
    }

}
