package com.ssafy.thezip.member.domain;

import com.ssafy.thezip.member.dto.response.MemberGetResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberRepository{
    Member findById(Long id);
    Member findByUsernameAll(String email);
    Member findByUsername(String username);
    boolean existsByUsername(String username);

    boolean existsByNickname(String nickname);
    void save(Member member);
    boolean update(Member member);
    void updateCollegeId(@Param("collegeId") Integer collegeId,
                         @Param("memberId") Long memberId);
}