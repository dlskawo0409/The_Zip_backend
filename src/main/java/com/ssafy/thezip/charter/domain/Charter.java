package com.ssafy.thezip.charter.domain;

import com.ssafy.thezip.common.Image.domain.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Charter {
    private Long charterId;
    private CharterKind charterKind;
    private String floor;
    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private int deposit;
    private int rent;
    private String name;
    private float size;
    private int constructionYear;
    private BuildingUse buildingUse;
    private Long memberId;
    private String charterDong;
    private String charterGu;
    private Integer bonbun;
    private Integer bubun;
    private String preCode;
    private String postCode;
    private Image image;

}
