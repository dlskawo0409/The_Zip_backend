package com.ssafy.thezip.member.application;

import com.ssafy.thezip.member.domain.Member;
import com.ssafy.thezip.member.domain.MemberRepository;
import com.ssafy.thezip.member.dto.request.CustomMemberDetails;
import com.ssafy.thezip.member.exception.MemberErrorCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ssafy.thezip.member.exception.MemberException;

import java.util.Optional;

import static com.ssafy.thezip.member.exception.MemberErrorCode.MEMBER_NOT_FOUND;

@Service
public class CustomMemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomMemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email);
        Member userData = Optional.ofNullable(memberRepository.findByUsername(email))
                .orElseThrow(() -> new MemberException.MemberConflictException(MemberErrorCode.MEMBER_NOT_FOUND, email));

        if (userData != null) {
            return new CustomMemberDetails(userData);
        }


        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}