package com.hf.giftlist.application.config;

import com.hf.giftlist.application.repository.login.OtpSenderMockRepository;
import com.hf.giftlist.application.repository.login.OtpSenderSeSRepository;
import com.hf.giftlist.application.repository.login.OtpSenderSmtpRepository;
import com.hf.giftlist.domain.repository.login.IOtpSenderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class EmailProviderConfig {

    private static final Logger logger = LoggerFactory.getLogger(EmailProviderConfig.class);

    @Value("${otp.subject-email}")
    private String subjectEmail;

    @Value("${otp.sender-email}")
    private String senderEmail;

    @Value("${otp.mail-provider}")
    private String provider;

    @Autowired
    private JavaMailSender javaMailSender;

    @Bean
    public IOtpSenderRepository emailProvider() {
        logger.info("Mail provider initialization...");
        if (this.provider.equalsIgnoreCase("ses")) {
            logger.info("Mail provider SES will be initialized...");
            return new OtpSenderSeSRepository(subjectEmail, senderEmail);
        } else if (this.provider.equalsIgnoreCase("smtp")) {
            logger.info("Mail provider SMTP will be initialized...");
            return new OtpSenderSmtpRepository(subjectEmail, senderEmail, javaMailSender);
        } else {
            logger.info("Mail provider MOCK will be initialized...");
            return new OtpSenderMockRepository();
        }
    }
}
