package com.ssafy.thezip.interest_charter.domain;

import com.ssafy.thezip.charter.domain.Charter;
import com.ssafy.thezip.charter.domain.CharterKind;
import com.ssafy.thezip.interest_charter.dto.request.InterestCharterInsertDTO;
import com.ssafy.thezip.interest_charter.dto.response.InterestCharterResponseDTO;
import com.ssafy.thezip.member.dto.request.CustomMemberDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface InterestCharterRepository {
    void save(@Param("interestCharterInsertDTO") InterestCharterInsertDTO interestCharterInsertDTO ,
              @Param("memberId") Long memberId);
    List<InterestCharterResponseDTO> findByMemberId(@Param("charterKind") String charterKind ,
                                                    @Param("memberId") Long memberId);
    void delete(@Param("charterId") Long charterId,
                @Param("memberId") Long memberId);


}
