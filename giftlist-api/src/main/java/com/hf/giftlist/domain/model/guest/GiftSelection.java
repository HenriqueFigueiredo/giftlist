package com.hf.giftlist.domain.model.guest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GiftSelection {
    private String giftId;
    private String giftListId;
    private String userId;
}
