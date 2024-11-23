package com.hf.giftlist.domain.usecase.giftlist;

import com.hf.giftlist.domain.repository.giftlist.IGiftListDeletionRepository;
import org.springframework.stereotype.Component;

@Component
public class GiftListDeletionUseCase {

    private final IGiftListDeletionRepository repository;

    public GiftListDeletionUseCase(final IGiftListDeletionRepository repository) {
        this.repository = repository;
    }

    public void delete(final String userId, final String listId) {
        this.repository.delete(userId, listId);
    }
}
