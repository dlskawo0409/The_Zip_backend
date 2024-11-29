package com.ssafy.thezip.dormitory.presentation;

import com.ssafy.thezip.dormitory.application.DormitoryService;
import com.ssafy.thezip.dormitory.domain.Dormitory;
import com.ssafy.thezip.dormitory.dto.request.DormitoryInsertDTO;
import com.ssafy.thezip.dormitory.dto.request.DormitoryUpdateDTO;
import com.ssafy.thezip.dormitory.exception.DormitoryException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/dormitories")
@Tag(name = "dormitory", description = "기숙사 API")
public class DormitoryController {

    private final DormitoryService dormitoryService;

//    @PostMapping
//    @Operation(summary = "기숙사 정보 넣기", description = "기숙사 정보를 넣습니다.")
//    public ResponseEntity<?> postDormitoryController(@RequestBody DormitoryInsertDTO dormitoryInsertDTO) throws DormitoryException.DormitoryBadRequestException {
//        dormitoryService.registerDormitory(dormitoryInsertDTO);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @GetMapping("/college/{college-id}")
    @Operation(summary = "기숙사 정보 확인", description = "대학 id를 활용해 기숙사 정보를 확인합니다.")
    public ResponseEntity<?> getDormitoryByCollegeIdController(@PathVariable("college-id") Long collegeId) {
        List<Dormitory> dormitories = dormitoryService.getDormitoryByCollegeId(collegeId);
        return ResponseEntity.ok(dormitories);
    }

    @GetMapping("/id/{dormitory-id}")
    @Operation(summary = "기숙사 정보 확인", description = "id를 활용해 기숙사 정보를 확인합니다.")
    public ResponseEntity<?> getDormitoryByIdController(@PathVariable("dormitory-id") Long dormitoryId) {
        return ResponseEntity.ok(dormitoryService.getDormitoryById(dormitoryId));
    }


//    @PutMapping
//    public ResponseEntity<?> putDormitoryController(@RequestBody DormitoryUpdateDTO dormitoryUpdateDTO){
//        dormitoryService.updateDormitory(dormitoryUpdateDTO);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{dormitory-id}")
//    public ResponseEntity<?> deleteDormitoryController(@PathVariable("dormitory-id") Long dormitoryId) {
//        dormitoryService.deleteDormitory(dormitoryId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }



}
