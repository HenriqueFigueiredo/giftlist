package com.hf.giftlist.application.controller.gift;

import com.hf.giftlist.application.service.session.SessionUser;
import com.hf.giftlist.application.service.session.ThreadLocalSession;
import com.hf.giftlist.domain.usecase.gift.GiftDeletionUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GiftDeletionController {

    private final GiftDeletionUseCase useCase;

    public GiftDeletionController(final GiftDeletionUseCase useCase) {
        this.useCase = useCase;
    }

    @SessionUser(mustBeAuthenticated = true)
    @DeleteMapping("/gift-list/{listId}/gift/{giftId}")
    public ResponseEntity<Void> create(@PathVariable("listId") String listId, @PathVariable("giftId") String giftId) {
        var session = ThreadLocalSession.getSession();
        this.useCase.delete(listId, giftId, session.getUserId());
        return ResponseEntity.noContent().build();
    }
}
