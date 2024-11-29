package com.ssafy.thezip.dongcode.presentation;

import com.ssafy.thezip.dongcode.application.DongCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/dongcodes")
@Tag(name = "DongCode", description = "동코드 API")
public class DongCodeController {
    private final DongCodeService dongCodeService;

    @Operation(summary = "동코드 리스트", description = "시, 군 ,구 동코드 리스트를 가지고 옵니다.")
    @GetMapping
    public ResponseEntity<?> getSidoController(@RequestParam(value = "sido" ,required = false) String sido,
                                               @RequestParam(value = "gugun",required = false)String gugun,
                                               @RequestParam(value = "dong",required = false) String dong
    ){
        System.out.println(sido) ;
        System.out.println(gugun);
        System.out.println(dong);
        if(sido == null || sido.isBlank() || sido.equals("null")){
            return ResponseEntity.ok(dongCodeService.getSido());
        }
        if(gugun == null || gugun.isBlank() || gugun.equals("null")){
            return ResponseEntity.ok(dongCodeService.getGugunBySido(sido));
        }
        if(dong == null || dong.isBlank() || dong.equals("null")){
            return ResponseEntity.ok(dongCodeService.getDongByGugun(sido, gugun));
        }
        return ResponseEntity.ok(dongCodeService.getDongCodeByDong(sido, gugun, dong));
    }

    @GetMapping("/{dong-code}")
    public ResponseEntity<?> getSidoGugunDongController(@PathVariable("dong-code") String dongCode){
        return ResponseEntity.ok(dongCodeService.getSidoGugunDong(dongCode));
    }


}
