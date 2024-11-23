package com.hf.giftlist.domain.exception;

public class SendEmailException extends RuntimeException {
    public SendEmailException(final Throwable tw) {
        super("Error sending email:", tw);
    }
}
