package net.lycee.web.enquete.api.controller.answer;

import java.util.List;

public record AnswerSummaryResponse(
        List<AnswerSummaryInfo> history
) {
}
