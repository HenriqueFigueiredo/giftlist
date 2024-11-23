package com.hf.giftlist.application.controller.guest;

import com.hf.giftlist.application.controller.guest.dto.GiftSelectionRequest;
import com.hf.giftlist.application.service.session.SessionUser;
import com.hf.giftlist.application.service.session.ThreadLocalSession;
import com.hf.giftlist.domain.model.guest.GiftSelection;
import com.hf.giftlist.domain.usecase.guest.GiftSelectionUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GuestGiftSelectionController {

    private final GiftSelectionUseCase useCase;

    public GuestGiftSelectionController(final GiftSelectionUseCase useCase) {
        this.useCase = useCase;
    }

    @SessionUser(mustBeAuthenticated = true)
    @PostMapping("/guests/select-gift")
    public ResponseEntity<Void> create(@Valid @RequestBody GiftSelectionRequest request) {
        var session = ThreadLocalSession.getSession();
        var gift = new GiftSelection(request.giftId(), request.giftListId(), session.getUserId());
        this.useCase.select(gift);
        return ResponseEntity.noContent().build();
    }
}
