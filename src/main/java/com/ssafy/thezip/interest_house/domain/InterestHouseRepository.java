package com.ssafy.thezip.interest_house.domain;

import com.ssafy.thezip.interest_house.dto.request.InterestHouseInsertDTO;
import com.ssafy.thezip.interest_house.dto.response.InterestHouseWithHouseDeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InterestHouseRepository {
    void save(@Param("interestHouseInsertDTO") InterestHouseInsertDTO interestHouseInsertDTO,
              @Param("memberId") Long memberId);

    List<InterestHouseWithHouseDeal> findHouseDeal(Long memberId);
    void delete(@Param("aptSeq") String aptSeq, @Param("memberId") Long memberId);

//    boolean existsByMemberIdAndInterestHouseId(@Param("memberId") Long memberId,
//                                               @Param("aptSeq")String aptSeq);
}

