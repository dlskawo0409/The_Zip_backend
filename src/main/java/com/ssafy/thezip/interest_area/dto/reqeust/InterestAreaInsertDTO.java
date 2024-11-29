package com.ssafy.thezip.interest_area.dto.reqeust;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InterestAreaInsertDTO {
    @Schema(description = "동코드", required = true, example = "1117010900")
    private String dongCode;
}
