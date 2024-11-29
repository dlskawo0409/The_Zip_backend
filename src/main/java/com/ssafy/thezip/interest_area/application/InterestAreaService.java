package com.ssafy.thezip.interest_area.application;

import com.ssafy.thezip.dongcode.domain.DongCodeRepository;
import com.ssafy.thezip.dongcode.dto.response.SidoGugunDongResponseDTO;
import com.ssafy.thezip.interest_area.domain.InterestArea;
import com.ssafy.thezip.interest_area.domain.InterestAreaRepository;
import com.ssafy.thezip.interest_area.dto.reqeust.InterestAreaInsertDTO;
import com.ssafy.thezip.interest_area.exception.InterestAreaErrorCode;
import com.ssafy.thezip.interest_area.exception.InterestAreaException;
import com.ssafy.thezip.member.dto.request.CustomMemberDetails;
import com.ssafy.thezip.member.exception.MemberErrorCode;
import com.ssafy.thezip.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InterestAreaService {

    private final InterestAreaRepository interestAreaRepository;
    private final DongCodeRepository dongCodeRepository;

    public void insertInterestArea(InterestAreaInsertDTO interestAreaInsertDTO,
                                   CustomMemberDetails loginMember) {
        SidoGugunDongResponseDTO sidoGugunDongResponseDTO = dongCodeRepository.findSidoGugunDongByDongCode(interestAreaInsertDTO.getDongCode());
        StringBuilder stringBuilder = new StringBuilder(sidoGugunDongResponseDTO.getSido()+" ");
        stringBuilder.append(sidoGugunDongResponseDTO.getGugun()+" ");
        stringBuilder.append(sidoGugunDongResponseDTO.getDong());
        System.out.println(stringBuilder.toString());
        try {
            interestAreaRepository.save(interestAreaInsertDTO, stringBuilder.toString(), loginMember.getMemberId());
        } catch (DataIntegrityViolationException e) {
            // Unique 제약 조건 위반일 경우
            throw new InterestAreaException.InterestAreaConflictException(InterestAreaErrorCode.DUPLICATE_DONG_CODE, interestAreaInsertDTO.getDongCode());
        }
    }

    public List<InterestArea> getInterestArea(CustomMemberDetails loginMember){
        return interestAreaRepository.findByMemberId(loginMember.getMemberId());
    }

    public void deleteInterestArea(Long interestAreaId, CustomMemberDetails loginMember) throws MemberException.MemberBadRequestException {
        boolean isAdmin = loginMember.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if(!(isOwner(loginMember.getMemberId(), interestAreaId) || isAdmin)){
            throw new MemberException.MemberBadRequestException(MemberErrorCode.FORBIDDEN_ACCESS);
        }

        interestAreaRepository.delete(interestAreaId);
    }

    public boolean isOwner(Long memberId, Long interestHouseId) {
        return interestAreaRepository.existsByMemberIdAndInterestAreaId(memberId, interestHouseId);
    }

}
