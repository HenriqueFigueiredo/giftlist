package com.hf.giftlist.application.controller.login.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignonRequest(

        @NotNull(message = "name is required.")
        @NotBlank(message = "name must not be empty.")
        @Size(max = 100, message = "name length must be between 1 and 50.")
        String name,

        @NotNull(message = "lastname is required.")
        @NotBlank(message = "lastname must not be empty.")
        @Size(max = 100, message = "lastname length must be between 1 and 100.")
        String lastname
) {}
