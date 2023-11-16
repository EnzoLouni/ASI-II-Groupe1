package com.cpe.irc5.asi2.grp1.card_manager.controller.privates;

import com.cpe.irc5.asi2.grp1.card_manager.dtos.CardDto;
import com.cpe.irc5.asi2.grp1.card_manager.service.CardModelService;
import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.MessageNotWriteableException;
import javax.validation.Valid;
import java.net.ConnectException;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/private")
public class CardPrivateController {

    private final CardModelService cardModelService;
    @PostMapping("/cards")
    public void createCardsForUser(@RequestBody @Valid UserDto newUser) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        cardModelService.createCardRequest(newUser);
    }

    @PutMapping("/cards/{cardId}")
    public void updateCard(@PathVariable(name = "cardId") Integer cardId, @RequestBody @Valid CardDto newCardDto) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        cardModelService.updateCardRequest(cardId, newCardDto);
    }
}
