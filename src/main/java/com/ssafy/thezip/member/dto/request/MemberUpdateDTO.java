package com.ssafy.thezip.member.dto.request;

import com.ssafy.thezip.member.domain.Gender;
import com.ssafy.thezip.member.domain.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberUpdateDTO {

    @Schema(description = "비밀번호", required = true, example = "dlskawo49!")
    private String password;

    @Schema(description = "닉네임", required = true, example = "dlskawo0409")
    private String nickname;

    @Schema(description = "성별", required = true, example = "FEMALE")
    private Gender gender;

    @Schema(description = "사용자 Role", required = true, example = "ROLE_USER")
    private Role role;
}
