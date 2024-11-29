package com.ssafy.thezip.apartment.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApartmentInfoByNameDTO {
    private String name;
    private int start;
    private int limit;

}
