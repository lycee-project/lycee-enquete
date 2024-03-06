package net.lycee.web.enquete.api.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import net.lycee.web.enquete.api.domain.enums.LyceeCodeEnum;

public enum QuestionType implements LyceeCodeEnum<QuestionType> {
    SINGLE("01"),
    MULTIPLE("02");

    @JsonValue
    private final String code;

    QuestionType(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static QuestionType create(String value) {
        return LyceeCodeEnum.of(QuestionType.class, value);
    }

}
