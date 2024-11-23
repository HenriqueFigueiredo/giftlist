package com.hf.giftlist.domain.usecase.giftlist;

import com.hf.giftlist.domain.model.giftlist.GiftList;
import com.hf.giftlist.domain.repository.giftlist.IFindGiftListRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindGiftListUseCase {

    private final IFindGiftListRepository repository;

    public FindGiftListUseCase(final IFindGiftListRepository repository) {
        this.repository = repository;
    }

    public Optional<GiftList> find(final String listId) {
        return this.repository.find(listId);
    }
}
