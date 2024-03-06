package net.lycee.web.enquete.api.service.answer;

import net.lycee.web.enquete.api.controller.answer.AnswerSummaryInfo;
import net.lycee.web.enquete.api.domain.AnswerId;
import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.domain.SpaceId;
import net.lycee.web.enquete.api.domain.UserId;
import net.lycee.web.enquete.api.entity.QuestionEntity;
import net.lycee.web.enquete.api.mapper.AnswerHistoryRecord;
import net.lycee.web.enquete.api.repository.question.AnswerSummary;
import net.lycee.web.enquete.api.repository.question.AnswerSummaryEntity;
import net.lycee.web.enquete.api.repository.question.QuestionAnswerEntity;
import net.lycee.web.enquete.api.repository.question.QuestionRepository;
import net.lycee.web.enquete.api.service.space.SpaceService;
import net.lycee.web.enquete.live.LiveService;
import net.lycee.web.enquete.utils.date.LyceeDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    private final LyceeDate lyceeDate;

    private final QuestionRepository questionRepository;

    private final SpaceService spaceService;

    private final LiveService liveService;

    @Autowired
    public AnswerService(
            LyceeDate lyceeDate,
            QuestionRepository questionRepository,
            SpaceService spaceService,
            LiveService liveService
    ) {
        this.lyceeDate = lyceeDate;
        this.questionRepository = questionRepository;
        this.spaceService = spaceService;
        this.liveService = liveService;
    }

    public void answer(
            QuestionId questionId,
            UserId userId,
            List<AnswerId> answerList
    ) {

        QuestionEntity question = questionRepository.checkValidQuestion(userId, questionId, this.lyceeDate);

        try {
            questionRepository.answerQuestion(questionId, userId, answerList);
        } catch (DuplicateKeyException ignored) {
            // TODO: 重複した場合は，無視する(ほんとにいいの？)
        }

        // WebSocketにて通知
        liveService.noticeSpaceInfo(question.getSpaceId());
    }

    public List<AnswerSummaryInfo> summaryBySpaceId(UserId userId, SpaceId spaceId) {
        spaceService.validateOwner(userId, spaceId);

        List<AnswerSummaryEntity> answerList = questionRepository.summaryAnswer(spaceId);

        return answerList.stream()
                .collect(Collectors.toMap(
                        AnswerSummaryEntity::getQuestionId,
                        e -> e
                ))
                .values()
                .stream()
                .map(a -> {
                    Map<AnswerId, Integer> answers = a.getAnswers()
                            .stream()
                            .collect(Collectors.toMap(
                                    AnswerSummary::getAnswerId,
                                    AnswerSummary::getCounts
                            ));
                    return new AnswerSummaryInfo(
                            a.getQuestionId(),
                            answers
                    );
                }).toList();
    }

    public List<AnswerHistoryRecord> getHistoryListBySpaceId(UserId userId, SpaceId spaceId) {
        return this.questionRepository.getHistoriesBySpaceId(userId, spaceId);
    }
}
