package com.hf.giftlist.application.controller.login.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CodeRequest(
        @NotNull(message = "Code is required.")
        @NotBlank(message = "Code must not be empty.")
        @Size(min = 4, max = 4, message = "Code length must be 4.")
        String code
) {}
