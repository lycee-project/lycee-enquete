package net.lycee.web.enquete.api.controller.space;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.ConstraintViolations;
import am.ik.yavi.core.Validator;
import am.ik.yavi.factory.ValidatorFactory;
import am.ik.yavi.message.MessageSourceMessageFormatter;
import net.lycee.web.enquete.exception.YaviValidationException;
import net.lycee.web.enquete.interceptor.LyceeAuthorized;
import net.lycee.web.enquete.api.service.space.SpaceService;
import net.lycee.web.enquete.api.service.space.SpaceRegisterDto;
import net.lycee.web.enquete.utils.LyceeConstants;
import net.lycee.web.enquete.interceptor.RequestUser;
import net.lycee.web.enquete.api.domain.SpaceId;
import net.lycee.web.enquete.utils.date.LyceeDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

@RestController
@RequestMapping("/api/space")
@LyceeAuthorized
public class SpaceController {

    private Validator<SpacePostRequest> spacePostRequestValidator() {
        return factory.validator(builder -> {
            return builder
                    .constraint(SpacePostRequest::getName, "name", it -> {
                        return it.notNull()
                                .greaterThanOrEqual(3)
                                .lessThanOrEqual(100);
                    })
                    .constraint(SpacePostRequest::getCloseTime, "closeTime", it -> {
                        return it.notNull()
                                .greaterThan(lyceeDate.getMilliseconds()).message(
                                        "{0}は未来日を設定してください"
                                );
                    });
        });
    }
    private final ValidatorFactory factory;
    private final RequestUser requestUser;
    private final LyceeDate lyceeDate;

    private final SpaceService spaceService;

    @Autowired
    public SpaceController(
            ValidatorFactory factory,
            RequestUser user,
            LyceeDate lyceeDate,
            SpaceService spaceService) {
        this.factory = factory;
        this.requestUser = user;
        this.lyceeDate = lyceeDate;
        this.spaceService = spaceService;
    }


    /**
     * スペース一覧取得API
     * @return スペース一覧
     */
    @GetMapping()
    public ResponseEntity<SpaceGetListResponse> handleGetList() {

        var spaceList = spaceService.readAll(requestUser.getUserId());

        return ResponseEntity.ok(
                new SpaceGetListResponse(spaceList)
        );
    }

    /**
     * スペース情報取得API
     * @param spaceId 対象スペースID
     * @return スペース情報
     */
    @GetMapping("/{spaceId}")
    public ResponseEntity<SpaceGetOneResponse> handleGetOne(
            @PathVariable("spaceId") SpaceId spaceId
    ) {
        var info = spaceService.readOne(requestUser.getUserId(), spaceId);

        return ResponseEntity.ok(
                new SpaceGetOneResponse(
                        info.id(),
                        info.ownerId(),
                        info.name(),
                        info.openedTime(),
                        info.closeTime()
                )
        );
    }


    /**
     * スペース登録API
     * @param request スペース情報
     * @return スペースID
     */
    @PostMapping
    public ResponseEntity<SpacePostResponse> handlePost(
            @RequestBody SpacePostRequest request
    ) {
        ConstraintViolations violations = spacePostRequestValidator().validate(request);
        if (!violations.isValid()) {
            throw new YaviValidationException(violations);
        }

        SpaceRegisterDto registerDto = new SpaceRegisterDto(
                requestUser.getUserId(),
                request.getName(),
                request.getCloseTime()
        );

        SpaceId spaceId = spaceService.register(registerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new SpacePostResponse(spaceId));
    }

    /**
     * スペース参加API
     * @param spaceId 参加するスペースID
     * @return 無し
     */
    @PostMapping("/join/{spaceId}")
    public ResponseEntity<String> handleJoin(
            @PathVariable("spaceId") SpaceId spaceId
    ) {
        LyceeConstants.JoinResult result = spaceService.join(
                requestUser.getUserId(),
                spaceId
        );

        return ResponseEntity.status(switch (result) {
            case Joined -> HttpStatus.OK;
            case AlreadyJoined -> HttpStatus.NO_CONTENT;
            case SpaceClosed -> HttpStatus.GONE;
        }).body(null);
    }

}
