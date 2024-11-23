package com.hf.giftlist.application.controller.giftlist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GiftListCreationRequest(
        @NotNull(message = "name is required.")
        @NotBlank(message = "name must not be empty.")
        @Size(min = 1, max = 100, message = "name length must be between 1 and 100.")
        String name,

        @NotNull(message = "description is required.")
        @NotBlank(message = "description must not be empty.")
        @Size(min = 1, max = 255, message = "description length must be between 1 and 255.")
        String description
) {}
