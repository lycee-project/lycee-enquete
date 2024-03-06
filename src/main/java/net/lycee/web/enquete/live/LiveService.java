package net.lycee.web.enquete.live;

import net.lycee.web.enquete.api.domain.SpaceId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Service;

@Service
public class LiveService {
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @Autowired
    public LiveService(
            SimpMessageSendingOperations simpMessageSendingOperations
    ) {
        this.simpMessageSendingOperations = simpMessageSendingOperations;
    }

    public void noticeSpaceInfo(SpaceId spaceId) {
        simpMessageSendingOperations.convertAndSend(
                "/notice/info/" + spaceId.value(),
                "{status: 'ok'}"
        );
    }
}
