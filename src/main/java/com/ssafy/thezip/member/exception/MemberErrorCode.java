package com.ssafy.thezip.member.exception;

import lombok.Getter;

@Getter
public enum MemberErrorCode {

    ILLEGAL_NICKNAME_NULL("05000", "닉네임은 필수로 입력해야합니다."),
    ILLEGAL_NICKNAME_LENGTH("05001", "닉네임 길이는 7자여야 합니다."),
    ILLEGAL_EMAIL_NULL("05002", "이메일은 필수로 입력해야합니다."),
    ILLEGAL_EMAIL_PATTERN("05003", "올바르지 않은 이메일 형식입니다."),
    ILLEGAL_ROLE("05004","허용되진않는 권한입니다."),
    FORBIDDEN_MEMBER_STATUS("05100", "탈퇴 혹은 차단된 회원입니다."),
    FORBIDDEN_ACCESS("05101", "해당 회원 정보에 대한 접근 권한이 없습니다."),
    MEMBER_NOT_FOUND("05400", "존재하지 않는 회원입니다."),
    MEMBER_ALREADY_EXIST("05401", "이미 가입된 이메일입니다."),
    ILLEGAL_NICKNAME_ALREADY_EXISTS("05900", "이미 존재하는 닉네임입니다.");

    private final String code;
    private final String message;

    MemberErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}