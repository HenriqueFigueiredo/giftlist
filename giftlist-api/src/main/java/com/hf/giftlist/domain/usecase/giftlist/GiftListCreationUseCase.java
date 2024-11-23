package com.hf.giftlist.domain.usecase.giftlist;

import com.hf.giftlist.domain.exception.GiftListMaxQuantityException;
import com.hf.giftlist.domain.model.giftlist.NewGiftList;
import com.hf.giftlist.domain.repository.giftlist.IGiftListByUserRepository;
import com.hf.giftlist.domain.repository.giftlist.IGiftListCreationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GiftListCreationUseCase {

    private final IGiftListCreationRepository repository;
    private final IGiftListByUserRepository findListByUserRepository;
    private final int maxQuantity;

    public GiftListCreationUseCase(final IGiftListCreationRepository repository,
                                   final IGiftListByUserRepository findListByUserRepository,
                                   final @Value("${app.giftlist.max.quantity}") int maxQuantity) {
        this.repository = repository;
        this.findListByUserRepository = findListByUserRepository;
        this.maxQuantity = maxQuantity;
    }

    public String create(final NewGiftList giftList) {
        var listQt = this.findListByUserRepository.findAll(giftList.getUserId()).size();

        if (listQt >= this.maxQuantity) {
            throw new GiftListMaxQuantityException();
        }

        return this.repository.create(giftList);
    }
}
