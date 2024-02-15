package net.lycee.web.anquete.api.repository.question;

import net.lycee.web.anquete.api.service.question.QuestionInfo;
import net.lycee.web.anquete.utils.date.LyceeDate;
import net.lycee.web.anquete.api.domain.SpaceId;

import java.util.List;

public interface QuestionRepository {

    List<QuestionInfo> readQuestions(SpaceId spaceId, LyceeDate current);

}
