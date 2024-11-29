package com.ssafy.thezip.houseinfo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class HouseInfo {
    private String aptSeq;
    private String dongName;
    private String jibun;
    private String roadName;
    private String roadNameBonbun; // 도로명 주소 본번
    private String roadNameBubun;
    private String apartName;
    private int buildYear;
    private String latitude;
    private String longitude;
}
