package com.ssafy.thezip.common.Image.application;

import com.ssafy.thezip.common.Image.domain.Image;
import com.ssafy.thezip.common.Image.domain.ImageType;
import com.ssafy.thezip.common.Image.dto.request.ImageInsertByURLDTO;
import com.ssafy.thezip.common.Image.dto.response.ImageResponseDTO;
import com.ssafy.thezip.common.Image.exception.ImageException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    Image upload(MultipartFile multipartFile, String referenceId, ImageType imageType) throws IOException;
    void upload(ImageInsertByURLDTO imageInsertByURLDTO);
    void delete (Long imageId);
    void delete (Image image);
    String getPresignedURL(Long imageId) throws ImageException.ImageBadRequestException;
    Image update(MultipartFile multipartFile, Long imageId, ImageType imageType);
    List<ImageResponseDTO> getImage(String referenceId, ImageType imageType);
//    List<Long> getImageIdList(String referenceId);
}
