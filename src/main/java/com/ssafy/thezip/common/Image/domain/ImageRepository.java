package com.ssafy.thezip.common.Image.domain;

import com.ssafy.thezip.common.Image.dto.response.ImageResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;
@Mapper
public interface ImageRepository{
    Image findByImageId(Long imageId);
    boolean save(Image image);
    boolean delete(Long imageId);
    boolean update(Image image);
    List<ImageResponseDTO> findByReferenceId(@Param("referenceId") String referenceId, @Param("imageType") ImageType imageType);
//    List<Long> findByImageList(String referenceId);
}