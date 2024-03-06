package net.lycee.web.enquete.api.mapper;

import net.lycee.web.enquete.api.domain.AnswerId;
import net.lycee.web.enquete.api.domain.QuestionId;

public record AnswerRecord(
        QuestionId questionId,
        AnswerId answerId,
        Integer answerNo,
        String description
) {
}
