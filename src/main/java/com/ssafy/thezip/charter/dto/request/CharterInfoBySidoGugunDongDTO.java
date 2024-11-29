package com.ssafy.thezip.charter.dto.request;

import com.ssafy.thezip.charter.domain.CharterKind;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CharterInfoBySidoGugunDongDTO extends CharterInfoDTO {
    private String sido;
    private String gugun;
    private String dong;


    public CharterInfoBySidoGugunDongDTO(String sido, String gugun, String dong){
        this.sido = sido;
        this.gugun = gugun;
        this.dong = dong;
    }

}
