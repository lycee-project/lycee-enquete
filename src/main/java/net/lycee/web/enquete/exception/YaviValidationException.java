package net.lycee.web.enquete.exception;


import am.ik.yavi.core.ConstraintViolations;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class YaviValidationException extends QesRuntimeException {

    private final ConstraintViolations violations;

    public YaviValidationException(ConstraintViolations violations) {
        super(HttpStatus.BAD_REQUEST, "入力値エラー");
        this.violations = violations;
    }

}
