package com.hf.giftlist.application.controller.gift.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record GiftCreationRequest(

        @NotNull(message = "name is required.")
        @NotBlank(message = "name must not be empty.")
        @Size(min = 1, max = 100, message = "name length must be between 1 and 100.")
        String name,

        @NotNull(message = "url is required.")
        @NotBlank(message = "url must not be empty.")
        @Size(max = 100, message = "url length must be between 1 and 255.")
        @URL(message = "url invalid")
        String url
) {}
