package com.ssafy.thezip.board.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardInsertDTO {
    private String title;
    private String content;
    private String author;
}
