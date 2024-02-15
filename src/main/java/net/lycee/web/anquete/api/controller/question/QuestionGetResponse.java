package net.lycee.web.anquete.api.controller.question;

import net.lycee.web.anquete.api.service.question.QuestionInfo;

import java.util.List;

public record QuestionGetResponse(
        List<QuestionInfo> questions
) {
}
