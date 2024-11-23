package com.hf.giftlist.application.repository.gift;

import com.hf.giftlist.application.repository.gift.client.GiftClient;
import com.hf.giftlist.domain.repository.gift.IGiftDeletionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GiftDeletionRepository implements IGiftDeletionRepository {

    private final GiftClient client;

    public GiftDeletionRepository(final GiftClient client) {
        this.client = client;
    }

    @Override
    public void delete(final String listId, final String giftId, final String userId) {
        this.client.deleteByIdAndUserIdAndGiftListId(giftId, userId);
    }
}
