package com.ssafy.thezip.board.domain;

import com.ssafy.thezip.board.dto.request.BoardInsertDTO;
import com.ssafy.thezip.board.dto.request.BoardUpdateDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardRepository {
    void save(BoardInsertDTO boardInsertDTO);
    List<Board> findALL();
    void update(BoardUpdateDTO boardUpdateDTO);
    void delete(Long boardId);

}
