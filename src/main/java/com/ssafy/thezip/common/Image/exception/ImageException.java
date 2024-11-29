package com.ssafy.thezip.common.Image.exception;

import com.ssafy.thezip.common.exception.ErrorCode;

import org.apache.coyote.BadRequestException;

public class ImageException {
    public static class ImageBadRequestException extends BadRequestException {
        public ImageBadRequestException(ImageErrorCode errorCode) {
            super(String.valueOf(new ErrorCode<>(errorCode.getCode(), errorCode.getMessage())));
        }
    }

}
