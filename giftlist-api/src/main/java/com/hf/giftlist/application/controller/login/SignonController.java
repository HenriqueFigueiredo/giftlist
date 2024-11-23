package com.hf.giftlist.application.controller.login;

import com.hf.giftlist.application.controller.login.dto.SignonRequest;
import com.hf.giftlist.application.service.session.SessionUser;
import com.hf.giftlist.application.service.session.ThreadLocalSession;
import com.hf.giftlist.domain.usecase.login.OtpSendingUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SignonController {

    private final OtpSendingUseCase otpUseCase;

    public SignonController(final OtpSendingUseCase otpUseCase) {
        this.otpUseCase = otpUseCase;
    }

    @SessionUser
    @PostMapping("/signon")
    public ResponseEntity<Void> signon(@Valid @RequestBody SignonRequest request) {
        var session = ThreadLocalSession.getSession();
        session.setName(request.name());
        session.setLastname(request.lastname());
        this.otpUseCase.send(session);
        return ResponseEntity.noContent().build();
    }
}
