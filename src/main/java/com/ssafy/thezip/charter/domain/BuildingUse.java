package com.ssafy.thezip.charter.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ssafy.thezip.member.domain.Role;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum BuildingUse {
    OFFICE("오피스텔"),
    SINGLE_FAMILY("단독다가구"),
    APARTMENT("아파트"),
    MULTI_FAMILY("연립다세대");

    private final String key;

    BuildingUse(String key) {
        this.key = key;
    }
    @JsonCreator
    public static BuildingUse from(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null; // 또는 기본 값 설정
        }
        return Arrays.stream(values())
                .filter(buildingUse -> buildingUse.key.equalsIgnoreCase(input) || buildingUse.name().equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 역할 키: " + input));
    }

    @JsonValue
    public String getKey() {
        return key;
    }

}
