package com.ssafy.thezip.houseinfo.dto.response;

import com.ssafy.thezip.common.Image.domain.Image;
import com.ssafy.thezip.common.Image.dto.response.ImageResponseDTO;
import com.ssafy.thezip.housedeal.domain.HouseDeal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HouseInfoWithHouseDeals {
    private String aptSeq;
    private String preDongCode;
    private String postDongCode;
    private String dongName;
    private String jibun;
    private String roadName;
    private String roadNameBonbun; // 도로명 주소 본번
    private String roadNameBubun;
    private String apartName;
    private int buildYear;
    private String latitude;
    private String longitude;
    private Boolean isInterested;
    private Integer likes;
    private ImageResponseDTO imageResponseDTO;
    List<HouseDeal> houseDealList;
}
