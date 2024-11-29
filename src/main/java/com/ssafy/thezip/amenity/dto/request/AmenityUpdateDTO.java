package com.ssafy.thezip.amenity.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AmenityUpdateDTO {
    @Schema(description = "편의시설 ID", required = true, example = "1")
    private Long amenityId;
    @Schema(description = "편의시설 종류", required = true, example = "헬스장")
    private String name;
}
