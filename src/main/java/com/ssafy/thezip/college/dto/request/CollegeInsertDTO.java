package com.ssafy.thezip.college.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CollegeInsertDTO {
    @Schema(description = "동코드", required = true, example = "1111010100")
    private String collegeDong;
    @Schema(description = "대학 이름", required = true, example = "한국외국어대학교")
    private String name;
    @Schema(description = "위도", required = true, example = "37.5974453")
    private Double latitude;
    @Schema(description = "경도", required = true, example = "127.0588002")
    private Double longitude;
    @Schema(description = "기숙사 사이트", required = true, example = "https://mhdorm.hufs.ac.kr/mhdorm/index.do")
    private String url;
}
