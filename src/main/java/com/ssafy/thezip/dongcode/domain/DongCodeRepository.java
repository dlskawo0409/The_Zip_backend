package com.ssafy.thezip.dongcode.domain;

import com.ssafy.thezip.dongcode.dto.response.SidoGugunDongResponseDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DongCodeRepository {
    List<String> findSido();
    List<String> findGugunBySido(String sido);
    List<String> findDongByGugun(@Param("sido") String sido, @Param("gugun") String gugun);
    String findDongCodeByDong(@Param("sido") String sido, @Param("gugun") String gugun, @Param("dong") String dong);
    SidoGugunDongResponseDTO findSidoGugunDongByDongCode(String dongCode);
}
