package com.ssafy.thezip.college.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class College {
    private Long collegeId;
    private String collegeName;
    private String collegeEnglishName;
    private String branchType;
    private String regionName;
    private String roadAddress;
    private String homePage;
}
