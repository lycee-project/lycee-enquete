package net.lycee.web.enquete.api.controller.question;

import am.ik.yavi.core.ConstraintViolations;
import am.ik.yavi.core.Validator;
import am.ik.yavi.factory.ValidatorFactory;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.service.question.QuestionCreateParam;
import net.lycee.web.enquete.api.service.question.QuestionInfo;
import net.lycee.web.enquete.api.service.question.QuestionService;
import net.lycee.web.enquete.exception.ValidationException;
import net.lycee.web.enquete.exception.YaviValidationException;
import net.lycee.web.enquete.interceptor.LyceeAuthorized;
import net.lycee.web.enquete.interceptor.RequestUser;
import net.lycee.web.enquete.api.domain.SpaceId;
import net.lycee.web.enquete.utils.date.LyceeDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
@LyceeAuthorized
@Slf4j
public class QuestionController {
    private final ValidatorFactory factory;

    private final RequestUser requestUser;

    private final LyceeDate lyceeDate;

    private final QuestionService questionService;


    @Autowired
    public QuestionController(
            ValidatorFactory factory,
            RequestUser requestUser,
            LyceeDate lyceeDate,
            QuestionService questionService
    ) {
        this.factory = factory;

        this.requestUser = requestUser;
        this.lyceeDate = lyceeDate;

        this.questionService = questionService;
    }

    /**
     * 質問取得API
     * @param spaceId スペースID
     * @return 質問情報
     */
    @GetMapping("/{spaceId}")
    public ResponseEntity<QuestionGetResponse> handleGet(
            @PathVariable("spaceId") SpaceId spaceId
    ) {
        List<QuestionInfo> questionList = questionService.getQuestionList(requestUser.getUserId(), spaceId);

        return ResponseEntity.ok(new QuestionGetResponse(questionList));
    }

    /**
     * 質問登録PAI
     * @param spaceId スペースID
     * @param request 質問情報
     * @return 実行結果
     */
    @PostMapping("/{spaceId}")
    public ResponseEntity<String> handlePost(
            @PathVariable("spaceId") SpaceId spaceId,
            @RequestBody QuestionPostRequest request
    ) {
        ConstraintViolations violations = questionPostRequestValidator().validate(request);
        if (!violations.isValid()) {
            throw new YaviValidationException(violations);
        }

        QuestionId questionId = questionService.createQuestion(
            new QuestionCreateParam(
                    requestUser.getUserId(),
                    spaceId,
                    request.getType(),
                    request.getDescription(),
                    request.getEndTime(),
                    request.getAnswers()
            )
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", questionId.value())
                .body(null);
    }

    private Validator<QuestionPostRequest> questionPostRequestValidator() {
        return factory.validator(builder -> {
            return builder
                    ._object(QuestionPostRequest::getType, "type", type -> {
                        return type.notNull();
                    })
                    .constraint(QuestionPostRequest::getDescription, "description", it -> {
                        return it.notNull()
                                .lessThanOrEqual(1000);
                    })
                    .constraint(QuestionPostRequest::getEndTime, "endTime", endTime -> {
                        // FIXME: 共通化やドメイン単位での実装などが出来ないか？
                        return endTime.notNull()
                                .greaterThanOrEqual(lyceeDate.getMilliseconds())
                                .message("{0}は未来日を設定してください");
                    })
                    .constraint(QuestionPostRequest::getAnswers, "answers", answers -> {
                        // FIXME: message()が上書きできてない(propertiesのメッセージが使われてしまう)
                        return answers.notEmpty()
                                .greaterThanOrEqual(2)
                                .message("{0}は最低{1}つは必要です")
                                .lessThanOrEqual(5)
                                .message("{0}は最大{1}つまでです");
                    });
        });
    }

}
