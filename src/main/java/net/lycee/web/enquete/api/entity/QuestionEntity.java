package net.lycee.web.enquete.api.entity;

import java.util.List;

public record QuestionEntity(
    String spaceId,
    String questionId,
    Integer no,
    String type,
    String description,
    Long endTime,
    Boolean isEnd,
    List<AnswerEntity> answers
) {

}
