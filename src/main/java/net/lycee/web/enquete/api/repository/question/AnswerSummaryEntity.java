package net.lycee.web.enquete.api.repository.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lycee.web.enquete.api.domain.QuestionId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerSummaryEntity {
    private QuestionId questionId;
    private List<AnswerSummary> answers;
}
