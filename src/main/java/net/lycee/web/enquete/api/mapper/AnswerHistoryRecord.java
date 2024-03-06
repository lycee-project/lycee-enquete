package net.lycee.web.enquete.api.mapper;

import net.lycee.web.enquete.api.domain.AnswerId;
import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.domain.UserId;

public record AnswerHistoryRecord(
        UserId userId,
        QuestionId questionId,
        AnswerId answerId
) {
}
