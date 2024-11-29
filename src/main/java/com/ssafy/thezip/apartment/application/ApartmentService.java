package com.ssafy.thezip.apartment.application;

import com.ssafy.thezip.apartment.domain.Apartment;
import com.ssafy.thezip.apartment.domain.ApartmentRepository;

import com.ssafy.thezip.apartment.dto.request.ApartmentInfoByNameDTO;
import com.ssafy.thezip.apartment.dto.request.ApartmentInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    public List<Apartment> getApartmentByDongCodeWithPage(ApartmentInfoDTO apartmentInfoDTO){
        return apartmentRepository.findApartmentByDongCodeWithPage(apartmentInfoDTO);
    }

    public List<Apartment> getApartmentByNameByPage(ApartmentInfoByNameDTO apartmentInfoByNameDTO){
        return apartmentRepository.findApartmentByNameWithPage(apartmentInfoByNameDTO);
    }

}
