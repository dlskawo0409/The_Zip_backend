package com.ssafy.thezip.board_comment.presentation;

import com.ssafy.thezip.board.application.BoardService;
import com.ssafy.thezip.board_comment.application.BoardCommentService;
import com.ssafy.thezip.board_comment.domain.BoardComment;
import com.ssafy.thezip.board_comment.dto.request.BoardCommentInsertDTO;
import com.ssafy.thezip.member.dto.request.CustomMemberDetails;
import com.ssafy.thezip.member.exception.MemberException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards/comments")
@Tag(name = "BoardComment ", description = "게시판 댓글 API")
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @GetMapping("/{board-id}")
    public ResponseEntity<?> getBoardCommentController(@PathVariable("board-id") Long boardId) {
        List<BoardComment> boardCommentList = boardCommentService.getBoardCommentById(boardId);
        return ResponseEntity.ok(boardCommentList);
    }

    @PostMapping
    public ResponseEntity<?> postBoardCommentController(@RequestBody BoardCommentInsertDTO boardCommentInsertDTO,
                                                        @AuthenticationPrincipal CustomMemberDetails loginMember) throws MemberException.MemberBadRequestException {

        return ResponseEntity.ok(boardCommentService.registerBoardComment(boardCommentInsertDTO,loginMember));
    }

    @DeleteMapping("/{board-comment-id}")
    public ResponseEntity<?> deleteBoardCommentController(@PathVariable("board-comment-id") Long boardCommentId,
                                                          @AuthenticationPrincipal CustomMemberDetails loginMember) throws MemberException.MemberBadRequestException {
        boardCommentService.deleteBoardComment(boardCommentId, loginMember);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
