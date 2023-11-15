package com.cpe.irc5.asi2.grp1.card_manager.mapper;

import com.cpe.irc5.asi2.grp1.card_manager.model.CardModel;
import com.cpe.irc5.asi2.grp1.card_manager.model.CardReference;
import com.cpe.irc5.asi2.grp1.public_card.dtos.CardDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {

    public CardDTO fromCardModelToCardDTO(CardModel card);

    public CardModel fromCardDtoToCardModel(CardDTO cardDTO);


    public List<CardDTO> fromCardModelToCardDTO(List<CardModel> cardModel);

    public List<CardModel> fromCardDtoToCardModel(List<CardDTO> cardDTO);



    public CardDTO fromCardReferenceToCardDTO(CardReference cardRef);

    public CardReference fromCardDtoToCardReference(CardReference cardReference);
    public List<CardDTO> fromCardReferenceToCardDTO(List<CardReference> cardRef);

    public List<CardReference> fromCardDtoToCardReference(List<CardDTO> cardDTO);

}
