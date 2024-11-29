package com.ssafy.thezip.member.dto.response;

import com.ssafy.thezip.member.domain.Gender;
import com.ssafy.thezip.member.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberUpdateResponse {
    private String nickname;
    private Gender gender;
    private Role role;
}
