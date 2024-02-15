package net.lycee.web.enquete.api.repository.question;

import net.lycee.web.enquete.api.entity.QuestionEntity;
import net.lycee.web.enquete.api.mapper.question.QuestionMapper;
import net.lycee.web.enquete.api.service.question.QuestionAnswerInfo;
import net.lycee.web.enquete.api.service.question.QuestionInfo;
import net.lycee.web.enquete.utils.date.LyceeDate;
import net.lycee.web.enquete.api.domain.AnswerId;
import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.domain.SpaceId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionRepositoryImpl(
            QuestionMapper questionMapper
    ) {
        this.questionMapper = questionMapper;
    }


    @Override
    public List<QuestionInfo> readQuestions(SpaceId spaceId, LyceeDate current) {
        List<QuestionEntity> questionList = this.questionMapper.readQuestions(
                spaceId.value(),
                current.getMilliseconds()
        );

        return questionList.stream()
                .map(e ->
                    new QuestionInfo(
                            new SpaceId(e.spaceId()),
                            new QuestionId(e.questionId()),
                            e.no(),
                            e.type(),
                            e.description(),
                            e.endTime(),
                            e.isEnd(),
                            e.answers().stream()
                                    .map(a ->
                                            new QuestionAnswerInfo(
                                                    new AnswerId(a.answerId()),
                                                    a.no(),
                                                    a.description()
                                            ))
                                    .toList()
                    )
                )
                .toList();
    }
}
