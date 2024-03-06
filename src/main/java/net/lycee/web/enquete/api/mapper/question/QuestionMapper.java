package net.lycee.web.enquete.api.mapper.question;

import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.domain.SpaceId;
import net.lycee.web.enquete.api.domain.UserId;
import net.lycee.web.enquete.api.entity.QuestionEntity;
import net.lycee.web.enquete.api.mapper.QuestionRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface QuestionMapper {

    List<QuestionEntity> readQuestions(
            @Param("userId") UserId userId,
            @Param("spaceId") SpaceId spaceId,
            @Param("current") Long current);

    void insert(@Param("record") QuestionRecord record);

    Optional<QuestionEntity> checkQuestion(
            @Param("userId") UserId userId,
            @Param("questionId") QuestionId questionId,
            @Param("current") Long current
    );
}
