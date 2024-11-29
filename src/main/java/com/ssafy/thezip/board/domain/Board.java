package com.ssafy.thezip.board.domain;

import com.ssafy.thezip.comment.BasicEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board extends BasicEntity {
    private Integer boardId;
    private String title;
    private String content;
    private String author;
    private Integer views;

}
