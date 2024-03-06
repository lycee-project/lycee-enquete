package net.lycee.web.enquete.api.service.answer;

import net.lycee.web.enquete.api.domain.AnswerId;
import net.lycee.web.enquete.api.domain.QuestionId;

public record AnswerResult(
        QuestionId questionId,
        AnswerId answerId,
        Integer no,
        String description
) {
}
