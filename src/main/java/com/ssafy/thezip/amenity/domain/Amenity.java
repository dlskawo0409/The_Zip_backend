package com.ssafy.thezip.amenity.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Amenity {
    private Long amenityId;
    private Long dormitoryId;
    private String name;

}
