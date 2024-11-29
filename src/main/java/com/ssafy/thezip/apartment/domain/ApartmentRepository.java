package com.ssafy.thezip.apartment.domain;

import com.ssafy.thezip.apartment.dto.request.ApartmentInfoByNameDTO;
import com.ssafy.thezip.apartment.dto.request.ApartmentInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApartmentRepository {
//    List<Apartment> selectByDongCodeWithPage(@Param("dongCode") String dongCode, @Param("start") int start, @Param("limit") int limit);
    List<Apartment> findApartmentByDongCodeWithPage(ApartmentInfoDTO apartmentInfoDTO);
    List<Apartment> findApartmentByNameWithPage(ApartmentInfoByNameDTO apartmentInfoByNameDTO);
}


