package com.hf.giftlist.domain.usecase.gift;

import com.hf.giftlist.domain.exception.GiftListMaxSizeException;
import com.hf.giftlist.domain.model.gift.NewGift;
import com.hf.giftlist.domain.repository.gift.IGiftCreationRepository;
import com.hf.giftlist.domain.repository.giftlist.IFindGiftListRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GiftCreationUseCase {

    private final IGiftCreationRepository repository;
    private final IFindGiftListRepository findGiftListRepository;
    private final int maxListSize;


    public GiftCreationUseCase(final IGiftCreationRepository repository,
                               final IFindGiftListRepository findGiftListRepository,
                               final @Value("${app.giftlist.max.size}") int maxListSize) {
        this.repository = repository;
        this.findGiftListRepository = findGiftListRepository;
        this.maxListSize = maxListSize;
    }

    public void create(final NewGift gift) {
        var listSize = this.findGiftListRepository.find(gift.getListId())
                .orElseThrow(() -> new RuntimeException("Invalid list.")).getGifts().size();

        if (listSize >= this.maxListSize) {
            throw new GiftListMaxSizeException();
        }
        this.repository.create(gift);
    }
}
