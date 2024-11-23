package com.hf.giftlist.application.controller.giftlist;

import com.hf.giftlist.application.controller.giftlist.dto.GiftListCreationRequest;
import com.hf.giftlist.application.controller.giftlist.dto.GiftListCreationResponse;
import com.hf.giftlist.application.service.session.SessionUser;
import com.hf.giftlist.application.service.session.ThreadLocalSession;
import com.hf.giftlist.domain.model.giftlist.NewGiftList;
import com.hf.giftlist.domain.usecase.giftlist.GiftListCreationUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GiftListCreationController {

    private final GiftListCreationUseCase useCase;

    public GiftListCreationController(final GiftListCreationUseCase useCase) {
        this.useCase = useCase;
    }

    @SessionUser(mustBeAuthenticated = true)
    @PostMapping("/gift-list")
    public ResponseEntity<GiftListCreationResponse> create(@Valid @RequestBody GiftListCreationRequest request) {
        var session = ThreadLocalSession.getSession();
        var newGiftList = new NewGiftList(request.name(), request.description(), session.getUserId());
        var listId = this.useCase.create(newGiftList);
        var response = new GiftListCreationResponse(listId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
