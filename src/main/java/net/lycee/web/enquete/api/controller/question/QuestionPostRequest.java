package net.lycee.web.enquete.api.controller.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import net.lycee.web.enquete.api.domain.QuestionType;

import java.util.List;

@Data
public class QuestionPostRequest {

    @NotNull
    private QuestionType type;

    @NotEmpty
    private String description;

    @NotNull
    private Long endTime;

    @NotEmpty
    @Size(min = 2)
    private List<@NotEmpty String> answers;

}
