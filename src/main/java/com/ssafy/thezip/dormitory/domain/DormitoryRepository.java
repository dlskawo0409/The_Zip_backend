package com.ssafy.thezip.dormitory.domain;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DormitoryRepository {
//    void save(DormitoryInsertDTO dormitoryInsertDTO);
    List<Dormitory> findByCollegeId(Long collegeId);
    Dormitory findById(Long dormitory);
//    void update(DormitoryUpdateDTO dormitoryUpdateDTO);
//    void delete(Long dormitoryId);
}
