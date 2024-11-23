package com.hf.giftlist.domain.usecase.login;

import com.hf.giftlist.domain.exception.InvalidOtpException;
import com.hf.giftlist.domain.model.login.NewUser;
import com.hf.giftlist.domain.model.login.Session;
import com.hf.giftlist.domain.repository.login.IUserCreationRepository;
import org.springframework.stereotype.Component;

@Component
public class OtpValidationUseCase {

    private final IUserCreationRepository userRepository;

    public OtpValidationUseCase(final IUserCreationRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validate(final Session session, final String otpCode) {
        if (!session.getOtpCode().equals(otpCode)) {
            throw new InvalidOtpException();
        }

        if (session.isNewUser()) {
            final var newUser = new NewUser(session.getName(), session.getLastname(), session.getUserEmail());
            var createdUser = this.userRepository.create(newUser);
            session.setUserId(createdUser.getId());
        }

        session.setAuthenticated(true);
    }
}
