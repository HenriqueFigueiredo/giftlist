package com.hf.giftlist.application.controller.gift;

import com.hf.giftlist.application.controller.gift.dto.GiftCreationRequest;
import com.hf.giftlist.application.service.session.SessionUser;
import com.hf.giftlist.application.service.session.ThreadLocalSession;
import com.hf.giftlist.domain.model.gift.NewGift;
import com.hf.giftlist.domain.usecase.gift.GiftCreationUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class GiftCreationController {

    private final GiftCreationUseCase useCase;

    public GiftCreationController(final GiftCreationUseCase useCase) {
        this.useCase = useCase;
    }

    @SessionUser(mustBeAuthenticated = true)
    @PostMapping("/gift-list/{id}/gift")
    public ResponseEntity<Void> create(@PathVariable("id") String id, @Valid @RequestBody GiftCreationRequest request) {
        var session = ThreadLocalSession.getSession();
        var gift = new NewGift(request.name(), request.url(), session.getUserId(), id);
        this.useCase.create(gift);
        return ResponseEntity.noContent().build();
    }
}
