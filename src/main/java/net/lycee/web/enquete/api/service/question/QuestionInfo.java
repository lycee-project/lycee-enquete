package net.lycee.web.enquete.api.service.question;

import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.domain.SpaceId;

import java.util.List;

public record QuestionInfo(
        SpaceId spaceId,
        QuestionId questionId,
        Integer order,
        String type,
        String description,
        Long endTime,
        Boolean isEnd,
        List<QuestionAnswerInfo> answers
) {
}
