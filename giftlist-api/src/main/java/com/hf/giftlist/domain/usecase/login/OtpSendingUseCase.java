package com.hf.giftlist.domain.usecase.login;

import com.hf.giftlist.domain.model.login.Session;
import com.hf.giftlist.domain.repository.login.IOtpSenderRepository;
import org.springframework.stereotype.Component;

@Component
public class OtpSendingUseCase {

    private final IOtpSenderRepository otpSender;

    public OtpSendingUseCase(final IOtpSenderRepository otpSender) {
        this.otpSender = otpSender;
    }

    public void send(final Session session) {
        var otp = this.otpSender.sendOtp(session.getUserEmail());
        session.setOtpCode(otp);
    }
}
