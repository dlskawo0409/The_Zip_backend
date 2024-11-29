package com.ssafy.thezip.common.Image.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ssafy.thezip.member.domain.Role;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum ImageType {

    PROFILE("PROFILE", "profile_image"),
    APARTMENT("APARTMENT", "apartment_image"),
    CHARTER("CHARTER", "charter_image");

    private final String key;
    private final String url;

    ImageType(String key, String url) {
        this.key = key;
        this.url = url;
    }

    @JsonValue
    public String getKey() {
        return this.key;
    }


    public String getUrl(){
        return this.url;
    }
}
