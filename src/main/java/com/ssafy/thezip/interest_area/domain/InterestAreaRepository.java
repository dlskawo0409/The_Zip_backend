package com.ssafy.thezip.interest_area.domain;

import com.ssafy.thezip.interest_area.dto.reqeust.InterestAreaInsertDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InterestAreaRepository {
    void save(InterestAreaInsertDTO interestAreaInsertDTO,
              @Param("name") String name,
              @Param("memberId") Long memberId);
    List<InterestArea> findByMemberId(Long memberId);
    void delete(@Param("interestAreaId") Long interestAreaId);

    boolean existsByMemberIdAndInterestAreaId(@Param("memberId") Long memberId,
                                               @Param("interestAreaId")Long interestAreaId);

}
