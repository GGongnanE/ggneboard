package com.ggne.ggneboard.entity;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PostTypeEnum {
    NOTICE("공지사항"),
    GENERAL("일반게시판"),
    FREE("자유게시판"),
    QNA("질문과 답변");

    private final String typeName;
    // Enum 타입을 String으로 매핑하기 위한 Map
    private static final Map<String, PostTypeEnum> TYPE_MAP =
            Stream.of(values()).collect(Collectors.toMap(PostTypeEnum::getTypeName, e -> e));

    PostTypeEnum(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    /**
     * 문자열을 Enum으로 변환하는 메소드
     * @param typeName (한글타입명)
     * @return
     */
    public static PostTypeEnum fromString(String typeName) {
        PostTypeEnum postType = TYPE_MAP.get(typeName);
        if (postType == null) {
            throw new IllegalArgumentException("Invalid Post type name: " + typeName);
        }
        return postType;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
