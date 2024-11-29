package com.ssafy.thezip.interest_house.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InterestHouseInsertDTO {

    @Schema(description = "아파트 seq", required = true, example = "11170-16")
    private String aptSeq;

}
