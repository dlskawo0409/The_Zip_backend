package com.ssafy.thezip.houseinfo.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseInfoDTO {
    private String preDongCode;
    private String postDongCode;
    private DealInfoDTO dealInfoDTO;

    public HouseInfoDTO(String dongCode){
        this.preDongCode = dongCode.substring(0,5);
        this.postDongCode = dongCode.substring(5,10);
    }

}
