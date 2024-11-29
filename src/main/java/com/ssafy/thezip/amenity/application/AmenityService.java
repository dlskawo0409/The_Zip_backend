package com.ssafy.thezip.amenity.application;

import com.ssafy.thezip.amenity.domain.Amenity;
import com.ssafy.thezip.amenity.domain.AmenityRepository;
import com.ssafy.thezip.amenity.dto.request.AmenityInsertDTO;
import com.ssafy.thezip.amenity.dto.request.AmenityUpdateDTO;
import com.ssafy.thezip.amenity.exception.AmenityException;
import com.ssafy.thezip.interest_area.exception.InterestAreaErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AmenityService {

    private final AmenityRepository amenityRepository;

    public void insertAmenity(AmenityInsertDTO amenityInsertDTO){

        if(amenityRepository.existAmenity(amenityInsertDTO)){
            throw new AmenityException.AmenityConflictException(InterestAreaErrorCode.DUPLICATE_DONG_CODE, amenityInsertDTO.getName());
        }
        amenityRepository.save(amenityInsertDTO);
    }

    public List<Amenity> getAmenityList(Long dormitoryId){
      return amenityRepository.findByDormitory(dormitoryId);
    }

    public void updateAmenity(AmenityUpdateDTO amenityUpdateDTO){
        amenityRepository.update(amenityUpdateDTO);
    }

    public void deleteAmenity(Long amenityId){
        amenityRepository.delete(amenityId);
    }

}
