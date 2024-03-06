package net.lycee.web.enquete.api.controller.answer;

import net.lycee.web.enquete.api.mapper.AnswerHistoryRecord;

import java.util.List;

public record AnswerAllGetResponse(
    List<AnswerHistoryInfo> history
) {
}
