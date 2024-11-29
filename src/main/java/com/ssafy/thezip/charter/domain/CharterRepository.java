package com.ssafy.thezip.charter.domain;

import com.ssafy.thezip.charter.dto.request.CharterInfoBySidoGugunDongDTO;
import com.ssafy.thezip.charter.dto.request.CharterInfoDTO;
import com.ssafy.thezip.charter.dto.request.CharterInsertDTO;
import com.ssafy.thezip.charter.dto.request.CharterUpdateDTO;
import com.ssafy.thezip.charter.dto.response.CharterInfoListResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface CharterRepository {

    Long findCountByDongCode(CharterInfoDTO charterInfoDTO);

    List<Charter> findCharterByDongCode(@Param("charterInfoDTO") CharterInfoDTO charterInfoDTO,
                                        @Param("memberId") Long memberId);
    List<Charter> findCharterBySidoGugunDong(@Param("charterInfoBySidoGugunDongDTO")CharterInfoBySidoGugunDongDTO charterInfoBySidoGugunDongDTO,
                                             @Param("memberId") Long memberId);
    List<CharterInfoListResponseDTO> findByName(String name);
    List<Charter> findById(@Param("charterId") Long charterId,
                           @Param("memberId") Long memberId);
    List<Charter> findByLikes(@Param("charterKind")String charterKind,
                              @Param("count") Integer count);
    void save(Charter charter);
    void updateCharter(@Param("charterUpdateDTO") CharterUpdateDTO charterUpdateDTO,
                       @Param("memberId") Long memberId);
    List<Charter> findCharterByMemberId(Long memberId);
    void delete(@Param("charterId") Long charterId, @Param("memberId")Long memberId);

//    Charter findCharterMonthByRandom(Integer count);
//    Charter findCharterYearByRandom(Integer count);

}
