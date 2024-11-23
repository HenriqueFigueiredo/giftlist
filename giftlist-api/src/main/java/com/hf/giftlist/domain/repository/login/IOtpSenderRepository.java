package com.hf.giftlist.domain.repository.login;

public interface IOtpSenderRepository {
    String sendOtp(String email);
}
