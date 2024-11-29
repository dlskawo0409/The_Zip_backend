package com.ssafy.thezip.board.presentation;

import com.ssafy.thezip.board.application.BoardService;
import com.ssafy.thezip.board.domain.Board;
import com.ssafy.thezip.board.dto.request.BoardInsertDTO;
import com.ssafy.thezip.board.dto.request.BoardUpdateDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
@Tag(name = "Board", description = "게시판 API")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<?> postBoardController(@RequestBody BoardInsertDTO boardInsertDTO){
        boardService.insertBoard(boardInsertDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getBoardListController(){
        List<Board> boardList = boardService.findAll();
        return ResponseEntity.ok(boardList);
    }

    @PutMapping
    public ResponseEntity<?> putBoardController(@RequestBody BoardUpdateDTO boardUpdateDTO){
        boardService.update(boardUpdateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{board-id}")
    public ResponseEntity<?> deleteBoardController(@PathVariable("board-id")  Long boardId){
        boardService.delete(boardId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
