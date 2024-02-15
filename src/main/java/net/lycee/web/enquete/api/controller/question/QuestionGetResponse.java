package net.lycee.web.enquete.api.controller.question;

import net.lycee.web.enquete.api.service.question.QuestionInfo;

import java.util.List;

public record QuestionGetResponse(
        List<QuestionInfo> questions
) {
}
