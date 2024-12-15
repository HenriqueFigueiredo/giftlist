package com.hf.giftlist.application.repository.login;

import com.hf.giftlist.application.service.OtpService;
import com.hf.giftlist.domain.exception.SendEmailException;
import com.hf.giftlist.domain.repository.login.IOtpSenderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.concurrent.CompletableFuture;

public class OtpSenderSmtpRepository implements IOtpSenderRepository {

    private static final Logger logger = LoggerFactory.getLogger(OtpSenderSmtpRepository.class);

    private final String subjectEmail;
    private final String senderEmail;
    private final JavaMailSender javaMailSender;

    public OtpSenderSmtpRepository(String subjectEmail, String senderEmail, JavaMailSender javaMailSender) {
        this.subjectEmail = subjectEmail;
        this.senderEmail = senderEmail;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public String sendOtp(final String email) {
        var otp = OtpService.generateOTP();
        this.sendAsyncEmaio(email, otp);
        return otp;
    }

    private void sendAsyncEmaio(final String email, final String otp) {
        CompletableFuture.runAsync(() -> {
            this.sendEmail(email, otp);
        });
    }

    private void sendEmail(final String email, final String otp) {
        logger.info("Sending email to {}", email);
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(email);
            msg.setFrom(this.senderEmail);
            msg.setSubject(this.subjectEmail);
            msg.setText(otp);

            javaMailSender.send(msg);
            logger.info("Email successfully sent to {}", email);
        } catch (final Exception e) {
            logger.error("Error to send email", e);
            throw new SendEmailException(e);
        }
    }
}
