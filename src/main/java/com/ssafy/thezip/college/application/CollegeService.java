package com.ssafy.thezip.college.application;

import com.ssafy.thezip.college.domain.College;
import com.ssafy.thezip.college.domain.CollegeRepository;
import com.ssafy.thezip.college.dto.request.CollegeDeleteDTO;
import com.ssafy.thezip.college.dto.request.CollegeInsertDTO;
import com.ssafy.thezip.college.dto.request.CollegeUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollegeService {

    private final CollegeRepository collegeRepository;

//    public void insertCollege(CollegeInsertDTO collegeInsertDTO){
//        collegeRepository.save(collegeInsertDTO);
//    }
    public List<College> getCollegeByName(String name){
        return collegeRepository.findByName(name);
    };

    public College getCollegeById(Long collegeId){
        return collegeRepository.findById(collegeId);
    }


//    public void updateCollege(CollegeUpdateDTO collegeUpdateDTO){
//        collegeRepository.update(collegeUpdateDTO);
//    }
//    public void deleteCollege(CollegeDeleteDTO collegeDeleteDTO){
//        collegeRepository.delete(collegeDeleteDTO);
//    }
}
