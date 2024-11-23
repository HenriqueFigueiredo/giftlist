package com.hf.giftlist.application.controller.guest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GiftSelectionRequest(
        @NotNull(message = "giftId is required.")
        @NotBlank(message = "giftId must not be empty.")
        String giftId,

        @NotNull(message = "giftListId is required.")
        @NotBlank(message = "giftListId must not be empty.")
        String giftListId
) {}
