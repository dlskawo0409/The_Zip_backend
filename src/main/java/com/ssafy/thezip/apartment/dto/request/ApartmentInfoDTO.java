package com.ssafy.thezip.apartment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ApartmentInfoDTO")
public class ApartmentInfoDTO {

    @Schema(description = "동코드", required = true, example = "1111010100")
    private String dongCode;
    @Schema(description = "시작", required = true, example = "0")
    private int start;
    @Schema(description = "몇 개 가져올지 ", required = true, example = "5")
    private int limit;
}
