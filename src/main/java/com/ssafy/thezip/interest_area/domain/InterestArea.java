package com.ssafy.thezip.interest_area.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InterestArea {
    private Long interestAreaId;
    private Long memberId;
    private String name;
    private String dongCode;
}
