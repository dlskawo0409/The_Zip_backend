package com.ssafy.thezip.charter.dto.response;

import com.ssafy.thezip.charter.domain.BuildingUse;
import com.ssafy.thezip.charter.domain.CharterKind;
import com.ssafy.thezip.common.Image.dto.response.ImageResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CharterInfoListResponseDTO {
    private Long charterId; // Long
    private CharterKind charterKind; // Enum
    private int floor; // int
    private int deposit; // int
    private int rent; // int
    private String name; // String
    private float size; // float
    private int constructionYear; // int
    private BuildingUse buildingUse; // Enum
    private String charterDong; // String
    private String charterGu; // String
    private Integer bonbun; // Integer
    private Integer bubun; // Integer
    private Boolean isInterested;

}
