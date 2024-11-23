package com.hf.giftlist.domain.repository.giftlist;

import com.hf.giftlist.domain.model.giftlist.NewGiftList;

public interface IGiftListCreationRepository {
    String create(NewGiftList giftList);
}
