package net.lycee.web.enquete.api.repository.question;

import net.lycee.web.enquete.api.domain.AnswerId;
import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.domain.SpaceId;
import net.lycee.web.enquete.api.domain.UserId;
import net.lycee.web.enquete.api.entity.AnswerEntity;
import net.lycee.web.enquete.api.entity.QuestionEntity;
import net.lycee.web.enquete.api.mapper.AnswerHistoryRecord;
import net.lycee.web.enquete.api.mapper.AnswerRecord;
import net.lycee.web.enquete.api.mapper.QuestionRecord;
import net.lycee.web.enquete.api.mapper.question.AnswerHistoryMapper;
import net.lycee.web.enquete.api.mapper.question.AnswerMapper;
import net.lycee.web.enquete.api.mapper.question.QuestionMapper;
import net.lycee.web.enquete.api.service.question.QuestionAnswerInfo;
import net.lycee.web.enquete.api.service.question.QuestionInfo;
import net.lycee.web.enquete.exception.InvalidIdException;
import net.lycee.web.enquete.utils.IdUtils;
import net.lycee.web.enquete.utils.date.LyceeDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    private final IdUtils idUtils;

    private final QuestionMapper questionMapper;

    private final AnswerMapper answerMapper;

    private final AnswerHistoryMapper answerHistoryMapper;

    @Autowired
    public QuestionRepositoryImpl(
            IdUtils idUtils,
            QuestionMapper questionMapper,
            AnswerMapper answerMapper,
            AnswerHistoryMapper answerHistoryMapper
    ) {
        this.questionMapper = questionMapper;
        this.idUtils = idUtils;
        this.answerMapper = answerMapper;
        this.answerHistoryMapper = answerHistoryMapper;
    }


    @Override
    public List<QuestionInfo> readQuestions(UserId userId, SpaceId spaceId, LyceeDate current) {
        List<QuestionEntity> questionList = this.questionMapper.readQuestions(
                userId,
                spaceId,
                current.getMilliseconds()
        );

        return questionList.stream()
                .map(question ->
                        new QuestionInfo(
                                question.getSpaceId(),
                                question.getQuestionId(),
                                question.getNo(),
                                question.getType(),
                                question.getDescription(),
                                question.getEndTime(),
                                question.getAnswers().stream()
                                        .map(answer ->
                                                new QuestionAnswerInfo(
                                                        answer.getAnswerId(),
                                                        answer.getNo(),
                                                        answer.getDescription(),
                                                        answer.getIsAnswered()
                                                ))
                                        .toList()
                        )
                )
                .toList();
    }

    @Override
    public QuestionId createQuestions(QuestionEntity question) {
        QuestionId questionId = idUtils.publishQuestionId();

        questionMapper.insert(new QuestionRecord(
                question.getSpaceId(),
                questionId,
                null,
                question.getType(),
                question.getDescription(),
                question.getEndTime()
        ));

        int order = 1;
        for (AnswerEntity answer : question.getAnswers()){
            AnswerId answerId = idUtils.publishAnswerId();
            AnswerRecord record = new AnswerRecord(
                    questionId,
                    answerId,
                    order++,
                    answer.getDescription()
            );
            answerMapper.insert(record);
        }
        return questionId;
    }

    @Override
    public QuestionEntity checkValidQuestion(
            UserId userId, QuestionId questionId, LyceeDate current) {
        return questionMapper.checkQuestion(userId, questionId, current.getMilliseconds())
                .orElseThrow(InvalidIdException::new);
    }

    @Override
    public void answerQuestion(QuestionId questionId, UserId userId, List<AnswerId> answers) {
        for (AnswerId answerId: answers) {
            AnswerHistoryRecord record = new AnswerHistoryRecord(
                    userId,
                    questionId,
                    answerId
            );
            answerHistoryMapper.insert(record);
        }
    }

    @Override
    public List<AnswerSummaryEntity> summaryAnswer(SpaceId spaceId) {
        return answerHistoryMapper.summaryBySpaceId(spaceId);
    }

    @Override
    public List<AnswerHistoryRecord> getHistoriesBySpaceId(UserId userId, SpaceId spaceId) {
        return answerHistoryMapper.selectByUserIdJoined(userId, spaceId);
    }
}
