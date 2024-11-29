package com.ssafy.thezip.board.application;

import com.ssafy.thezip.board.domain.Board;
import com.ssafy.thezip.board.domain.BoardRepository;
import com.ssafy.thezip.board.dto.request.BoardInsertDTO;
import com.ssafy.thezip.board.dto.request.BoardUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public void insertBoard(BoardInsertDTO boardInsertDTO){
        boardRepository.save(boardInsertDTO);
    }

    public List<Board> findAll(){
        return boardRepository.findALL();
    }

    public void update(BoardUpdateDTO boardUpdateDTO){
        if ((boardUpdateDTO.getTitle() == null || boardUpdateDTO.getTitle().trim().isEmpty()) &&
                (boardUpdateDTO.getContent() == null || boardUpdateDTO.getContent().trim().isEmpty())) {
            return;
        }
        boardRepository.update(boardUpdateDTO);
    }

    public void delete(Long boardId){
        boardRepository.delete(boardId);
    }

}
