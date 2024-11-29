package com.ssafy.thezip.housedeal.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.function.IntToLongFunction;

@Getter
@Setter
@NoArgsConstructor
public class HouseDeal {
    private Long no;
    private String floor;
    private Integer dealYear;
    private Integer dealMonth;
    private Integer dealDay;
    private Float size;
    private String dealAmount;
}
