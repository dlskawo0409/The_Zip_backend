package com.ssafy.thezip.houseinfo.domain;

import com.ssafy.thezip.houseinfo.dto.request.DealInfoDTO;
import com.ssafy.thezip.houseinfo.dto.request.HouseInfoDTO;
import com.ssafy.thezip.houseinfo.dto.response.HouseInfoLikeDTO;
import com.ssafy.thezip.houseinfo.dto.response.HouseInfoWithHouseDeals;
import com.ssafy.thezip.houseinfo.dto.response.HouseInfoWithImagesDTO;
import com.ssafy.thezip.houseinfo.dto.response.LatitudeAndLongitude;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HouseInfoRepository {
//    List<HouseInfo> findByDongCode(String preDongCode, String postDongCode);

    List<HouseInfoWithHouseDeals> findAllByDongCode(@Param("houseInfoDTO") HouseInfoDTO houseInfoDTO,
                                                    @Param("memberId") Long memberId,
                                                    @Param("dealInfoDTO") DealInfoDTO dealInfoDTO);
    List<LatitudeAndLongitude> findLatitudeAndLongitudeByDongCode(HouseInfoDTO houseInfoDTO);
    HouseInfoWithImagesDTO findAllByDongCodeOnlyOne(@Param("aptSeq") String aptSeq,
                                                    @Param("memberId") Long memberId);
    List<HouseInfo> findByName(String aptName);

    List<HouseInfoLikeDTO> findByLikes(Integer count);


}
