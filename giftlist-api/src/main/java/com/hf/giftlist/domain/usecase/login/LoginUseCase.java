package com.hf.giftlist.domain.usecase.login;

import com.hf.giftlist.domain.model.login.Session;
import com.hf.giftlist.domain.repository.login.IFindUserRepository;
import com.hf.giftlist.domain.repository.login.IOtpSenderRepository;
import org.springframework.stereotype.Component;

@Component
public class LoginUseCase {

    private final IFindUserRepository loginValidator;
    private final IOtpSenderRepository otpSender;

    public LoginUseCase(final IFindUserRepository loginValidator, final IOtpSenderRepository otpSender) {
        this.loginValidator = loginValidator;
        this.otpSender = otpSender;
    }

    public Session login(final String email) {
        var user = this.loginValidator.findByEmail(email);
        var otp = user.map(loggedUser -> this.otpSender.sendOtp(loggedUser.getEmail())).orElse(null);

        return user.isPresent() ?
                Session.builder().userId(user.get().getId()).userEmail(user.get().getEmail()).otpCode(otp).build() :
                Session.builder().userEmail(email).newUser(true).build();
    }
}
