package com.ssafy.thezip.interest_charter.dto.response;

import com.ssafy.thezip.charter.domain.BuildingUse;
import com.ssafy.thezip.charter.domain.CharterKind;
import com.ssafy.thezip.common.Image.dto.response.ImageResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InterestCharterResponseDTO {
    private Long interestCharterId;
    private Long charterId; // Long
    private CharterKind charterKind; // Enum
    private Integer floor; // int
    private Integer deposit; // int
    private Integer rent; // int
    private String name; // String
    private Float size; // float
    private Integer constructionYear; // int
    private BuildingUse buildingUse; // Enum
    private String charterDong; // String
    private String charterGu; // String
    private Integer bonbun; // Integer
    private Integer bubun; // Integer
    private Integer likes;
    private ImageResponseDTO image; // Image DTO

}
