package com.ssafy.thezip.dormitory.exception;

import com.ssafy.thezip.common.exception.ErrorCode;
import com.ssafy.thezip.member.exception.MemberErrorCode;
import org.apache.coyote.BadRequestException;

public class DormitoryException {
    public static class DormitoryBadRequestException extends BadRequestException {
        public DormitoryBadRequestException(DormitoryErrorCode errorCode) {
            super(String.valueOf(new ErrorCode<>(errorCode.getCode(), errorCode.getMessage())));
        }
    }
}
