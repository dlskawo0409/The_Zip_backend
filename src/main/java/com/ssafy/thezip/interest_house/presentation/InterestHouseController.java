package com.ssafy.thezip.interest_house.presentation;

import com.ssafy.thezip.interest_house.application.InterestHouseService;
import com.ssafy.thezip.interest_house.dto.request.InterestHouseInsertDTO;
import com.ssafy.thezip.interest_house.dto.response.InterestHouseWithHouseDeal;
import com.ssafy.thezip.member.dto.request.CustomMemberDetails;
import com.ssafy.thezip.member.exception.MemberException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/interest-house")
@Tag(name = "Interest House", description = "관심 아파트 API")
public class InterestHouseController {

    private final InterestHouseService interestHouseService;

    @Operation(summary = "관심 아파트 추가", description = "관심 아파트를 추가합니다.")
    @PostMapping
    public void insertInterestController(@RequestBody  InterestHouseInsertDTO interestHouseInsertDTO,
                                         @AuthenticationPrincipal CustomMemberDetails loginMember){

        interestHouseService.registerInterestHouse(interestHouseInsertDTO, loginMember);
    }

    @Operation(summary = "관심 아파트 목록", description = "관심 아파트 목록을 불러옵니다.")
    @GetMapping
    public ResponseEntity<?> getInterestHouse(@AuthenticationPrincipal CustomMemberDetails loginMember){
        List<InterestHouseWithHouseDeal> interestHouseWithHouseDealList = interestHouseService.getInterestHouse(loginMember);
        return ResponseEntity.ok(interestHouseWithHouseDealList);
    }

    @Operation(summary = "관심 아파트 삭제", description = "관심 아파트 목록을 삭제합니다.")
    @DeleteMapping("/{aptSeq}")
    public ResponseEntity<?> deleteInterestHouse(@PathVariable("aptSeq") String aptSeq ,
                                                 @AuthenticationPrincipal CustomMemberDetails loginMember) throws MemberException.MemberBadRequestException {
        interestHouseService.removeInterestHouse(aptSeq, loginMember);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    

}
