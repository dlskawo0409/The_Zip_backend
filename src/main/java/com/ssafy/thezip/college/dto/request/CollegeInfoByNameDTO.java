package com.ssafy.thezip.college.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollegeInfoByNameDTO {
    @Schema(description = "대학이름", required = true, example = "한국")
    private String name;
}
