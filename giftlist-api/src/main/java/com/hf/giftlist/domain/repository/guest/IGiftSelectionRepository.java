package com.hf.giftlist.domain.repository.guest;

import com.hf.giftlist.domain.model.guest.GiftSelection;

public interface IGiftSelectionRepository {
    void select(GiftSelection gift);
}
