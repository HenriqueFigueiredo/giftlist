package com.hf.giftlist.application.repository.login;

import com.hf.giftlist.application.service.OtpService;
import com.hf.giftlist.domain.exception.SendEmailException;
import com.hf.giftlist.domain.repository.login.IOtpSenderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

public class OtpSenderMockRepository implements IOtpSenderRepository {

    private static final Logger logger = LoggerFactory.getLogger(OtpSenderMockRepository.class);

    @Override
    public String sendOtp(final String email) {
        var otp = OtpService.generateOTP();
        logger.info("The email will not be sended. {}", otp);
        return otp;
    }
}
