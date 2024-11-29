package com.ssafy.thezip.college.presentation;

import com.ssafy.thezip.college.application.CollegeService;
import com.ssafy.thezip.college.domain.College;
import com.ssafy.thezip.college.dto.request.CollegeDeleteDTO;
import com.ssafy.thezip.college.dto.request.CollegeInsertDTO;
import com.ssafy.thezip.college.dto.request.CollegeUpdateDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.websocket.servlet.UndertowWebSocketServletWebServerCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/colleges")
@Tag(name = "College", description = "대학 API")
public class CollegeController {

    private final CollegeService collegeService;

//    @PostMapping
//    public ResponseEntity<?> registrationCollegeController(@RequestBody CollegeInsertDTO collegeInsertDTO){
//        collegeService.insertCollege(collegeInsertDTO);
//        return new ResponseEntity<>("대학이 성공적으로 추가되었습니다.", HttpStatus.CREATED);
//    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getCollegeByNameController(@PathVariable("name")String name){
        List<College> collegeList = collegeService.getCollegeByName(name);
        return ResponseEntity.ok(collegeList);
    }

    @GetMapping("/id/{college-id}")
    public ResponseEntity<?> getCollegeByIdController(@PathVariable("college-id") Long collegeId){
        return ResponseEntity.ok(collegeService.getCollegeById(collegeId));
    }


//
//    @PutMapping
//    public ResponseEntity<?> updateCollegeController(@RequestBody CollegeUpdateDTO collegeUpdateDTO){
//        collegeService.updateCollege(collegeUpdateDTO);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping
//    public ResponseEntity<?> deleteCollegeController(@RequestBody CollegeDeleteDTO collegeDeleteDTO){
//        collegeService.deleteCollege(collegeDeleteDTO);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
