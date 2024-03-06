package net.lycee.web.enquete.api.repository.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lycee.web.enquete.api.domain.AnswerId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerSummary {
    private AnswerId answerId;
    private Integer counts;
}
