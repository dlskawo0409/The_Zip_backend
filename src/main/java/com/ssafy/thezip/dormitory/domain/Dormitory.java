package com.ssafy.thezip.dormitory.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;


@Getter
@Setter
@NoArgsConstructor
public class Dormitory {
    private Long dormitoryId;
    private String name;
    private String roomNumber;
    private Long roomCount;
    private Long capacity;
    private Long deposit;
    private Long rent;
    private Long yearlyRent;
    private Long maintenance;
    private String dormitoryKind;
    private Long collegeId;
    private Long sum;
}