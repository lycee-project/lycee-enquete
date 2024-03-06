package net.lycee.web.enquete.api.controller.answer;

import net.lycee.web.enquete.api.domain.AnswerId;
import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.domain.UserId;

import java.util.List;

public record AnswerHistoryInfo(
        UserId userId,
        QuestionId questionId,
        List<AnswerId> answers
) {
}
