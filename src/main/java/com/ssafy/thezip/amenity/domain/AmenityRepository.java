package com.ssafy.thezip.amenity.domain;

import com.ssafy.thezip.amenity.dto.request.AmenityInsertDTO;
import com.ssafy.thezip.amenity.dto.request.AmenityUpdateDTO;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface AmenityRepository {
    void save(AmenityInsertDTO amenityInsertDTO);
    List<Amenity> findByDormitory(Long dormitoryId);
    void update(AmenityUpdateDTO amenityUpdateDTO);
    void delete(Long AmenityId);
    Boolean existAmenity(AmenityInsertDTO  amenityInsertDTO);

}
