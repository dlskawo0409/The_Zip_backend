//package com.ssafy.thezip.apartment.presentation;
//
//import com.ssafy.thezip.apartment.application.ApartmentService;
//import com.ssafy.thezip.apartment.domain.Apartment;
//import com.ssafy.thezip.apartment.dto.request.ApartmentInfoDTO;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/apartments")
//@Tag(name = "Apartment", description = "부동산 거래 API")
//public class ApartmentController {
//
//    private final ApartmentService apartmentService;
//
//    @GetMapping
//    @Operation(summary = "부동산 거래 내역 페이지 확인", description = "부동산 거래 내역 페이지 확인합니다.")
//    public ResponseEntity<?> getHouseDealByDonCodeWithPageController(@ModelAttribute("ApartmentInfoDTO") ApartmentInfoDTO apartmentInfoDTO){
//        List<Apartment> result = apartmentService.getApartmentByDongCodeWithPage(apartmentInfoDTO);
//        return ResponseEntity.ok(result);
//    }
//
//}
