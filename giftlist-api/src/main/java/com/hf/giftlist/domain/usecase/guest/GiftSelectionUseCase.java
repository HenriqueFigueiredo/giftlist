package com.hf.giftlist.domain.usecase.guest;

import com.hf.giftlist.domain.model.guest.GiftSelection;
import com.hf.giftlist.domain.repository.guest.IGiftSelectionRepository;
import org.springframework.stereotype.Component;

@Component
public class GiftSelectionUseCase {

    private final IGiftSelectionRepository repository;

    public GiftSelectionUseCase(final IGiftSelectionRepository repository) {
        this.repository = repository;
    }

    public void select(final GiftSelection gift) {
        this.repository.select(gift);
    }
}
