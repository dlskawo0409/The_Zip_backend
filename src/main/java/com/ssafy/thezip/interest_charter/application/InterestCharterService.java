package com.ssafy.thezip.interest_charter.application;

import com.ssafy.thezip.interest_charter.domain.InterestCharterRepository;
import com.ssafy.thezip.interest_charter.dto.request.InterestCharterInsertDTO;
import com.ssafy.thezip.interest_charter.dto.response.InterestCharterResponseDTO;
import com.ssafy.thezip.member.dto.request.CustomMemberDetails;
import com.ssafy.thezip.member.exception.MemberErrorCode;
import com.ssafy.thezip.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InterestCharterService {

    private final InterestCharterRepository interestCharterRepository;

    public void registerInterestCharter(InterestCharterInsertDTO interestCharterInsertDTO,
                                        CustomMemberDetails loginMember){
        interestCharterRepository.save(interestCharterInsertDTO, loginMember.getMemberId());
    }

    public List<InterestCharterResponseDTO> getInterestCharter(String charterKind
            ,CustomMemberDetails loginMember){
        return interestCharterRepository.findByMemberId(charterKind, loginMember.getMemberId());
    }

    public void removeInterestCharter(Long interestHouseId, CustomMemberDetails loginMember){
        interestCharterRepository.delete(interestHouseId, loginMember.getMemberId());
    }

}
