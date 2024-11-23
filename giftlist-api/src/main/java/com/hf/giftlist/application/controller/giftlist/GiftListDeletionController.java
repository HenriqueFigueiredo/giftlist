package com.hf.giftlist.application.controller.giftlist;

import com.hf.giftlist.application.service.session.SessionUser;
import com.hf.giftlist.application.service.session.ThreadLocalSession;
import com.hf.giftlist.domain.usecase.giftlist.GiftListDeletionUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GiftListDeletionController {

    private final GiftListDeletionUseCase useCase;

    public GiftListDeletionController(final GiftListDeletionUseCase useCase) {
        this.useCase = useCase;
    }

    @SessionUser(mustBeAuthenticated = true)
    @DeleteMapping("/gift-list/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        var session = ThreadLocalSession.getSession();
        this.useCase.delete(session.getUserId(), id);
        return ResponseEntity.noContent().build();
    }
}
