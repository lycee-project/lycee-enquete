package net.lycee.web.enquete.api.mapper.question;

import net.lycee.web.enquete.api.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {

    List<QuestionEntity> readQuestions(
            @Param("spaceId") String spaceId,
            @Param("current") Long current);
}