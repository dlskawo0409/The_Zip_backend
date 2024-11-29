package com.ssafy.thezip.houseinfo.application;

import com.ssafy.thezip.houseinfo.domain.HouseInfo;
import com.ssafy.thezip.houseinfo.domain.HouseInfoRepository;
import com.ssafy.thezip.houseinfo.dto.request.DealInfoDTO;
import com.ssafy.thezip.houseinfo.dto.response.*;
import com.ssafy.thezip.houseinfo.dto.request.HouseInfoDTO;
import com.ssafy.thezip.member.dto.request.CustomMemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HouseInfoService {

    private final HouseInfoRepository houseInfoRepository;


    public List<HouseInfoWithHouseDeals> getHouseInfoWithHouseDealsByDongCode(HouseInfoDTO houseInfoDTO,
                                                                              CustomMemberDetails loginMember,
                                                                              DealInfoDTO dealInfoDTO){
        Long memberId = loginMember == null ? null : loginMember.getMemberId();

        return houseInfoRepository.findAllByDongCode(houseInfoDTO, memberId, dealInfoDTO);
    }

    public List<LatitudeAndLongitude> getLatitudeAndLongitudeByDongCode(HouseInfoDTO houseInfoDTO){
        return houseInfoRepository.findLatitudeAndLongitudeByDongCode(houseInfoDTO);
    }

    public HouseInfoWithImagesDTO getHouseInfoWithHouseDealsByDongCodeOnlyOne(String aptSeq,
                                                                              CustomMemberDetails loginMember){
        return houseInfoRepository.findAllByDongCodeOnlyOne(aptSeq, loginMember == null ? null : loginMember.getMemberId());
    }

    public  List<HouseInfo> getHouseInfoWithHouseDealsByName(String name){
        return houseInfoRepository.findByName(name);
    }

    public List<HouseInfoLikeDTO> getHouseInfoByLikes(Integer count){
        return houseInfoRepository.findByLikes(count);
    }

}
