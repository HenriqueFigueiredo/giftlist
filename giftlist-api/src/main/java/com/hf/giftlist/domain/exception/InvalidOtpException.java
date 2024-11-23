package com.hf.giftlist.domain.exception;

public class InvalidOtpException extends RuntimeException {
    public InvalidOtpException() {
        super("Invalid OTP");
    }
}
