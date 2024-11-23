package com.hf.giftlist.application.controller.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull(message = "email is required.")
        @Email(message = "email is invalid")
        String email
) {}
