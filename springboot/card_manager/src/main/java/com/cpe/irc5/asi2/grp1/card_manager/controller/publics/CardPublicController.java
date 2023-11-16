package com.cpe.irc5.asi2.grp1.card_manager.controller.publics;


import com.cpe.irc5.asi2.grp1.card_manager.dtos.CardDto;
import com.cpe.irc5.asi2.grp1.card_manager.service.CardModelService;
import com.cpe.irc5.asi2.grp1.card_manager.service.CardReferenceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.MessageNotWriteableException;
import javax.validation.Valid;
import java.net.ConnectException;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
//@Api(tags = {"Rest API for Managing Cards"})
public class CardPublicController {

    private final CardModelService cardModelService;
    private final CardReferenceService cardReferenceService;

    @GetMapping("/cards/{cardId}")
    public CardDto getCard(@PathVariable(name = "cardId") Integer cardId) {
        return cardModelService.getCard(cardId);
    }
    @GetMapping("/cards")
    public List<CardDto> getCards()
    {
        return cardModelService.getCards();
    }

    @GetMapping("/cards/{userId}/user")
    public List<CardDto> getCardsByUser(@PathVariable("userId") Integer userId)
    {
        return cardModelService.getCardsByUser(userId);
    }
    @GetMapping("/cards_to_sell")
    public List<CardDto> getCardsToSell()
    {
        return cardModelService.getCardsToSell();
    }
    @PutMapping("/cards/{id}/reference")
    public void updateCardReference(@PathVariable(name = "id") Integer id, @RequestBody @Valid CardDto newCardDto) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        cardReferenceService.updateCardReferenceRequest(id, newCardDto);
    }
    @PostMapping("/cards/reference")
    public void createCardReference(@RequestBody @Valid CardDto newCardDto) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        cardReferenceService.createCardReferenceRequest(newCardDto);
    }
}
