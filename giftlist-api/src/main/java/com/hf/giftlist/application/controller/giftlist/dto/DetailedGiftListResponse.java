package com.hf.giftlist.application.controller.giftlist.dto;

import java.util.List;

public record DetailedGiftListResponse(String id, String name, String description, List<DetailedGiftResponse> gifts) {
}
