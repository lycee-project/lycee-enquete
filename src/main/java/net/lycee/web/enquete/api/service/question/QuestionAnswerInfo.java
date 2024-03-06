package net.lycee.web.enquete.api.service.question;

import net.lycee.web.enquete.api.domain.AnswerId;

public record QuestionAnswerInfo(
        AnswerId answerId,
        Integer no,
        String description,
        Boolean isAnswered
) {
}
