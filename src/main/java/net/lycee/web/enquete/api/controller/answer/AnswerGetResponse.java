package net.lycee.web.enquete.api.controller.answer;

import lombok.AllArgsConstructor;
import net.lycee.web.enquete.api.domain.AnswerId;

import java.util.List;

@AllArgsConstructor
public class AnswerGetResponse {
    List<AnswerInfo> answers;
    List<AnswerId> selectedAnswerId;
}
