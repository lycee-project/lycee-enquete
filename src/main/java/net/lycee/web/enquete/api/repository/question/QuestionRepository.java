package net.lycee.web.enquete.api.repository.question;

import net.lycee.web.enquete.api.service.question.QuestionInfo;
import net.lycee.web.enquete.utils.date.LyceeDate;
import net.lycee.web.enquete.api.domain.SpaceId;

import java.util.List;

public interface QuestionRepository {

    List<QuestionInfo> readQuestions(SpaceId spaceId, LyceeDate current);

}
