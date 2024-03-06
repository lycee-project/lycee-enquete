package net.lycee.web.enquete.api.controller.question;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.service.question.QuestionCreateParam;
import net.lycee.web.enquete.api.service.question.QuestionInfo;
import net.lycee.web.enquete.api.service.question.QuestionService;
import net.lycee.web.enquete.exception.ValidationException;
import net.lycee.web.enquete.interceptor.LyceeAuthorized;
import net.lycee.web.enquete.interceptor.RequestUser;
import net.lycee.web.enquete.api.domain.SpaceId;
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

    private final RequestUser requestUser;

    private final QuestionService questionService;


    @Autowired
    public QuestionController(
            RequestUser requestUser,
            QuestionService questionService
    ) {
        this.requestUser = requestUser;
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
     * @param bindingResult バインディング結果
     * @return 実行結果
     */
    @PostMapping("/{spaceId}")
    public ResponseEntity<String> handlePost(
            @PathVariable("spaceId") SpaceId spaceId,
            @RequestBody @Valid QuestionPostRequest request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
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

}
