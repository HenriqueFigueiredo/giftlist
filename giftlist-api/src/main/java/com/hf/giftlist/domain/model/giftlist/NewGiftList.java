package com.hf.giftlist.domain.model.giftlist;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewGiftList {
    private String name;
    private String description;
    private String userId;
}
