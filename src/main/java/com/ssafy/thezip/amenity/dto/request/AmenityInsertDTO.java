package com.ssafy.thezip.amenity.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AmenityInsertDTO {
    @Schema(description = "기숙사 ID", required = false, example = "1")
    private Long dormitoryId;
    @Schema(description = "편의시설 이름", required = true, example = "세탁실")
    private String name;
}
