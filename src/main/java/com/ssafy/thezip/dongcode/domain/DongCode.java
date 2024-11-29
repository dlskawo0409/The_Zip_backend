package com.ssafy.thezip.dongcode.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DongCode {
    private String dongCode;
    private String sidoName;
    private String gugunName;
    private String dongName;
}
