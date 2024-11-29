package com.ssafy.thezip.board_comment.application;

import com.ssafy.thezip.board_comment.domain.BoardComment;
import com.ssafy.thezip.board_comment.domain.BoardCommentRepository;
import com.ssafy.thezip.board_comment.dto.request.BoardCommentInsertDTO;
import com.ssafy.thezip.board_comment.dto.response.BoardCommentRegisterResponseDTO;
import com.ssafy.thezip.member.domain.Member;
import com.ssafy.thezip.member.domain.MemberRepository;
import com.ssafy.thezip.member.domain.Role;
import com.ssafy.thezip.member.dto.request.CustomMemberDetails;
import com.ssafy.thezip.member.exception.MemberErrorCode;
import com.ssafy.thezip.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class BoardCommentService {

    private final BoardCommentRepository boardCommentRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public BoardCommentRegisterResponseDTO registerBoardComment(BoardCommentInsertDTO boardCommentInsertDTO,
                                     CustomMemberDetails loginMember) {
        Member member = memberRepository.findById(loginMember.getMemberId());
        BoardCommentRegisterResponseDTO responseDTO = new BoardCommentRegisterResponseDTO();
        boardCommentRepository.save(boardCommentInsertDTO, member,responseDTO);
        return responseDTO;
    }

    public List<BoardComment> getBoardCommentById(Long boardId){
        return boardCommentRepository.findByBoardId(boardId);
    }
    @Transactional
    public void deleteBoardComment(Long boardCommentId, CustomMemberDetails loginMember) throws MemberException.MemberBadRequestException {

        String role = loginMember.getAuthorities().iterator().next().getAuthority();
        BoardComment boardComment = boardCommentRepository.findByCommentId(boardCommentId);
        if(!(role.equals(Role.ADMIN.getKey()) || Objects.equals(loginMember.getMemberId(), boardComment.getMemberId()))){
            throw new MemberException.MemberBadRequestException(MemberErrorCode.ILLEGAL_ROLE);
        }
        boardCommentRepository.delete(boardCommentId);
    }


}
