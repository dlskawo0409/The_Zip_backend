package com.ssafy.thezip.board_comment.domain;

import com.ssafy.thezip.board_comment.dto.request.BoardCommentInsertDTO;
import com.ssafy.thezip.board_comment.dto.response.BoardCommentRegisterResponseDTO;
import com.ssafy.thezip.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardCommentRepository {

    void save(@Param("boardCommentInsertDTO") BoardCommentInsertDTO boardCommentInsertDTO,
              @Param("member") Member member,
              @Param("boardCommentRegisterResponseDTO") BoardCommentRegisterResponseDTO boardCommentRegisterResponseDTO);

    List<BoardComment> findByBoardId(Long boardId);
    BoardComment findByCommentId(Long commentId);
    void delete(Long boardCommentId);

}
