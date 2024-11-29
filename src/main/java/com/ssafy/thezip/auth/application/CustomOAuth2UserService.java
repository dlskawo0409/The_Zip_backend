package com.ssafy.thezip.auth.application;

import com.ssafy.thezip.auth.domain.CustomOAuth2User;
import com.ssafy.thezip.auth.dto.response.KakaoResponse;
import com.ssafy.thezip.auth.dto.response.OAuth2Response;

import com.ssafy.thezip.common.Image.domain.Image;
import com.ssafy.thezip.common.Image.domain.ImageRepository;
import com.ssafy.thezip.common.Image.domain.ImageType;
import com.ssafy.thezip.member.domain.Member;
import com.ssafy.thezip.member.domain.MemberRepository;
import com.ssafy.thezip.member.domain.Role;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

//        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }
//        else if (registrationId.equals("google")) {
//
//            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
//        }
        else {

            return null;
        }

        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬
        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        Member existData = memberRepository.findByUsernameAll(username);;


        if (existData == null) {

            Member member = Member.builder()
                    .username(username)
                    .nickname(oAuth2Response.getName())
                    .role(Role.from("ROLE_USER"))
                    .build();
            memberRepository.save(member);

            Image profile = Image.builder()
                    .imageType(ImageType.PROFILE)
                    .imageUrl(oAuth2Response.getProfile())
                    .referenceId(String.valueOf(member.getMemberId())).build();
            imageRepository.save(profile);


            return new CustomOAuth2User(member);
        }
        else {

            existData.setNickname(oAuth2Response.getName());

            Image profile = Image.builder()
                    .imageId(existData.getProfile().getImageId())
                    .imageType(ImageType.PROFILE)
                    .imageUrl(oAuth2Response.getProfile())
                    .referenceId(String.valueOf(existData.getMemberId())).build();

            imageRepository.update(profile);

            existData.setProfile(profile);


            memberRepository.update(existData);

            return new CustomOAuth2User(existData);
        }
    }

}

