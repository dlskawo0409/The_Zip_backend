package com.ssafy.thezip.charter.dto.request;

import com.ssafy.thezip.charter.domain.BuildingUse;
import com.ssafy.thezip.charter.domain.CharterKind;
import com.ssafy.thezip.charter.domain.ValidDealDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidDealDate
public class CharterUpdateDTO {
    @Schema(description = "월세 번호", required = true, example = "1")
    private Long charterId;
    @Schema(description = "동 번호", required = false, example = "1117010900")
    private String dongCode;

    @Schema(description = "전세/월세", required = false, example = "월세")
    private CharterKind charterKind;
    @Schema(description = "층", required = false, example = "3")
    private String floor;
    @Schema(description = "계약년도", required = false, example = "2024")
    private Integer dealYear;
    @Schema(description = "계약월", required = false, example = "11")
    private Integer dealMonth;
    @Schema(description = "계약일", required = false, example = "20")
    private Integer dealDay;
    @Schema(description = "보증금", required = false, example = "400")
    private Integer deposit;
    @Schema(description = "월세 비용", required = false, example = "55")
    private Integer rent;
    @Schema(description = "건물명", required = false, example = "남재빌딩")
    private String name;
    @Schema(description = "면적", required = true, example = "31.45")
    private Float size;
    @Schema(description = "건설년도", required = false, example = "1999")
    private Integer constructionYear;
    @Schema(description = "건물사용용도", required = false, example = "단독다가구")
    private BuildingUse buildingUse;
    @Schema(description = "건물구", required = true, example = "용산구")
    private String charterGu;
    @Schema(description = "건물 동", required = true, example = "청파동")
    private String charterDong;
    @Schema(description = "본번", required = true, example = "3")
    private Integer bonbun;
    @Schema(description = "본번", required = true, example = "60")
    private Integer bubun;

    @Schema(description = "동 앞 번호", required = true, example = "11170",hidden = true)
    private String preCode;
    @Schema(description = "동 뒤 번호", required = true, example = "10900",hidden = true)
    private String postCode;
    public void changeDongCode(){
        if(!dongCode.trim().isBlank()){
            this.preCode = dongCode.substring(0,5);
            this.postCode= dongCode.substring(5,10);
        }
    }
}
