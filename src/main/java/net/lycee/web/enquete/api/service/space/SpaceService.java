package net.lycee.web.enquete.api.service.space;

import lombok.extern.slf4j.Slf4j;
import net.lycee.web.enquete.api.controller.space.SpaceInfo;
import net.lycee.web.enquete.api.entity.SpaceEntity;
import net.lycee.web.enquete.api.repository.space.SpaceRepository;
import net.lycee.web.enquete.exception.NotJoinException;
import net.lycee.web.enquete.exception.QesRuntimeException;
import net.lycee.web.enquete.utils.IdUtils;
import net.lycee.web.enquete.utils.LyceeConstants;
import net.lycee.web.enquete.utils.date.LyceeDate;
import net.lycee.web.enquete.api.domain.SpaceId;
import net.lycee.web.enquete.api.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SpaceService {

    private final SpaceRepository spaceRepository;
    private final LyceeDate lyceeDate;
    private final IdUtils idUtils;

    @Autowired
    public SpaceService(
            SpaceRepository spaceRepository,
            LyceeDate lyceeDate,
            IdUtils idUtils
    ) {
        this.spaceRepository = spaceRepository;
        this.lyceeDate = lyceeDate;
        this.idUtils = idUtils;
    }


    public LyceeConstants.JoinResult join(
            UserId userId,
            SpaceId spaceId) {
        long current = lyceeDate.getMilliseconds();

        boolean isAvailable = spaceRepository.checkOpened(spaceId, current);
        if (!isAvailable) {
            return LyceeConstants.JoinResult.SpaceClosed;
        }

        try {
            spaceRepository.join(userId, spaceId);
        } catch (DuplicateKeyException e) {
            return LyceeConstants.JoinResult.AlreadyJoined;
        }

        return LyceeConstants.JoinResult.Joined;
    }

    public List<SpaceInfo> readAll(UserId userId) {
        return spaceRepository.read(userId, null)
                .stream().map(it ->
                        new SpaceInfo(
                                it.getId(),
                                it.getOwnerId(),
                                it.getName(),
                                it.getOpenedTime(),
                                it.getCloseTime()
                        )
                )
                .toList();
    }

    /**
     * 参加しているスペース情報の取得
     * @param userId ユーザID
     * @param spaceId スペースID
     * @return スペース情報
     */
    public SpaceInfo readOne(UserId userId, SpaceId spaceId) {
        List<SpaceEntity> spaceList = spaceRepository.read(userId, spaceId);

        if (spaceList.isEmpty()) {
            throw new NotJoinException();
        }
        SpaceEntity space = spaceList.get(0);
        return new SpaceInfo(
                space.getId(),
                space.getOwnerId(),
                space.getName(),
                space.getOpenedTime(),
                space.getCloseTime()
        );
    }

    /**
     * スペース情報の登録
     * @param registerDto スペース情報
     * @return 登録スペースID
     */
    public SpaceId register(SpaceRegisterDto registerDto) {
        SpaceId spaceId = idUtils.publishSpaceId();
        SpaceEntity entity = SpaceEntity.of(
                spaceId,
                registerDto.userId(),
                registerDto.name(),
                lyceeDate.getMilliseconds(),
                registerDto.closeTime()
        );

        spaceRepository.insert(entity);

        // 登録者自身もスペースに参加しないといけない
        spaceRepository.join(registerDto.userId(), spaceId);

        return spaceId;
    }

    public void validateOwner(UserId userId, SpaceId spaceId) {
        SpaceInfo space = readOne(userId, spaceId);

        if (!space.ownerId().equals(userId)) {
            throw new QesRuntimeException(
                    HttpStatus.FORBIDDEN,
                    "スペースオーナーではありません"
            );
        }

    }
}
