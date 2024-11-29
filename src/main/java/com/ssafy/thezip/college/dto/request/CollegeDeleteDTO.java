package com.ssafy.thezip.college.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollegeDeleteDTO {
    @Schema(description = "대학Id", required = true, example = "3")
    private Long collegeId;
}
