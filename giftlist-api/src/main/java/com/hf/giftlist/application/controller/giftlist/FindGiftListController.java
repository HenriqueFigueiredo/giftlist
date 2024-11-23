package com.hf.giftlist.application.controller.giftlist;

import com.hf.giftlist.application.controller.giftlist.dto.DetailedGiftListResponse;
import com.hf.giftlist.application.controller.giftlist.dto.DetailedGiftResponse;
import com.hf.giftlist.application.service.session.SessionUser;
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
public class FindGiftListController {

    private final FindGiftListUseCase useCase;

    public FindGiftListController(final FindGiftListUseCase useCase) {
        this.useCase = useCase;
    }

    @SessionUser(mustBeAuthenticated = true)
    @GetMapping("/gift-list/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") String id) {
        var giftList = this.useCase.find(id);

        if (giftList.isEmpty()) {
            return ResponseEntity.status(NOT_FOUND).build();
        }

        var gifts = giftList.get().getGifts().stream().map(this::map).toList();
        var response = new DetailedGiftListResponse(giftList.get().getId(), giftList.get().getName(), giftList.get().getDescription(), gifts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private DetailedGiftResponse map(final Gift gift) {
        var guest = gift.getGuest();
        var guestName = guest != null ? guest.getName() : null;
        var guestLastname = guest != null ? guest.getLastName() : null;
        var guestEmail = guest != null ? guest.getEmail() : null;
        return new DetailedGiftResponse(gift.getId(), gift.getName(), gift.getUrl(), guestName, guestLastname, guestEmail);
    }
}
