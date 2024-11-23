package com.hf.giftlist.domain.repository.gift;

import com.hf.giftlist.domain.model.gift.NewGift;

public interface IGiftCreationRepository {
    void create(NewGift gift);
}
