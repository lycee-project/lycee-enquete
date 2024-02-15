package net.lycee.web.enquete.api.controller;

import net.lycee.web.enquete.api.domain.AnswerId;
import net.lycee.web.enquete.api.domain.QuestionId;
import net.lycee.web.enquete.api.domain.SpaceId;
import net.lycee.web.enquete.api.domain.UserId;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        AnswerId.register(binder);
        QuestionId.register(binder);
        SpaceId.register(binder);
        UserId.register(binder);
    }

}
