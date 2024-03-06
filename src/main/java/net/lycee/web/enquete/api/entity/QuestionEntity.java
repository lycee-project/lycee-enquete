package net.lycee.web.enquete.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.domain.QuestionType;
import net.lycee.web.enquete.api.domain.SpaceId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEntity {
    private QuestionId questionId;
    private SpaceId spaceId;
    private Integer no;
    private QuestionType type;
    private String description;
    private Long endTime;

    private List<AnswerEntity> answers;
}
