package com.ssafy.thezip.interest_charter.presentation;

import com.ssafy.thezip.interest_charter.application.InterestCharterService;
import com.ssafy.thezip.interest_charter.domain.InterestCharterRepository;
import com.ssafy.thezip.interest_charter.dto.request.InterestCharterInsertDTO;
import com.ssafy.thezip.interest_charter.dto.response.InterestCharterResponseDTO;
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
@RequestMapping("/interest-charter")
@Tag(name = "Interest Charter", description = "관심 월세/전세 API")
public class InterestCharterController {

    private final InterestCharterService interestCharterService;

    @Operation(summary = "관심 월세/전세 추가", description = "관심 월세/전세를 추가합니다.")
    @PostMapping
    public void insertInterestController(@RequestBody InterestCharterInsertDTO interestCharterInsertDTO,
                                         @AuthenticationPrincipal CustomMemberDetails loginMember){
        interestCharterService.registerInterestCharter(interestCharterInsertDTO, loginMember);
    }

    @Operation(summary = "관심 월세/전세 목록", description = "관심 월세/전세 목록을 불러옵니다.")
    @GetMapping
    public ResponseEntity<?> getInterestHouse(@RequestParam(required = false) String charterKind,
                                              @AuthenticationPrincipal CustomMemberDetails loginMember){
        List<InterestCharterResponseDTO> interestCharterResponseDTOList = interestCharterService.getInterestCharter(charterKind,loginMember);
        return ResponseEntity.ok(interestCharterResponseDTOList);
    }

    @Operation(summary = "관심 월세/전세 삭제", description = "관심 월세/전세 목록을 삭제합니다.")
    @DeleteMapping("/{charter-id}")
    public ResponseEntity<?> deleteInterestHouse(@PathVariable("charter-id") Long charterId ,
                                                 @AuthenticationPrincipal CustomMemberDetails loginMember){
        interestCharterService.removeInterestCharter(charterId, loginMember);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
