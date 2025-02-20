package com.ssafy.thezip.auth.dto.response;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;


public class KakaoResponse implements OAuth2Response{
    private final Map<String, Object> attribute;
    private final Map<String, Object> properties;

    public KakaoResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
        this.properties = (Map<String, Object>) attribute.get("properties");
//        Object response = attribute.get("response");
//        if (response instanceof Map) {
//            this.attribute = (Map<String, Object>) response;
//        } else {
//            throw new IllegalArgumentException("Invalid response format");
//        }
//        System.out.println(attribute.toString());
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {

        return attribute.get("id").toString();
    }

    @Override
    public String getName() {
        return properties.get("nickname").toString();
    }

    @Override
    public String getProfile() {
        return properties.get("profile_image").toString();
    }


}
