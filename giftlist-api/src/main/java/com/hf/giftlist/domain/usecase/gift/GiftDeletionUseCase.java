package com.hf.giftlist.domain.usecase.gift;

import com.hf.giftlist.domain.repository.gift.IGiftDeletionRepository;
import org.springframework.stereotype.Component;

@Component
public class GiftDeletionUseCase {
    private final IGiftDeletionRepository repository;

    public GiftDeletionUseCase(final IGiftDeletionRepository repository) {
        this.repository = repository;
    }

    public void delete(final String listId, final String giftId, final String userId) {
        this.repository.delete(listId, giftId, userId);
    }
}
