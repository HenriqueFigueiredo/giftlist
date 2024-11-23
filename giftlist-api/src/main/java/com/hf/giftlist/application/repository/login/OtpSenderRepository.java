package com.hf.giftlist.application.repository.login;

import com.hf.giftlist.application.service.OtpService;
import com.hf.giftlist.domain.exception.SendEmailException;
import com.hf.giftlist.domain.repository.login.IOtpSenderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Repository
public class OtpSenderRepository implements IOtpSenderRepository {

    private static final Logger logger = LoggerFactory.getLogger(OtpSenderRepository.class);

    private final String subjectEmail;
    private final String senderEmail;
    private final SesClient sesClient;

    public OtpSenderRepository(
            @Value("${aws.ses.subject-email}") final String subjectEmail,
            @Value("${aws.ses.sender-email}") final String senderEmail
    ) {
        this.sesClient = SesClient.create();
        this.subjectEmail = subjectEmail;
        this.senderEmail = senderEmail;
    }

    @Override
    public String sendOtp(final String email) {
        var otp = OtpService.generateOTP();
        this.sendEmail(email, otp);
        return otp;
    }

    private void sendEmail(final String email, final String otp) {
        logger.info("Sending email to {}", email);
        try {
            SendEmailRequest emailRequest = SendEmailRequest.builder()
                    .destination(Destination.builder()
                            .toAddresses(email)
                            .build())
                    .message(Message.builder()
                            .subject(Content.builder().data(subjectEmail).build())
                            .body(Body.builder()
                                    .text(Content.builder().data(otp).build())
                                    .build())
                            .build())
                    .source(senderEmail)
                    .build();
            sesClient.sendEmail(emailRequest);
            logger.info("Email successfully sent to {}", email);
        } catch (final SesException e) {
            logger.info("Error to send email to {}", email);
            throw new SendEmailException(e);
        }
    }
}