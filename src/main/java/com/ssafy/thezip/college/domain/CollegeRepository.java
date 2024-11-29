package com.ssafy.thezip.college.domain;

import com.ssafy.thezip.college.dto.request.CollegeDeleteDTO;
import com.ssafy.thezip.college.dto.request.CollegeInsertDTO;
import com.ssafy.thezip.college.dto.request.CollegeUpdateDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollegeRepository {
//    void save(CollegeInsertDTO collegeInsertDTO);
    College findById(Long collegeId);
    List<College> findByName(String name);
//    void update(CollegeUpdateDTO collegeUpdateDTO);
//    void delete(CollegeDeleteDTO collegeDeleteDTO);
}
