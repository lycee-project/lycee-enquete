package net.lycee.web.anquete.utils;

import net.lycee.web.anquete.api.domain.QuestionId;
import net.lycee.web.anquete.api.domain.SpaceId;
import net.lycee.web.anquete.api.domain.UserId;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdUtils {

    private synchronized static String issueIdInternal() {
        return UUID.randomUUID().toString();
    }

    public UserId publishUserId() {
        String uuid = issueIdInternal();

        return new UserId(uuid);
    }

    public SpaceId publishSpaceId() {
        String id = issueIdInternal();
        return new SpaceId(id);
    }

    public QuestionId publishQuestionId() {
        String id = issueIdInternal();
        return new QuestionId(id);
    }

}
