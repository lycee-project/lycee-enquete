package net.lycee.web.enquete.api.service.question;

import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.domain.QuestionType;
import net.lycee.web.enquete.api.domain.SpaceId;

import java.util.List;

public record QuestionInfo(
        SpaceId spaceId,
        QuestionId questionId,
        Integer order,
        QuestionType type,
        String description,
        Long endTime,
        List<QuestionAnswerInfo> answers
) {
}
