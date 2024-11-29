package com.ssafy.thezip.common.exception;

import org.springframework.http.HttpStatus;
public class ConflictException extends GlobalException {

    public ConflictException(ErrorCode<?> errorCode) {
        super(errorCode, HttpStatus.CONFLICT);
    }

}