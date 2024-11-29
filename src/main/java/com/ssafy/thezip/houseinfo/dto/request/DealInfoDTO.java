package com.ssafy.thezip.houseinfo.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DealInfoDTO {
    private Integer min = 0;
    private Integer max = Integer.MAX_VALUE;

    public DealInfoDTO(Integer min, Integer max){
        this.min = min;
        this.max = max;

        if(min == null){
            this.min = 0;
        }
        if(max == null){
            this.max = Integer.MAX_VALUE;
        }
    }
}
