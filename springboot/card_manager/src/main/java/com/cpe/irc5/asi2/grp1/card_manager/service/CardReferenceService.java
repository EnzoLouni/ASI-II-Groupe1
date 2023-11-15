package com.cpe.irc5.asi2.grp1.card_manager.service;

import com.cpe.irc5.asi2.grp1.card_manager.mapper.CardMapper;
import com.cpe.irc5.asi2.grp1.card_manager.model.CardModel;
import com.cpe.irc5.asi2.grp1.card_manager.model.CardReference;
import com.cpe.irc5.asi2.grp1.card_manager.repository.CardModelRepository;
import com.cpe.irc5.asi2.grp1.card_manager.repository.CardRefRepository;
import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import com.cpe.irc5.asi2.grp1.public_card.dtos.CardDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import javax.inject.Inject;
import javax.jms.MessageNotWriteableException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.GROUP;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.TYPE;

@Service
public class CardReferenceService {

    @Inject
    private CardRefRepository cardRefRepository;

    @Inject
    private CardMapper cardMapper;

    @Inject
    private CardBusService cardBusService;

    private static final ObjectMapper mapper = new ObjectMapper();

    public CardReferenceService(CardRefRepository cardRefRepository) {
        this.cardRefRepository=cardRefRepository;
    }

    public List<CardReference> getAllCardRef() {
        List<CardReference> cardRefList = new ArrayList<>();
        cardRefRepository.findAll().forEach(cardRefList::add);
        return cardRefList;
    }

    /* public void addCardRef(CardReference cardRef) {
        cardRefRepository.save(cardRef);
    } */

    public void addCardRefRequest(CardDTO cardDTO) throws JsonProcessingException, MessageNotWriteableException, ConnectException {

        System.err.printf(" Create Request received %n");
        // log.info("Create Request received");
        ObjectNode objectNode = (ObjectNode) mapper.readTree(mapper.writeValueAsString(cardDTO));
        objectNode.put(GROUP, GroupID.Cards.name());
        objectNode.put(TYPE, RequestType.POST.name());
        cardBusService.pushInQueue(objectNode);
    }

    public void addCardRef(CardReference cardRef) throws CannotCreateTransactionException {

        System.err.printf(" Update User with ID: %s %n", cardRef.getId());
        // log.info("Update User with ID: {}", cardUpdated.getId());
        try {
            cardRefRepository.save(cardRef);
        } catch(EmptyResultDataAccessException e) {
            System.err.printf(" %s %n", e);
            // log.error(USER_NOT_FOUND, id);
        }
    }

    public void updateCardRef(CardReference cardRef) {
        cardRefRepository.save(cardRef);

    }

    public CardReference getRandCardRef() {
        List<CardReference> cardRefList=getAllCardRef();
        if( cardRefList.size()>0) {
            Random rand=new Random();
            int rindex=rand.nextInt(cardRefList.size()-1);
            return cardRefList.get(rindex);
        }
        return null;
    }

    /**
     * Executed after application start
     */
    @EventListener(ApplicationReadyEvent.class)
    public void doInitAfterStartup() throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        for(int i=0;i<10;i++){
            CardReference cardRef =new CardReference("name"+i,"description"+i,"family"+i,"affinity"+i,"http://medias.3dvf.com/news/sitegrab/gits2045.jpg","https://cdn.animenewsnetwork.com/thumbnails/fit600x1000/cms/feature/89858/05.jpg");
            addCardRefRequest(cardMapper.fromCardReferenceToCardDTO(cardRef));
            i++;
        }
    }

}
