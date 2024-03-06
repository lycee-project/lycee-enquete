package net.lycee.web.enquete.api.controller.answer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import net.lycee.web.enquete.api.domain.AnswerId;
import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.domain.SpaceId;
import net.lycee.web.enquete.api.mapper.AnswerHistoryRecord;
import net.lycee.web.enquete.api.service.answer.AnswerGetResult;
import net.lycee.web.enquete.api.service.answer.AnswerResult;
import net.lycee.web.enquete.api.service.answer.AnswerService;
import net.lycee.web.enquete.interceptor.LyceeAuthorized;
import net.lycee.web.enquete.interceptor.RequestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
@LyceeAuthorized
public class AnswerController {

    private final RequestUser requestUser;
    private final AnswerService answerService;

    @Autowired
    public AnswerController(
            RequestUser requestUser,
            AnswerService answerService
    ) {
        this.requestUser = requestUser;
        this.answerService = answerService;
    }

    /**
     * 回答登録API
     * @param questionId 質問ID
     * @param request 回答IDリスト
     * @return 実行結果
     */
    @PostMapping("/{questionId}")
    public ResponseEntity<String> handlePost(
            @PathVariable("questionId") QuestionId questionId,
            @RequestBody @Valid @NotEmpty List<AnswerId> request
    ) {
        answerService.answer(questionId, requestUser.getUserId(), request);

        return ResponseEntity.ok("ok");
    }

    /**
     * 回答全取得API
     * @return 実行結果
     */
    @GetMapping("/all")
    public ResponseEntity<AnswerAllGetResponse> handleAllGet(
            @RequestHeader("X-LYCEE-SPACE-ID") SpaceId spaceId

    ) {
        List<AnswerHistoryRecord> answerList = answerService.getHistoryListBySpaceId(requestUser.getUserId(), spaceId);

        return ResponseEntity.ok(new AnswerAllGetResponse(answerList.stream()
                .map(it -> new AnswerHistoryInfo(it.userId(), it.questionId(), List.of(it.answerId())))
                .toList())
        );
    }

    /**
     * 回答集計API
     * @param spaceId スペースID
     * @return 集計結果
     */
    @GetMapping("/summary/{spaceId}")
    public ResponseEntity<AnswerSummaryResponse> handleSummary(
            @PathVariable("spaceId") SpaceId spaceId
    ) {

        List<AnswerSummaryInfo> answerList = answerService.summaryBySpaceId(requestUser.getUserId(), spaceId);

        return ResponseEntity.ok(
                new AnswerSummaryResponse(answerList)
        );
    }


}
