package com.ssafy.thezip.dormitory.domain;

public enum DormitoryKind {
    STUDENT("대학생"),
    GRADUATE("대학원"),
    FOREIGNER("외국인"),
    TEACHER("교원");

    private final String description;

    DormitoryKind(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static DormitoryKind fromDescription(String description) {
        for (DormitoryKind kind : values()) {
            if (kind.getDescription().equals(description)) {
                return kind;
            }
        }
        throw new IllegalArgumentException("Unknown description: " + description);
    }
}