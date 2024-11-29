package com.ssafy.thezip.dormitory.application;

import com.ssafy.thezip.dormitory.domain.Dormitory;
import com.ssafy.thezip.dormitory.domain.DormitoryRepository;
import com.ssafy.thezip.dormitory.dto.request.DormitoryInsertDTO;
import com.ssafy.thezip.dormitory.dto.request.DormitoryUpdateDTO;
import com.ssafy.thezip.dormitory.exception.DormitoryErrorCode;
import com.ssafy.thezip.dormitory.exception.DormitoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DormitoryService {

    private final DormitoryRepository dormitoryRepository;

//    public void registerDormitory(DormitoryInsertDTO dormitoryInsertDTO) throws DormitoryException.DormitoryBadRequestException {
//        Integer rent = dormitoryInsertDTO.getRent();
//        Integer yearlyRent = dormitoryInsertDTO.getYearlyRent();
//
//        if(rent == null && yearlyRent == null){
//            throw new DormitoryException.DormitoryBadRequestException(DormitoryErrorCode.ILLEGAL_RENT_AND_YEARLY_RENT_NULL);
//        }
//        if(rent == null){
//            rent = (int)Math.floor(yearlyRent/12/1000);
//            rent *= 1000;
//            dormitoryInsertDTO.setRent(rent);
//        }
//        if(yearlyRent == null){
//            dormitoryInsertDTO.setYearlyRent(rent * 12);
//        }
//
//        dormitoryKindRepository.save(dormitoryInsertDTO);
//    }

    public List<Dormitory> getDormitoryByCollegeId(Long collegeId){
        return dormitoryRepository.findByCollegeId(collegeId);
    }

    public Dormitory getDormitoryById(Long dormitoryId){
        return dormitoryRepository.findById(dormitoryId);
    }
//    public void updateDormitory(@RequestBody DormitoryUpdateDTO dormitoryUpdateDTO){
//        dormitoryKindRepository.update(dormitoryUpdateDTO);
//    }

//    public void deleteDormitory(Long dormitoryId){
//        dormitoryKindRepository.delete(dormitoryId);
//    }

}
