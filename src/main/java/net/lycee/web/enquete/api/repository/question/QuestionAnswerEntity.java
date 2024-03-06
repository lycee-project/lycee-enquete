package net.lycee.web.enquete.api.repository.question;

import net.lycee.web.enquete.api.domain.AnswerId;

public record QuestionAnswerEntity(
        AnswerId answerId,
        Integer no,
        String description,
        Boolean isSelected
) {
}
