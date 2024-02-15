package net.lycee.web.anquete.api.service.question;

import net.lycee.web.anquete.api.repository.question.QuestionRepository;
import net.lycee.web.anquete.api.service.space.SpaceService;
import net.lycee.web.anquete.utils.date.LyceeDate;
import net.lycee.web.anquete.api.domain.SpaceId;
import net.lycee.web.anquete.api.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final LyceeDate lyceeDate;

    private final QuestionRepository questionRepository;

    private final SpaceService spaceService;


    @Autowired
    public QuestionService(
            LyceeDate lyceeDate,
            QuestionRepository questionRepository,
            SpaceService spaceService
    ) {
        this.lyceeDate = lyceeDate;
        this.questionRepository = questionRepository;
        this.spaceService = spaceService;
    }


    public List<QuestionInfo> getQuestionList(UserId userId, SpaceId spaceId) {
        spaceService.readOne(userId, spaceId);

        return questionRepository.readQuestions(spaceId, lyceeDate);
    }
}
