package net.lycee.web.enquete.api.repository.question;

import net.lycee.web.enquete.api.domain.AnswerId;
import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.domain.UserId;
import net.lycee.web.enquete.api.entity.QuestionEntity;
import net.lycee.web.enquete.api.mapper.AnswerHistoryRecord;
import net.lycee.web.enquete.api.service.question.QuestionInfo;
import net.lycee.web.enquete.utils.date.LyceeDate;
import net.lycee.web.enquete.api.domain.SpaceId;

import java.util.List;

public interface QuestionRepository {

    List<QuestionInfo> readQuestions(
            UserId userId,
            SpaceId spaceId,
            LyceeDate current);

    QuestionId createQuestions(QuestionEntity question);

    QuestionEntity checkValidQuestion(
            UserId userId, QuestionId questionId, LyceeDate current);

    void answerQuestion(
            QuestionId questionId,
            UserId userId,
            List<AnswerId> answers);


    List<AnswerSummaryEntity> summaryAnswer(SpaceId spaceId);

    List<AnswerHistoryRecord> getHistoriesBySpaceId(UserId userId, SpaceId spaceId);
}
