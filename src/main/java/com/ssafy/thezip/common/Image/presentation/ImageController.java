package com.ssafy.thezip.common.Image.presentation;

import com.ssafy.thezip.common.Image.application.ImageService;
import com.ssafy.thezip.common.Image.domain.Image;
import com.ssafy.thezip.common.Image.domain.ImageType;
import com.ssafy.thezip.common.Image.dto.request.ImageInsertByURLDTO;
import com.ssafy.thezip.common.Image.dto.request.ImageInsertDTO;
import com.ssafy.thezip.common.Image.exception.ImageErrorCode;
import com.ssafy.thezip.common.Image.exception.ImageException;
import com.ssafy.thezip.common.storage.application.StorageService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
@Tag(name = "Image", description = "이미지 API")
public class ImageController {
    private final ImageService imageService;
    private final StorageService storageService;

//    @GetMapping
//    public RespsonseEntity<?> getImageByImageUrlController(
////            @Parameter(description = "imageURL", required = false)
//            @RequestParam(required = false) String imageUrl,
////            @Parameter(description = "Image Type", schema = @Schema(implementation = ImageType.class))
//            @RequestParam(required = false)  ImageType imageType,
////            @Parameter(description = "referenceId",required = false)
//            @RequestParam(required = false)  String referenceId
//            ) throws IOException {
//
//        if(imageUrl == null && referenceId == null){
//            throw new ImageException.ImageBadRequestException(ImageErrorCode.IMAGE_URL_AND_REFERENCE_ID_IS_NULL);
//        }
//
//        if(imageUrl != null && !imageUrl.startsWith("http")){
//            byte[] imageData = (byte[]) storageService.loadAsResource(imageUrl, imageType.getUrl());
//            MediaType mediaType = Image.getMediaTypeForFileName(imageUrl);
//
//            return ResponseEntity.ok()
//                    .contentType(mediaType) // 이미지 타입에 맞게 설정 (JPEG, PNG 등)
//                    .body(imageData);
//        }
//
//        if(imageType == null){
//            throw new ImageException.ImageBadRequestException(ImageErrorCode.IMAGE_TYPE_IS_NULL);
//        }
//
//        return ResponseEntity.ok(imageService.getImage(referenceId, imageType));
//    }
    @GetMapping("/apart/{image}")
    public ResponseEntity<?> getImageApartByImageUrlController(@PathVariable("image")String imageURL) throws IOException {

        if(imageURL != null && !imageURL.startsWith("http")){
            byte[] imageData = (byte[]) storageService.loadAsResource(imageURL, ImageType.APARTMENT.getUrl());
            MediaType mediaType = Image.getMediaTypeForFileName(imageURL);

            return ResponseEntity.ok()
                    .contentType(mediaType) // 이미지 타입에 맞게 설정 (JPEG, PNG 등)
                    .body(imageData);
        }

//        if(imageType == null){
//            throw new ImageException.ImageBadRequestException(ImageErrorCode.IMAGE_TYPE_IS_NULL);
//        }

        return ResponseEntity.ok(imageURL);
    }

    @GetMapping("/charter/{image}")
    public ResponseEntity<?> getImageCharterByImageUrlController(@PathVariable("image")String imageURL) throws IOException {

        if(imageURL != null && !imageURL.startsWith("http")){
            byte[] imageData = (byte[]) storageService.loadAsResource(imageURL, ImageType.CHARTER.getUrl());
            MediaType mediaType = Image.getMediaTypeForFileName(imageURL);

            return ResponseEntity.ok()
                    .contentType(mediaType) // 이미지 타입에 맞게 설정 (JPEG, PNG 등)
                    .body(imageData);
        }

//        if(imageType == null){
//            throw new ImageException.ImageBadRequestException(ImageErrorCode.IMAGE_TYPE_IS_NULL);
//        }

        return ResponseEntity.ok(imageURL);
    }


    @GetMapping("/{imageId}")
    public ResponseEntity<?> getImageByImageIdController(
            @PathVariable Long imageId,
            @Parameter(description = "Image Type", schema = @Schema(implementation = ImageType.class))
            @RequestParam ImageType imageType ) throws IOException {
        return ResponseEntity.ok(imageService.getPresignedURL(imageId));
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> registrationImageController( @Valid @RequestPart("information") ImageInsertDTO imageInsertDTO,
                                         @Parameter(
                                                 description = "multipart/form-data 형식의 이미지를 input으로 받습니다.",
                                                 content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
                                         )
                                         @RequestPart("image") MultipartFile multipartFile) throws IOException {
        imageService.upload(multipartFile,imageInsertDTO.getReferenceId(),imageInsertDTO.getImageType());
        return new ResponseEntity<>("이미지가 성공적으로 추가되었습니다.", HttpStatus.CREATED);
    }


    @PostMapping("/url")
    public ResponseEntity<?> registrationImageByURLController(@RequestBody ImageInsertByURLDTO imageInsertByURLDTO){
        imageService.upload(imageInsertByURLDTO);
        return new ResponseEntity<>("이미지가 성공적으로 추가되었습니다.", HttpStatus.CREATED);
    }

    @DeleteMapping("/{image-id}")
    public ResponseEntity<?> deleteImage(@PathVariable("image-id") Long imageId){
        imageService.delete(imageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
