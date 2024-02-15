package net.lycee.web.anquete.api.service.question;

import net.lycee.web.anquete.api.domain.AnswerId;

public record QuestionAnswerInfo(
        AnswerId answerId,
        Integer no,
        String description
) {
}
