package net.lycee.web.enquete.api.controller.answer;

import net.lycee.web.enquete.api.domain.AnswerId;
import net.lycee.web.enquete.api.domain.QuestionId;

public record AnswerInfo(
        QuestionId questionId,
        AnswerId answerId,
        Integer no,
        String description
) {
}
