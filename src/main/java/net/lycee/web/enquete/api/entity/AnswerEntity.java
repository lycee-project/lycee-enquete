package net.lycee.web.enquete.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lycee.web.enquete.api.domain.AnswerId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerEntity {
        private AnswerId answerId;
        private Integer no;
        private String description;
        private Boolean isAnswered;
}
