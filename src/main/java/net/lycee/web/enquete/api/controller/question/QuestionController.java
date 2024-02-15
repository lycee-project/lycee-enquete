package net.lycee.web.enquete.api.controller.question;

import net.lycee.web.enquete.api.service.question.QuestionInfo;
import net.lycee.web.enquete.api.service.question.QuestionService;
import net.lycee.web.enquete.interceptor.LyceeAuthorized;
import net.lycee.web.enquete.interceptor.RequestUser;
import net.lycee.web.enquete.api.domain.SpaceId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/question")
@LyceeAuthorized
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

    @GetMapping("/{spaceId}")
    public ResponseEntity<QuestionGetResponse> handleGet(
            @PathVariable("spaceId") SpaceId spaceId
    ) {
        List<QuestionInfo> questionList = questionService.getQuestionList(requestUser.getUserId(), spaceId);

        return ResponseEntity.ok(new QuestionGetResponse(questionList));
    }




}
