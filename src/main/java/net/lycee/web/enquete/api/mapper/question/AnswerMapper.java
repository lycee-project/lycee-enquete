package net.lycee.web.enquete.api.mapper.question;

import net.lycee.web.enquete.api.mapper.AnswerRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AnswerMapper {

    void insert(@Param("record") AnswerRecord record);

}
