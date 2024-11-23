package com.hf.giftlist.application.controller.guest;

import com.hf.giftlist.application.controller.guest.dto.GuestGiftListResponse;
import com.hf.giftlist.application.controller.guest.dto.GuestGiftResponse;
import com.hf.giftlist.domain.model.giftlist.Gift;
import com.hf.giftlist.domain.usecase.giftlist.FindGiftListUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1")
public class FindGuestGiftListController {

    private final FindGiftListUseCase useCase;

    public FindGuestGiftListController(final FindGiftListUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/guests/gift-list/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") String id) {
        var giftList = this.useCase.find(id);

        if (giftList.isEmpty()) {
            return ResponseEntity.status(NOT_FOUND).build();
        }

        var gifts = giftList.get().getGifts().stream().map(this::map).toList();
        var response = new GuestGiftListResponse(giftList.get().getId(), giftList.get().getName(), giftList.get().getDescription(), gifts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private GuestGiftResponse map(Gift gift) {
        return new GuestGiftResponse(gift.getId(), gift.getName(), gift.getUrl(), gift.getGuest() == null);
    }
}
