package com.ssafy.thezip.charter.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CharterKind {
    MONTHLY_RENT("월세"),
    YEARLY_RENT("전세");


    private String key;

    CharterKind(String key){
        this.key = key;
    }

    @JsonCreator
    public static CharterKind from(String input) {
        if (input == null || input.trim().isEmpty()) {
           return null;
        }
        return Arrays.stream(values())
                .filter(charterKind -> charterKind.key.equals(input) || charterKind.name().equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 값: " + input));
    }


    @JsonValue
    public String getKey() {
        return key;
    }
}
