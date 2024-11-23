package com.hf.giftlist.application.controller.login;

import com.hf.giftlist.application.controller.login.dto.LoginRequest;
import com.hf.giftlist.application.exception.MsgResponse;
import com.hf.giftlist.application.service.JweService;
import com.hf.giftlist.domain.model.login.Session;
import com.hf.giftlist.domain.usecase.login.LoginUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hf.giftlist.application.exception.MessageEnum.NEW_USER;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    private final JweService jweService;
    private final LoginUseCase loginUseCase;

    public LoginController(final JweService jweService, final LoginUseCase loginUseCase) {
        this.jweService = jweService;
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest request) {
        final Session session = this.loginUseCase.login(request.email());
        System.out.println(session);

        if (session.isNewUser()) {
            final MsgResponse body = new MsgResponse(NEW_USER.getCode(), NEW_USER.getMsg());
            return ResponseEntity.status(NOT_FOUND).header(AUTHORIZATION, this.jweService.createSession(session)).body(body);
        }

        return ResponseEntity.status(NO_CONTENT).header(AUTHORIZATION, this.jweService.createSession(session)).build();
    }
}
