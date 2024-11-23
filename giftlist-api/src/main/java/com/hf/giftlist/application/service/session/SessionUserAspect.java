package com.hf.giftlist.application.service.session;

import com.hf.giftlist.application.exception.InvalidSessionException;
import com.hf.giftlist.application.service.JweService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Aspect
@Component
public class SessionUserAspect {

    private final JweService jweService;

    public SessionUserAspect(final JweService jweService) {
        this.jweService = jweService;
    }

    @Before("@annotation(sessionUser)")
    public void beforeMethod(SessionUser sessionUser) {
        var request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        var authorizationHeader = request.getHeader(AUTHORIZATION);
        var session = this.jweService.decryptSession(authorizationHeader);

        if (sessionUser.mustBeAuthenticated() && !session.isAuthenticated()) {
            throw new InvalidSessionException("User must be authenticated.");
        }

        System.out.printf("Sessao de entrada %s", session);
        ThreadLocalSession.setSession(session);
    }

    @AfterReturning("@annotation(com.hf.giftlist.application.service.session.SessionUser)")
    public void afterMethod() {
        var response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        var session = ThreadLocalSession.getSession();
        System.out.printf("Sessao de saida %s", session);
        var authorization = this.jweService.createSession(session);
        response.setHeader(AUTHORIZATION, authorization);
    }
}
