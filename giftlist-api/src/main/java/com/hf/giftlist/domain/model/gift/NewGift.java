package com.hf.giftlist.domain.model.gift;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewGift {
    private String name;
    private String url;
    private String userId;
    private String listId;
}
