package com.ssafy.thezip.charter.presentation;

import com.ssafy.thezip.charter.application.CharterService;
import com.ssafy.thezip.charter.domain.Charter;
import com.ssafy.thezip.charter.domain.CharterKind;
import com.ssafy.thezip.charter.dto.request.CharterInfoBySidoGugunDongDTO;
import com.ssafy.thezip.charter.dto.request.CharterInfoDTO;
import com.ssafy.thezip.charter.dto.request.CharterInsertDTO;
import com.ssafy.thezip.charter.dto.request.CharterUpdateDTO;
import com.ssafy.thezip.houseinfo.dto.request.DealInfoDTO;
import com.ssafy.thezip.member.dto.request.CustomMemberDetails;
import com.ssafy.thezip.member.dto.request.MemberJoinDTO;
import com.ssafy.thezip.member.exception.MemberException;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/charters")
@Tag(name = "Charter", description = "월세 API")
public class CharterController {

    private final CharterService charterService;

//    @GetMapping("/{dong-code}")
//    public ResponseEntity<?> getCharterController(@PathVariable("dong-code") String dongCode){
//        return ResponseEntity.ok(charterService.getCharterList(new CharterInfoDTO(dongCode)));
//    }

    @GetMapping
    public ResponseEntity<?> getCharterController(@RequestParam("dongCode") String dongCode,
                                                  @RequestParam(value = "charterKind", required = false) String charterKind,
                                                  @RequestParam(value = "depositMin", required = false) Integer depositMin,
                                                  @RequestParam(value = "depositMax", required = false) Integer depositMax,
                                                  @RequestParam(value = "rentMin", required = false) Integer rentMin,
                                                  @RequestParam(value = "rentMax", required = false) Integer rentMax,
                                                  @RequestParam("start") Integer start,
                                                  @RequestParam("limit")Integer limit,
                                                  @AuthenticationPrincipal CustomMemberDetails loginMember ){

        CharterInfoDTO charterInfoDTO = new CharterInfoDTO();
        charterInfoDTO.changeDongCode(dongCode);
        charterInfoDTO.setCharterKind(CharterKind.from(charterKind));
        charterInfoDTO.setDeposit(new DealInfoDTO(depositMin, depositMax));
        charterInfoDTO.setRent(new DealInfoDTO(rentMin,rentMax));
        charterInfoDTO.setStart(start);
        charterInfoDTO.setLimit(limit);

        return ResponseEntity.ok(charterService.getCharterList(charterInfoDTO, loginMember));
    }

    @GetMapping("/count")
    public ResponseEntity<?> getCharterResultCountController(@RequestParam("dongCode") String dongCode,
                                                  @RequestParam(value = "charterKind", required = false) String charterKind,
                                                  @RequestParam(value = "depositMin", required = false) Integer depositMin,
                                                  @RequestParam(value = "depositMax", required = false) Integer depositMax,
                                                  @RequestParam(value = "rentMin", required = false) Integer rentMin,
                                                  @RequestParam(value = "rentMax", required = false) Integer rentMax){

        CharterInfoDTO charterInfoDTO = new CharterInfoDTO();
        charterInfoDTO.changeDongCode(dongCode);
        charterInfoDTO.setCharterKind(CharterKind.from(charterKind));
        charterInfoDTO.setDeposit(new DealInfoDTO(depositMin, depositMax));
        charterInfoDTO.setRent(new DealInfoDTO(rentMin,rentMax));

        return ResponseEntity.ok(charterService.getCharterResultCount(charterInfoDTO));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getCharterByNameController(@PathVariable("name") String name){
        return ResponseEntity.ok(charterService.getCharterListByName(name));

    }

    @GetMapping("/charter-id/{charter-id}")
    public ResponseEntity<?> getCharterByNameController(@PathVariable("charter-id") Long charterId,
                                                        @AuthenticationPrincipal CustomMemberDetails loginMember){
        return ResponseEntity.ok(charterService.getCharterById(charterId, loginMember));

    }




    @GetMapping("/sido-gugun-dong")
    public ResponseEntity<?> getCharterBySidoGugunDong(@RequestParam String sido,
                                                       @RequestParam String gugun,
                                                       @RequestParam String dong,
                                                       @RequestParam String charterKind,
                                                       @RequestParam Integer start,
                                                       @RequestParam Integer limit,
                                                       @AuthenticationPrincipal CustomMemberDetails loginMember){

        CharterInfoBySidoGugunDongDTO charterInfoBySidoGugunDongDTO = new CharterInfoBySidoGugunDongDTO(sido, gugun, dong);
        charterInfoBySidoGugunDongDTO.setCharterKind(CharterKind.from(charterKind));
        charterInfoBySidoGugunDongDTO.setStart(start);
        charterInfoBySidoGugunDongDTO.setLimit(limit);

        return ResponseEntity.ok(charterService.getCharterListBySidoGugunDong(charterInfoBySidoGugunDongDTO, loginMember));
    }

    @GetMapping("/likes")
    public ResponseEntity<?> getCharterByLikes(@RequestParam String charterKind,
                                               @RequestParam Integer count){
        return ResponseEntity.ok(charterService.getCharterByLikes(charterKind, count));
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> postChaterController(@RequestPart("CharterInsertDTO") CharterInsertDTO charterInsertDTO,

                                         @Parameter(
                                                 description = "multipart/form-data 형식의 이미지를 input으로 받습니다.",
                                                 content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
                                         )
                                         @RequestPart(value = "image", required = false) MultipartFile multipartFile,
                                                  @AuthenticationPrincipal CustomMemberDetails loginMember) throws IOException {
        charterService.postCharter(charterInsertDTO, multipartFile ,loginMember);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<?> putCharterController(
            @RequestBody CharterUpdateDTO charterUpdateDTO,
            @AuthenticationPrincipal CustomMemberDetails loginMember) throws MemberException.MemberBadRequestException {
        charterService.updateCharter(charterUpdateDTO, loginMember);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/agent")
    public ResponseEntity<?> getCharterByMemberIdController(@AuthenticationPrincipal CustomMemberDetails loginMember){
        List<Charter> charterList = charterService.getCharterList(loginMember);
        return ResponseEntity.ok(charterList);
    }

    @DeleteMapping("/{charter-id}")
    public ResponseEntity<?> deleteCharter(@PathVariable("charter-id") Long charterId,
                                           @AuthenticationPrincipal CustomMemberDetails loginMember) {
        charterService.deleteCharter(charterId, loginMember);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
