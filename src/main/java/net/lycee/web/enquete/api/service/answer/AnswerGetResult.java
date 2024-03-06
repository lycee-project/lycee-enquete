package net.lycee.web.enquete.api.service.answer;

import net.lycee.web.enquete.api.domain.AnswerId;

import java.util.List;

public record AnswerGetResult (
    List<AnswerResult> answers,
    List<AnswerId> selected
) {
}
