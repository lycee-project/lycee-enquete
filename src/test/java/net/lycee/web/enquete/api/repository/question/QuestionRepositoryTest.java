package net.lycee.web.enquete.api.repository.question;

import net.lycee.web.enquete.api.domain.UserId;
import net.lycee.web.enquete.utils.date.LyceeFixedDate;
import net.lycee.web.enquete.api.domain.SpaceId;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
@Transactional
@Sql("/sql/init.sql")
public class QuestionRepositoryTest {
    @Autowired
    private QuestionRepositoryImpl target;

    @Nested
    public class ReadQuestionTest {

        @Test
        public void test_invalidSpace() {

            target.readQuestions(
                    new UserId("01234567-abcd-abcd-abcd-user00000001"),
                    new SpaceId("01234567-abcd-abcd-abcd-space0000001"),
                    new LyceeFixedDate("2024-03-01 14:00:01")
            );


        }
    }

}
