package com.ssafy.thezip.common.Image.application;

import com.ssafy.thezip.common.Image.domain.Image;
import com.ssafy.thezip.common.Image.domain.ImageRepository;
import com.ssafy.thezip.common.Image.domain.ImageType;
import com.ssafy.thezip.common.Image.dto.request.ImageInsertByURLDTO;
import com.ssafy.thezip.common.Image.dto.response.ImageResponseDTO;
import com.ssafy.thezip.common.Image.exception.ImageErrorCode;
import com.ssafy.thezip.common.Image.exception.ImageException;
import com.ssafy.thezip.common.storage.application.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Transient;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final StorageService storageService;
    private final ImageRepository imageRepository;

    @Transient
    @Override
    public Image upload(MultipartFile multipartFile, String referenceId, ImageType imageType) throws IOException {
        String imageName = Image.makeImageName(multipartFile);
        Image image = Image.builder()
                .imageUrl(imageName)
                .referenceId(referenceId)
                .imageType(imageType)
                .build();

        storageService.store(multipartFile, imageType.getUrl()+"/"+imageName);
        imageRepository.save(image);
        return image;
    }


    @Override
    public void upload(ImageInsertByURLDTO imageInsertByURLDTO) {
        Image image = Image.builder()
                .imageUrl(imageInsertByURLDTO.getImageUrl())
                .referenceId(imageInsertByURLDTO.getReferenceId())
                .imageType(imageInsertByURLDTO.getImageType())
                .build();
        imageRepository.save(image);
    }


    @Override
    @Transactional
    public void delete(Long imageId) {
        Image image = imageRepository.findByImageId(imageId);
        storageService.deleteOne(image.getImageUrl());
        imageRepository.delete(imageId);
    }

    @Override
    public void delete(Image image){
        storageService.deleteOne(image.getImageUrl());
    }


    @Override
    public String getPresignedURL(Long imageId) throws ImageException.ImageBadRequestException {
//        image = Optional.ofNullable(Optional.ofNullable(imageRepository.findByImageId(Long.parseLong(key)))
//                .orElseThrow(() -> new BadRequestException.ImageBadRequestException(IMAGE_FILE_IS_NULL)));
        Image image = imageRepository.findByImageId(imageId);
        if(image == null){
            throw new ImageException.ImageBadRequestException(ImageErrorCode.CANT_NOT_FIND_IMAGE);
        }

        return image.getImageUrl();
    }

    @Override
    public Image update(MultipartFile multipartFile, Long imageId, ImageType imageType) {
        String imageName = Image.makeImageName(multipartFile);
        storageService.store(multipartFile,imageType.getUrl()+"/"+imageName);
        Image image = Image.builder()
                .imageId(imageId)
                .imageUrl(imageName)
                .build();
        image.onUpdate();
        if(imageRepository.update(image)) {
            return image;
        }
        throw new RuntimeException("fail to upload image");
    }

    @Override
    public List<ImageResponseDTO> getImage(String referenceId, ImageType imageType) {
        return imageRepository.findByReferenceId(referenceId ,imageType);
    }

//    @Override
//    public List<Long> getImageIdList(String referenceId) {
//        return imageRepository.findByImageList(referenceId);
//    }


}
