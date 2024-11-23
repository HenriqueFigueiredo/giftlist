package com.hf.giftlist.application.controller.guest.dto;

import java.util.List;

public record GuestGiftListResponse(String id, String name, String description, List<GuestGiftResponse> gifts) {
}
