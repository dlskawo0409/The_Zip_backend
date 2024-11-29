package com.ssafy.thezip.board.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateDTO {
    private Long boardId;
    private String title;
    private String content;
}
