package net.lycee.web.enquete.api.repository.space;

import net.lycee.web.enquete.api.entity.SpaceEntity;
import net.lycee.web.enquete.api.mapper.space.SpaceMapper;
import net.lycee.web.enquete.api.domain.SpaceId;
import net.lycee.web.enquete.api.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpaceRepositoryImpl implements SpaceRepository {

    private final SpaceMapper spaceMapper;

    @Autowired
    public SpaceRepositoryImpl(SpaceMapper spaceMapper) {
        this.spaceMapper = spaceMapper;
    }

    @Override
    public List<SpaceEntity> read(UserId userId, SpaceId spaceId) {
        return spaceMapper.selectByUser(userId,
                spaceId);
    }

    @Override
    public void insert(SpaceEntity entity) {
        spaceMapper.insert(new QesSpace(
                entity.getId().value(),
                entity.getOwnerId().value(),
                entity.getName(),
                entity.getOpenedTime(),
                entity.getCloseTime()
        ));
    }

    @Override
    public boolean checkOpened(SpaceId spaceId, long currentTime) {
        return spaceMapper.selectByPKAndTime(spaceId.value(), currentTime) != null;
    }

    @Override
    public void join(UserId userId, SpaceId spaceId) {
        spaceMapper.insertJoin(userId.value(), spaceId.value());
    }
}
