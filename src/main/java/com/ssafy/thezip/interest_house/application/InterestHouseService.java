package com.ssafy.thezip.interest_house.application;

import com.ssafy.thezip.interest_house.domain.InterestHouseRepository;
import com.ssafy.thezip.interest_house.dto.request.InterestHouseInsertDTO;
import com.ssafy.thezip.interest_house.dto.response.InterestHouseWithHouseDeal;
import com.ssafy.thezip.member.dto.request.CustomMemberDetails;
import com.ssafy.thezip.member.exception.MemberErrorCode;
import com.ssafy.thezip.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestHouseService {

    private final InterestHouseRepository interestHouseRepository;


    public void registerInterestHouse(InterestHouseInsertDTO interestHouseInsertDTO,
                                      CustomMemberDetails loginMember){
        interestHouseRepository.save(interestHouseInsertDTO, loginMember.getMemberId());
    }

    public List<InterestHouseWithHouseDeal> getInterestHouse(CustomMemberDetails loginMember){
        return interestHouseRepository.findHouseDeal(loginMember.getMemberId());
    }

    @Transactional
    public void removeInterestHouse(String aptSeq, CustomMemberDetails loginMember) throws MemberException.MemberBadRequestException {
        boolean isAdmin = loginMember.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
//        if(!(isOwner(loginMember.getMemberId(), aptSeq) || isAdmin)){
//            throw new MemberException.MemberBadRequestException(MemberErrorCode.FORBIDDEN_ACCESS);
//        }

        interestHouseRepository.delete(aptSeq, loginMember.getMemberId());
    }
//    public boolean isOwner(Long memberId, String aptSeq) {
//        return interestHouseRepository.existsByMemberIdAndInterestHouseId(memberId, aptSeq);
//    }

}
