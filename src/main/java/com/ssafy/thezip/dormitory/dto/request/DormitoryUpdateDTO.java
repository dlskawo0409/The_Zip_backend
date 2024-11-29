package com.ssafy.thezip.dormitory.dto.request;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.ssafy.thezip.dormitory.domain.DormitoryKind;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DormitoryUpdateDTO {
    @Schema(description = "기숙사 Id", required = true, example = "1")
    private Long dormitoryId;

    @Schema(description = "대학 Id", required = true, example = "1")
    private Long collegeId;

    @Schema(description = "방이름", required = false, example = "글로벌홀")
    private String name;

    @Schema(description = "동이름", required = false, example = "2인실")
    private Integer roomNumber;

    @Schema(description = "방갯수", required = false, example = "1")
    private Integer roomCount;

    @Schema(description = "수용인원", required = false, example = "2")
    private Integer capacity;

    @Schema(description = "보증금", required = false, example = "")
    @JsonSetter(nulls = Nulls.SKIP)
    private Integer deposit;

    @Schema(description = "월세", required = false, example = "381000")
    private Integer rent;

    @Schema(description = "1년치 월세비용", required = false, example = "")
    private Integer yearlyRent;

    @Schema(description = "관리비", required = false, example = "")
    private Integer maintenance;

    @Schema(description = "방 종류 UNIVERSITY, GRADUATE, FOREIGN_FAMILY", required = false, example = "")
    private DormitoryKind dormitoryKind;
}
