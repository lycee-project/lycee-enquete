package net.lycee.web.enquete.exception;

import org.springframework.http.HttpStatus;

public class NotJoinException extends QesRuntimeException {

    public NotJoinException() {
        super(HttpStatus.BAD_REQUEST, null);
    }
}
