package com.cpe.irc5.asi2.grp1.card_manager.controller.publics;


import com.cpe.irc5.asi2.grp1.card_manager.mapper.CardMapper;
import com.cpe.irc5.asi2.grp1.card_manager.model.CardModel;
import com.cpe.irc5.asi2.grp1.card_manager.service.CardModelService;
import com.cpe.irc5.asi2.grp1.public_card.dtos.CardDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Inject;
import javax.jms.MessageNotWriteableException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//ONLY FOR TEST NEED ALSO TO ALLOW CROOS ORIGIN ON WEB BROWSER SIDE
@CrossOrigin
@RestController
public class CardController {

    @Inject
    private CardMapper cardMapper;

    @Inject
    private CardModelService cardModelService;


    public CardController(CardModelService cardModelService) {
        this.cardModelService = cardModelService;
    }

    @RequestMapping(method=RequestMethod.GET, value="/cards")
    private List<CardDTO> getAllCards() {
        List<CardDTO> cLightList=new ArrayList<>();
        for(CardModel c:cardModelService.getAllCardModel()){
            cardMapper.fromCardModelToCardDTO(c);
            cLightList.add(cardMapper.fromCardModelToCardDTO(c));
        }
        return cLightList;
    }

    @RequestMapping(method=RequestMethod.GET, value="/card/{id}")
    private CardDTO getCard(@PathVariable String id) {
        Optional<CardModel> rcard;
        rcard= cardModelService.getCard(Integer.valueOf(id));
        if(rcard.isPresent()) {
            return cardMapper.fromCardModelToCardDTO(rcard.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card id:"+id+", not found",null);
    }

    @RequestMapping(method=RequestMethod.POST,value="/card")
    public void addCard(@RequestBody CardDTO card) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        cardModelService.addCardRequest(card);
    }

    @RequestMapping(method=RequestMethod.PUT,value="/card/{id}")
    public void updateCard(@RequestBody CardDTO card,@PathVariable String id) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        card.setId(Integer.valueOf(id));
        cardModelService.updateCardRequest(card.getId(), card);
    }

    @RequestMapping(method=RequestMethod.DELETE,value="/card/{id}")
    public void deleteUser(@PathVariable String id) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        cardModelService.deleteCardModelRequest(Integer.valueOf(id));
    }

    @RequestMapping(method= RequestMethod.GET, value="/cards_to_sell")
    private List<CardDTO> getCardsToSell() {
        List<CardDTO> list=new ArrayList<>();
        for( CardModel c : cardModelService.getAllCardToSell()){
            cardMapper.fromCardModelToCardDTO(c);
            list.add(cardMapper.fromCardModelToCardDTO(c));
        }
        return list;
    }

    @RequestMapping(method= RequestMethod.GET, value="/Rand_card")
    private List<CardModel>  getRandCard(@PathVariable String nbr) throws MessageNotWriteableException, JsonProcessingException, ConnectException {

        return cardModelService.getRandCard(Integer.parseInt(nbr));
    }


}
