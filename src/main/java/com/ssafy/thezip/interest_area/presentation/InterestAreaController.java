package com.ssafy.thezip.interest_area.presentation;


import com.ssafy.thezip.interest_area.application.InterestAreaService;
import com.ssafy.thezip.interest_area.domain.InterestArea;
import com.ssafy.thezip.interest_area.dto.reqeust.InterestAreaInsertDTO;
import com.ssafy.thezip.member.dto.request.CustomMemberDetails;
import com.ssafy.thezip.member.exception.MemberException;
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
@RequestMapping("/interest-area")
@Tag(name = "Interest Area", description = "관심 지역 API")
public class InterestAreaController {

    private final InterestAreaService interestAreaService;

    @PostMapping
    public ResponseEntity<?> registerInterestAreaController(@RequestBody  InterestAreaInsertDTO interestAreaInsertDTO,
                                                          @AuthenticationPrincipal CustomMemberDetails loginMember){

        interestAreaService.insertInterestArea(interestAreaInsertDTO, loginMember);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getInterestAreaController(@AuthenticationPrincipal CustomMemberDetails loginMember){
        List<InterestArea> interestAreaList = interestAreaService.getInterestArea(loginMember);
        return ResponseEntity.ok(interestAreaList);

    }

    @DeleteMapping("/{interest-area-id}")
    public ResponseEntity<?> deleteInterestAreaController(@PathVariable("interest-area-id") Long interestHouseId
            ,@AuthenticationPrincipal CustomMemberDetails loginMember) throws MemberException.MemberBadRequestException {

        interestAreaService.deleteInterestArea(interestHouseId, loginMember);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
