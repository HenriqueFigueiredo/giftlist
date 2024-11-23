package com.hf.giftlist.application.controller.login;

import com.hf.giftlist.application.controller.login.dto.CodeRequest;
import com.hf.giftlist.application.controller.login.dto.OtpValidationResponse;
import com.hf.giftlist.application.exception.MsgResponse;
import com.hf.giftlist.application.service.session.SessionUser;
import com.hf.giftlist.application.service.session.ThreadLocalSession;
import com.hf.giftlist.domain.exception.InvalidOtpException;
import com.hf.giftlist.domain.usecase.login.OtpValidationUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hf.giftlist.application.exception.MessageEnum.INVALID_CODE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/api/v1")
public class OtpValidationController {

    private OtpValidationUseCase otpUseCase;

    public OtpValidationController(final OtpValidationUseCase otpUseCase) {
        this.otpUseCase = otpUseCase;
    }

    @SessionUser
    @PostMapping("/code")
    public ResponseEntity<Object> validate(@Valid @RequestBody CodeRequest codeRequest) {
        try {
            var session = ThreadLocalSession.getSession();
            this.otpUseCase.validate(session, codeRequest.code());
            return ResponseEntity.ok(new OtpValidationResponse(session.getName()));
        } catch (final InvalidOtpException ex) {
            final MsgResponse body = new MsgResponse(INVALID_CODE.getCode(), INVALID_CODE.getMsg());
            return ResponseEntity.status(UNAUTHORIZED).body(body);
        }
    }
}
