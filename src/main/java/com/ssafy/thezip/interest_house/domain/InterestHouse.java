package com.ssafy.thezip.interest_house.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InterestHouse {
    private Long interestHouseId;
    private Long memberId;
    private String aptSeq;
}
