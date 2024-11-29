package com.ssafy.thezip.board_comment.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardCommentInsertDTO {
    private Integer boardId;
    private String content;

}
