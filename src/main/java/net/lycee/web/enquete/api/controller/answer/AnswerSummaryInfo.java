package net.lycee.web.enquete.api.controller.answer;

import net.lycee.web.enquete.api.domain.AnswerId;
import net.lycee.web.enquete.api.domain.QuestionId;

import java.util.Map;

public record AnswerSummaryInfo(
        QuestionId questionId,
        Map<AnswerId, Integer> answers
) {
}
