package com.ssafy.thezip.interest_house.dto.response;

import com.ssafy.thezip.housedeal.domain.HouseDeal;
import com.ssafy.thezip.houseinfo.domain.HouseInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InterestHouseWithHouseDeal {
    private Long interestHouseId;
    private String aptSeq;
    private String imageURL;
    private HouseInfo houseInfo;
    private HouseDeal houseDeal;
}
