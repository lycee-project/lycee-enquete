package net.lycee.web.enquete.api.mapper;

import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.domain.QuestionType;
import net.lycee.web.enquete.api.domain.SpaceId;

public record QuestionRecord(
        SpaceId spaceId,
        QuestionId questionId,
        Integer no,
        QuestionType type,
        String description,
        Long endTime
) {
}
