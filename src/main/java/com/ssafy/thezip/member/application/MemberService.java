package com.ssafy.thezip.member.application;

import com.ssafy.thezip.auth.jwt.JWTUtil;
import com.ssafy.thezip.common.Image.application.ImageService;
import com.ssafy.thezip.common.Image.domain.Image;
import com.ssafy.thezip.common.Image.domain.ImageType;
import com.ssafy.thezip.member.domain.Member;
import com.ssafy.thezip.member.domain.MemberRepository;
import com.ssafy.thezip.member.domain.Role;
import com.ssafy.thezip.member.dto.request.CustomMemberDetails;
import com.ssafy.thezip.member.dto.request.MemberJoinDTO;
import com.ssafy.thezip.member.dto.request.MemberUpdateDTO;
import com.ssafy.thezip.member.dto.response.MemberUpdateResponse;
import com.ssafy.thezip.member.exception.MemberErrorCode;
import com.ssafy.thezip.member.exception.MemberException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

import java.util.Optional;


import static com.ssafy.thezip.member.exception.MemberErrorCode.MEMBER_NOT_FOUND;


@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ImageService imageService;
    private final JWTUtil jwtUtil;
//    private final S3Service s3Service;


    @Transactional
    public boolean createMemberService(MemberJoinDTO memberJoinDto, MultipartFile multipartFile) throws IOException {

        checkUsernameDuplicate(memberJoinDto.getUsername());
        checkNicknameDuplicate(memberJoinDto.getNickname());

        if(memberJoinDto.getRole().equalsKeyOrName(Role.ADMIN.getKey())){
            throw new MemberException.MemberBadRequestException(MemberErrorCode.ILLEGAL_ROLE);
        }

        Member member = Member.builder()
                .username(memberJoinDto.getUsername())
                .password(bCryptPasswordEncoder.encode(memberJoinDto.getPassword()))
                .nickname(memberJoinDto.getNickname())
                .gender(memberJoinDto.getGender())
                .role(memberJoinDto.getRole())
                .build();
        memberRepository.save(member);
        Image profile = imageService.upload(multipartFile, String.valueOf(member.getMemberId()), ImageType.PROFILE);
        return true;
    }




    public Member getMemberService(CustomMemberDetails loginMember){
        return memberRepository.findById(loginMember.getMemberId());
    }


    private void checkUsernameDuplicate(String username) {
        if (memberRepository.existsByUsername(username)) {
            throw new MemberException.MemberConflictException(MemberErrorCode.MEMBER_ALREADY_EXIST, username);
        }
    }

    private void checkNicknameDuplicate(String nickname){
        if(memberRepository.existsByNickname(nickname)){
            throw new MemberException.MemberConflictException(MemberErrorCode.ILLEGAL_NICKNAME_ALREADY_EXISTS, nickname);
        }

    }


    public boolean duplicateUsernameService(String username){
        try{
            checkUsernameDuplicate(username);
        }catch(Exception e){
            return true;
        }
        return false;
    }

    public boolean duplicateNicknameService(String nickName) {
        try{
            checkNicknameDuplicate(nickName);
        }catch(Exception e){
            return true;
        }
        return false;
    }

    @Transactional
    public String updateProfile(CustomMemberDetails loginMember, MultipartFile multipartFile){
        Member member = Optional.ofNullable(loginMember.getMember())
                .orElseThrow(()->new MemberException.MemberConflictException(MEMBER_NOT_FOUND.ILLEGAL_NICKNAME_ALREADY_EXISTS, loginMember.getMember().getUsername()));

        member = memberRepository.findByUsernameAll(member.getUsername());
        Image beforeProfile = member.getProfile();
        Image profile = imageService.update(multipartFile, beforeProfile.getImageId(), beforeProfile.getImageType());
        Image image = memberRepository.findByUsername(member.getUsername()).getProfile();

        imageService.delete(image);


        // s3 이미지 삭제도 다음에 넣도록 하자! lazy 하게 해야 해서 to do로 남겨줌
        return profile.getImageUrl();
    }

    @Transactional
    public MemberUpdateResponse updateMember(MemberUpdateDTO memberUpdateDto, MultipartFile multipartFile, CustomMemberDetails loginMember) {
        Member memberBefore = Optional.ofNullable(loginMember.getMember())
                .orElseThrow(()->new MemberException.MemberConflictException(MEMBER_NOT_FOUND.ILLEGAL_NICKNAME_ALREADY_EXISTS, loginMember.getMember().getNickname()));

        memberBefore = memberRepository.findByUsernameAll(memberBefore.getUsername());

        if(!memberUpdateDto.getNickname().isEmpty() && !memberUpdateDto.getNickname().equals(memberBefore.getNickname())){
            checkNicknameDuplicate(memberUpdateDto.getNickname());
        }

        // Handle profile image if present
        if (multipartFile != null && !multipartFile.isEmpty()) {
            Image beforeImage = memberBefore.getProfile();
            Image profile = imageService.update(multipartFile, beforeImage.getImageId(), beforeImage.getImageType());
            memberBefore.setProfile(profile);
        }


        Member member = Member.builder()
                .username(memberBefore.getUsername())
                .password(memberUpdateDto.getPassword() == null || memberUpdateDto.getPassword().trim().isEmpty() ? "" : bCryptPasswordEncoder.encode(memberUpdateDto.getPassword()))
                .nickname(memberUpdateDto.getNickname() == null || memberUpdateDto.getNickname().trim().isEmpty() ? null : memberUpdateDto.getNickname())
                .gender(memberUpdateDto.getGender() == null ? null : memberUpdateDto.getGender())
                .role(memberUpdateDto.getRole() == null ? Role.USER : memberUpdateDto.getRole())
                .profile(memberBefore.getProfile())
                .build();


        System.out.println( member.toString());
        // Save the updated member
        memberRepository.update(member);
        return MemberUpdateResponse.builder()
                .nickname(member.getNickname())
                .gender(member.getGender())
                .role(member.getRole())
                .build();


    }

    public void updateMemberCollegeId(Integer collegeId, CustomMemberDetails loginMember){
        memberRepository.updateCollegeId(collegeId, loginMember.getMemberId());
    }

    public String deleteMember(CustomMemberDetails loginMember) {
        Member member = Optional.ofNullable(loginMember.getMember())
                .orElseThrow(()->new MemberException.MemberConflictException(MEMBER_NOT_FOUND.ILLEGAL_NICKNAME_ALREADY_EXISTS, loginMember.getMember().getNickname()));

        member.setDeletedAt(LocalDateTime.now());
        memberRepository.save(member);
        return "non-active";
    }

}