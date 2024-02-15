package net.lycee.web.enquete.api.entity;

import lombok.Data;
import net.lycee.web.enquete.api.domain.SpaceId;
import net.lycee.web.enquete.api.domain.UserId;

@Data
public class SpaceEntity {
    private SpaceId id;
    private UserId ownerId;
    private String name;
    private Long openedTime;
    private Long closeTime;

    public static SpaceEntity of(
            SpaceId id,
            UserId ownerId,
            String name,
            Long openedTime,
            Long closeTime
    ) {
        SpaceEntity entity = new SpaceEntity();
        entity.setId(id);
        entity.setOwnerId(ownerId);
        entity.setName(name);
        entity.setOpenedTime(openedTime);
        entity.setCloseTime(closeTime);
        return entity;
    }
}
