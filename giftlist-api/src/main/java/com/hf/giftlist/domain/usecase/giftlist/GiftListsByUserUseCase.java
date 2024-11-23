package com.hf.giftlist.domain.usecase.giftlist;

import com.hf.giftlist.domain.model.giftlist.SimpleGiftList;
import com.hf.giftlist.domain.repository.giftlist.IGiftListByUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GiftListsByUserUseCase {

    private final IGiftListByUserRepository repository;

    public GiftListsByUserUseCase(final IGiftListByUserRepository repository) {
        this.repository = repository;
    }

    public List<SimpleGiftList> findAll(final String userId) {
        return this.repository.findAll(userId);
    }
}
