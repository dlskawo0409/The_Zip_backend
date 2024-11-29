package com.ssafy.thezip.apartment.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class Apartment{
    private Long aptId;
    private String aptSeq;
    private String name;
    private String dongCode;
    private int floor;
    private float size;
    private int price;
    private Double latitude;
    private Double longitude;
}
