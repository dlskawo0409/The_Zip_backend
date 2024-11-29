package com.ssafy.thezip.common.Image.dto.request;

import com.ssafy.thezip.common.Image.domain.ImageType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ImageInsertByURLDTO {
    @Schema(description = "참조할 Id, member_id or apart_id", required = true, example = "1")
    private String referenceId;
    @Schema(description = "PROFILE, APARTMENT", required = true, example = "APARTMENT")
    private ImageType imageType;
    @Schema(description = "참조할 url", required = true, example = "https://file.kbland.kr/image/kbstar/land/img/alian/kms/complex/photo/objctidnfr/2810/MjgxMDE0MTgwNDMx.jpg")
    private String imageUrl;
}
