package com.cpe.irc5.asi2.grp1.card_manager.client;

import com.cpe.irc5.asi2.grp1.card_manager.dtos.CardDto;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "cardClient", url = "${card.ribbon.listOfServers}/cardapi")
public interface CardClient {
    @PostMapping("/private/cards")
    void createCardsForUser(@RequestBody @Valid UserDto newUser);

    @GetMapping("/public/cards/{cardId}")
    CardDto getCard(@PathVariable(name = "cardId") Integer cardId);

    @PutMapping("/private/cards/{cardId}/{type}")
    void updateCard(@PathVariable(name = "cardId") Integer cardId, @PathVariable(name = "type") RequestType type, @RequestBody @Valid CardDto newCardDto);
}
