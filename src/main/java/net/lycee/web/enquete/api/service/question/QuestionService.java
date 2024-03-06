package net.lycee.web.enquete.api.service.question;

import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.entity.AnswerEntity;
import net.lycee.web.enquete.api.entity.QuestionEntity;
import net.lycee.web.enquete.api.repository.question.QuestionRepository;
import net.lycee.web.enquete.api.service.space.SpaceService;
import net.lycee.web.enquete.live.LiveService;
import net.lycee.web.enquete.utils.date.LyceeDate;
import net.lycee.web.enquete.api.domain.SpaceId;
import net.lycee.web.enquete.api.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final LyceeDate lyceeDate;

    private final QuestionRepository questionRepository;

    private final SpaceService spaceService;

    private final LiveService liveService;


    @Autowired
    public QuestionService(
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


    public List<QuestionInfo> getQuestionList(UserId userId, SpaceId spaceId) {
        spaceService.readOne(userId, spaceId);

        return questionRepository.readQuestions(userId, spaceId, lyceeDate);
    }

    public QuestionId createQuestion(QuestionCreateParam createParam) {
        spaceService.readOne(createParam.userId(), createParam.spaceId());

        QuestionId questionId = questionRepository.createQuestions(new QuestionEntity(
                null,
                createParam.spaceId(),
                null,
                createParam.type(),
                createParam.description(),
                createParam.endTime(),
                createParam.answers().stream().map(
                        it -> new AnswerEntity(null, null, it, false)
                ).toList()
        ));

        liveService.noticeSpaceInfo(createParam.spaceId());

        return questionId;
    }

}
