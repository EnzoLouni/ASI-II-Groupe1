package com.cpe.irc5.asi2.grp1.card_manager.mapper;

import com.cpe.irc5.asi2.grp1.card_manager.model.CardModel;
import com.cpe.irc5.asi2.grp1.card_manager.model.CardReference;
import com.cpe.irc5.asi2.grp1.public_card.dtos.CardDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-08T17:35:25+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public CardDTO fromCardModelToCardDTO(CardModel card) {
        if ( card == null ) {
            return null;
        }

        CardDTO cardDTO = new CardDTO();

        cardDTO.setName( card.getName() );
        cardDTO.setDescription( card.getDescription() );
        cardDTO.setFamily( card.getFamily() );
        cardDTO.setAffinity( card.getAffinity() );
        cardDTO.setImgUrl( card.getImgUrl() );
        cardDTO.setSmallImgUrl( card.getSmallImgUrl() );
        cardDTO.setId( card.getId() );
        cardDTO.setEnergy( card.getEnergy() );
        cardDTO.setHp( card.getHp() );
        cardDTO.setDefence( card.getDefence() );
        cardDTO.setAttack( card.getAttack() );
        cardDTO.setPrice( card.getPrice() );

        return cardDTO;
    }

    @Override
    public CardModel fromCardDtoToCardModel(CardDTO cardDTO) {
        if ( cardDTO == null ) {
            return null;
        }

        CardModel cardModel = new CardModel();

        cardModel.setName( cardDTO.getName() );
        cardModel.setDescription( cardDTO.getDescription() );
        cardModel.setFamily( cardDTO.getFamily() );
        cardModel.setAffinity( cardDTO.getAffinity() );
        cardModel.setImgUrl( cardDTO.getImgUrl() );
        cardModel.setSmallImgUrl( cardDTO.getSmallImgUrl() );
        cardModel.setEnergy( cardDTO.getEnergy() );
        cardModel.setHp( cardDTO.getHp() );
        cardModel.setDefence( cardDTO.getDefence() );
        cardModel.setAttack( cardDTO.getAttack() );
        cardModel.setPrice( cardDTO.getPrice() );
        cardModel.setId( cardDTO.getId() );

        return cardModel;
    }

    @Override
    public List<CardDTO> fromCardModelToCardDTO(List<CardModel> cardModel) {
        if ( cardModel == null ) {
            return null;
        }

        List<CardDTO> list = new ArrayList<CardDTO>( cardModel.size() );
        for ( CardModel cardModel1 : cardModel ) {
            list.add( fromCardModelToCardDTO( cardModel1 ) );
        }

        return list;
    }

    @Override
    public List<CardModel> fromCardDtoToCardModel(List<CardDTO> cardDTO) {
        if ( cardDTO == null ) {
            return null;
        }

        List<CardModel> list = new ArrayList<CardModel>( cardDTO.size() );
        for ( CardDTO cardDTO1 : cardDTO ) {
            list.add( fromCardDtoToCardModel( cardDTO1 ) );
        }

        return list;
    }

    @Override
    public CardDTO fromCardReferenceToCardDTO(CardReference cardRef) {
        if ( cardRef == null ) {
            return null;
        }

        CardDTO cardDTO = new CardDTO();

        cardDTO.setName( cardRef.getName() );
        cardDTO.setDescription( cardRef.getDescription() );
        cardDTO.setFamily( cardRef.getFamily() );
        cardDTO.setAffinity( cardRef.getAffinity() );
        cardDTO.setImgUrl( cardRef.getImgUrl() );
        cardDTO.setSmallImgUrl( cardRef.getSmallImgUrl() );
        cardDTO.setId( cardRef.getId() );

        return cardDTO;
    }

    @Override
    public CardReference fromCardDtoToCardReference(CardReference cardReference) {
        if ( cardReference == null ) {
            return null;
        }

        CardReference cardReference1 = new CardReference();

        cardReference1.setName( cardReference.getName() );
        cardReference1.setDescription( cardReference.getDescription() );
        cardReference1.setFamily( cardReference.getFamily() );
        cardReference1.setAffinity( cardReference.getAffinity() );
        cardReference1.setImgUrl( cardReference.getImgUrl() );
        cardReference1.setSmallImgUrl( cardReference.getSmallImgUrl() );
        cardReference1.setId( cardReference.getId() );

        return cardReference1;
    }

    @Override
    public List<CardDTO> fromCardReferenceToCardDTO(List<CardReference> cardRef) {
        if ( cardRef == null ) {
            return null;
        }

        List<CardDTO> list = new ArrayList<CardDTO>( cardRef.size() );
        for ( CardReference cardReference : cardRef ) {
            list.add( fromCardReferenceToCardDTO( cardReference ) );
        }

        return list;
    }

    @Override
    public List<CardReference> fromCardDtoToCardReference(List<CardDTO> cardDTO) {
        if ( cardDTO == null ) {
            return null;
        }

        List<CardReference> list = new ArrayList<CardReference>( cardDTO.size() );
        for ( CardDTO cardDTO1 : cardDTO ) {
            list.add( cardDTOToCardReference( cardDTO1 ) );
        }

        return list;
    }

    protected CardReference cardDTOToCardReference(CardDTO cardDTO) {
        if ( cardDTO == null ) {
            return null;
        }

        CardReference cardReference = new CardReference();

        cardReference.setName( cardDTO.getName() );
        cardReference.setDescription( cardDTO.getDescription() );
        cardReference.setFamily( cardDTO.getFamily() );
        cardReference.setAffinity( cardDTO.getAffinity() );
        cardReference.setImgUrl( cardDTO.getImgUrl() );
        cardReference.setSmallImgUrl( cardDTO.getSmallImgUrl() );
        cardReference.setId( cardDTO.getId() );

        return cardReference;
    }
}
