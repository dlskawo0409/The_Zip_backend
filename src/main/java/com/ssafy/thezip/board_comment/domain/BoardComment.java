package com.ssafy.thezip.board_comment.domain;

import com.ssafy.thezip.comment.BasicEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardComment extends BasicEntity {
    private Long commentId;
    private Long memberId;
    private String content;
    private String author;
}
