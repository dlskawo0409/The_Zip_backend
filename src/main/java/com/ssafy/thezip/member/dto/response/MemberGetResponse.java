package com.ssafy.thezip.member.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.thezip.common.Image.domain.Image;
import com.ssafy.thezip.member.domain.Blocked;
import com.ssafy.thezip.member.domain.Gender;
import com.ssafy.thezip.member.domain.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberGetResponse {
    private Long memberId;
    private String username;
    private String nickname;
    private Gender gender;
    private Blocked isBlocked;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(hidden = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deletedAt;
    private Role role;
    private Image profile;
    private Integer collegeId;
}
