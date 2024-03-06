package net.lycee.web.enquete.exception;

import org.springframework.http.HttpStatus;

public class InvalidIdException extends QesRuntimeException {

    public InvalidIdException() {
        super(HttpStatus.BAD_REQUEST, null);
    }
}
