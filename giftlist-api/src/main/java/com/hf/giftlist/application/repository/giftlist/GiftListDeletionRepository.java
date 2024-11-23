package com.hf.giftlist.application.repository.giftlist;

import com.hf.giftlist.application.repository.giftlist.client.GiftListClient;
import com.hf.giftlist.domain.repository.giftlist.IGiftListDeletionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GiftListDeletionRepository implements IGiftListDeletionRepository {

    private final GiftListClient client;

    public GiftListDeletionRepository(final GiftListClient client) {
        this.client = client;
    }

    @Override
    public void delete(final String userId, final String listId) {
        var entity = client.findByIdAndUserId(listId, userId);
        entity.ifPresent(this.client::delete);
    }
}
