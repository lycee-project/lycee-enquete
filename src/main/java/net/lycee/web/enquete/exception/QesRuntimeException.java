package net.lycee.web.enquete.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class QesRuntimeException extends RuntimeException {

    private final HttpStatus responseStatus;

    public QesRuntimeException(HttpStatus status, String message) {
        super(message);
        this.responseStatus = status;
    }

}
