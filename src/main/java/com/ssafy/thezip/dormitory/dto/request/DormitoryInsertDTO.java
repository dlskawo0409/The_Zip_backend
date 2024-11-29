package com.ssafy.thezip.dormitory.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.thezip.dormitory.domain.DormitoryKind;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class DormitoryInsertDTO {
    @Schema(description = "방이름", required = true, example = "글로벌홀")
    private String name;
    @Schema(description = "동이름", required = true, example = "2인실")
    private Integer roomNumber;
    @Schema(description = "방갯수", required = true, example = "1")
    private Integer roomCount;
    @Schema(description = "수용인원", required = true, example = "2")
    private Integer capacity;
    @Schema(description = "보증금", required = true, example = "100000")
    private Integer deposit;
    @Schema(description = "월세", required = false, example = "")
    private Integer rent;
    @Schema(description = "1년치 월세비용", required = false, example = "4572000")
    private Integer yearlyRent;
    @Schema(description = "관리비", required = false, example = "700000")
    private Integer maintenance;
    @Schema(description = "방 종류 UNIVERSITY, GRADUATE, FOREIGN_FAMILY", required = false, example = "UNIVERSITY")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private DormitoryKind dormitoryKind;
    @Schema(description = "학교Id", required = false, example = "1")
    private Long collegeId;
}
