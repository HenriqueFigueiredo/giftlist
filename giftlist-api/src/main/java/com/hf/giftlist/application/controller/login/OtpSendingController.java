package com.hf.giftlist.application.controller.login;

import com.hf.giftlist.application.service.session.SessionUser;
import com.hf.giftlist.application.service.session.ThreadLocalSession;
import com.hf.giftlist.domain.usecase.login.OtpSendingUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OtpSendingController {

    private final OtpSendingUseCase otpUseCase;

    public OtpSendingController(final OtpSendingUseCase otpUseCase) {
        this.otpUseCase = otpUseCase;
    }

    @SessionUser
    @PostMapping("/send-code")
    public ResponseEntity<Void> sendCode() {
        var session = ThreadLocalSession.getSession();
        this.otpUseCase.send(session);
        return ResponseEntity.noContent().build();
    }
}
