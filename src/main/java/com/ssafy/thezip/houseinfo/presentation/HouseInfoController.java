package com.ssafy.thezip.houseinfo.presentation;

import com.ssafy.thezip.houseinfo.application.HouseInfoService;
import com.ssafy.thezip.houseinfo.dto.request.DealInfoDTO;
import com.ssafy.thezip.houseinfo.dto.response.HouseInfoLikeDTO;
import com.ssafy.thezip.houseinfo.dto.response.HouseInfoWithHouseDeals;
import com.ssafy.thezip.houseinfo.dto.request.HouseInfoDTO;
import com.ssafy.thezip.houseinfo.dto.response.LatitudeAndLongitude;
import com.ssafy.thezip.member.dto.request.CustomMemberDetails;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/houseinfos")
@Tag(name = "houseInfo", description = "아파트 매매 API")
public class HouseInfoController {

    private final HouseInfoService houseInfoService;

    @GetMapping
    public ResponseEntity<?> getHouseInfoByDongCodController(@RequestParam("dongcode") String dongCode,
                                                             @RequestParam(value = "min", required = false) Integer min,
                                                             @RequestParam(value = "max", required = false) Integer max,
                                                             @AuthenticationPrincipal CustomMemberDetails loginMember){

        List<HouseInfoWithHouseDeals> houseInfoList = houseInfoService.getHouseInfoWithHouseDealsByDongCode(new HouseInfoDTO(dongCode),
                loginMember,
                new DealInfoDTO(min, max));
        return ResponseEntity.ok(houseInfoList);
    }

    @GetMapping("/location")
    public ResponseEntity<?> getLatitudeAndLongitudeByDongCodeController(@RequestParam("dongcode") String dongCode,
                                                                         @RequestParam(value = "min", required = false) Integer min,
                                                                         @RequestParam(value = "max", required = false) Integer max){
        HouseInfoDTO houseInfoDTO = new HouseInfoDTO(dongCode);
        houseInfoDTO.setDealInfoDTO(new DealInfoDTO(min, max));
        List<LatitudeAndLongitude> latitudeAndLongitudeList = houseInfoService.getLatitudeAndLongitudeByDongCode(houseInfoDTO);
        return ResponseEntity.ok(latitudeAndLongitudeList);
    }

    @GetMapping("/details/{apt-seq}")
    public ResponseEntity<?> getHouseInfoByDongCodeOnlyOneController(@PathVariable("apt-seq")String aptSeq,
                                                                     @AuthenticationPrincipal CustomMemberDetails loginMember){
        return ResponseEntity.ok(houseInfoService.getHouseInfoWithHouseDealsByDongCodeOnlyOne(aptSeq, loginMember));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getHouseInfoByName(@PathVariable("name") String name){
        return ResponseEntity.ok(houseInfoService.getHouseInfoWithHouseDealsByName(name));
    }

    @GetMapping("/likes/{count}")
    public ResponseEntity<?> getHouseInfoByLikes(@PathVariable("count") Integer count){
        List<HouseInfoLikeDTO> houseInfoLikeDTOList = houseInfoService.getHouseInfoByLikes(count);
        return ResponseEntity.ok(houseInfoLikeDTOList);
    }



}
