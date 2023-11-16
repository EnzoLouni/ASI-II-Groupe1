package com.cpe.irc5.asi2.grp1.card_manager.mapper;

import com.cpe.irc5.asi2.grp1.card_manager.dtos.CardDto;
import com.cpe.irc5.asi2.grp1.card_manager.dtos.CardDto.CardDtoBuilder;
import com.cpe.irc5.asi2.grp1.card_manager.model.CardModel;
import com.cpe.irc5.asi2.grp1.card_manager.model.CardModel.CardModelBuilder;
import com.cpe.irc5.asi2.grp1.card_manager.model.CardReference;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-16T00:31:46+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
@Component
public class CardMapperImpl extends CardMapper {

    @Override
    public CardDto toCardDto(CardModel cardModel) {
        if ( cardModel == null ) {
            return null;
        }

        CardDtoBuilder cardDto = CardDto.builder();

        cardDto.id( cardModel.getId() );
        cardDto.energy( cardModel.getEnergy() );
        cardDto.hp( cardModel.getHp() );
        cardDto.defense( cardModel.getDefense() );
        cardDto.attack( cardModel.getAttack() );
        cardDto.price( cardModel.getPrice() );

        cardDto.name( cardReferenceService.getCardReference(cardModel.getCardReferenceId()).getName() );
        cardDto.description( cardReferenceService.getCardReference(cardModel.getCardReferenceId()).getDescription() );
        cardDto.family( cardReferenceService.getCardReference(cardModel.getCardReferenceId()).getFamily() );
        cardDto.affinity( cardReferenceService.getCardReference(cardModel.getCardReferenceId()).getAffinity() );
        cardDto.imgUrl( cardReferenceService.getCardReference(cardModel.getCardReferenceId()).getImgUrl() );
        cardDto.smallImgUrl( cardReferenceService.getCardReference(cardModel.getCardReferenceId()).getSmallImgUrl() );
        cardDto.userDto( cardModel.getUserId() != null ? userClient.getUser(cardModel.getUserId()) : null );

        return cardDto.build();
    }

    @Override
    public CardModel toCardModel(CardDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        CardModelBuilder cardModel = CardModel.builder();

        cardModel.id( cardDto.getId() );
        cardModel.energy( cardDto.getEnergy() );
        cardModel.hp( cardDto.getHp() );
        cardModel.defense( cardDto.getDefense() );
        cardModel.attack( cardDto.getAttack() );
        cardModel.price( cardDto.getPrice() );

        cardModel.userId( cardDto.getUserDto().getId() );
        cardModel.cardReferenceId( cardReferenceService.getCardReferenceIdByName(cardDto.getName()) );

        return cardModel.build();
    }

    @Override
    public CardReference toCardReference(CardDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        CardReference cardReference = new CardReference();

        cardReference.setId( cardDto.getId() );
        cardReference.setName( cardDto.getName() );
        cardReference.setDescription( cardDto.getDescription() );
        cardReference.setFamily( cardDto.getFamily() );
        cardReference.setAffinity( cardDto.getAffinity() );
        cardReference.setImgUrl( cardDto.getImgUrl() );
        cardReference.setSmallImgUrl( cardDto.getSmallImgUrl() );

        return cardReference;
    }
}
