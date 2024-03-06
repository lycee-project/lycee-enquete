package net.lycee.web.enquete.api.mapper.question;

import net.lycee.web.enquete.api.domain.SpaceId;
import net.lycee.web.enquete.api.domain.UserId;
import net.lycee.web.enquete.api.mapper.AnswerHistoryRecord;
import net.lycee.web.enquete.api.repository.question.AnswerSummaryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnswerHistoryMapper {

    void insert(
            @Param("record") AnswerHistoryRecord record);

    List<AnswerSummaryEntity> summaryBySpaceId(
            @Param("spaceId") SpaceId spaceId);

    List<AnswerHistoryRecord> selectByUserIdJoined(
            @Param("userId") UserId userId,
            @Param("spaceId") SpaceId spaceId);
}
