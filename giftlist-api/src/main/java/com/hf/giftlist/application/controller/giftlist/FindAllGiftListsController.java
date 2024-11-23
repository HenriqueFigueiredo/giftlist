package com.hf.giftlist.application.controller.giftlist;

import com.hf.giftlist.application.controller.giftlist.dto.GiftListResponse;
import com.hf.giftlist.application.service.session.SessionUser;
import com.hf.giftlist.application.service.session.ThreadLocalSession;
import com.hf.giftlist.domain.usecase.giftlist.GiftListsByUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FindAllGiftListsController {

    private final GiftListsByUserUseCase useCase;

    public FindAllGiftListsController(final GiftListsByUserUseCase useCase) {
        this.useCase = useCase;
    }

    @SessionUser(mustBeAuthenticated = true)
    @GetMapping("/gift-list")
    public ResponseEntity<List<GiftListResponse>> findAll() {
        var session = ThreadLocalSession.getSession();
        var giftLists = this.useCase.findAll(session.getUserId());
        var result = giftLists.stream().map(x -> new GiftListResponse(x.getId(), x.getName(), x.getDescription())).toList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
