package net.lycee.web.anquete.api.controller;

import net.lycee.web.anquete.api.domain.AnswerId;
import net.lycee.web.anquete.api.domain.QuestionId;
import net.lycee.web.anquete.api.domain.SpaceId;
import net.lycee.web.anquete.api.domain.UserId;
import net.lycee.web.anquete.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {
    @Autowired
    private IdUtils idUtils;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        AnswerId.register(binder);
        QuestionId.register(binder);
        SpaceId.register(binder);
        UserId.register(binder);
    }

}
