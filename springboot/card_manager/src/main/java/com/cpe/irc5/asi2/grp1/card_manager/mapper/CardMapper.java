package com.cpe.irc5.asi2.grp1.card_manager.mapper;

import com.cpe.irc5.asi2.grp1.card_manager.model.CardModel;
import com.cpe.irc5.asi2.grp1.card_manager.model.CardReference;
import com.cpe.irc5.asi2.grp1.card_manager.dtos.CardDto;
import com.cpe.irc5.asi2.grp1.card_manager.service.CardReferenceService;
import com.cpe.irc5.asi2.grp1.user_manager.client.UserClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mapstruct.InjectionStrategy.FIELD;

@Mapper(injectionStrategy = FIELD, componentModel = "spring")
public abstract class CardMapper {

    @Autowired
    protected UserClient userClient;

    @Autowired
    protected CardReferenceService cardReferenceService;

    @Mapping(target = "id", source = "cardModel.id")
    @Mapping(target = "name", expression = "java(cardReferenceService.getCardReference(cardModel.getCardReferenceId()).getName())")
    @Mapping(target = "description", expression = "java(cardReferenceService.getCardReference(cardModel.getCardReferenceId()).getDescription())")
    @Mapping(target = "family", expression = "java(cardReferenceService.getCardReference(cardModel.getCardReferenceId()).getFamily())")
    @Mapping(target = "affinity", expression = "java(cardReferenceService.getCardReference(cardModel.getCardReferenceId()).getAffinity())")
    @Mapping(target = "imgUrl", expression = "java(cardReferenceService.getCardReference(cardModel.getCardReferenceId()).getImgUrl())")
    @Mapping(target = "smallImgUrl", expression = "java(cardReferenceService.getCardReference(cardModel.getCardReferenceId()).getSmallImgUrl())")
    @Mapping(target = "energy", source = "cardModel.energy")
    @Mapping(target = "hp", source = "cardModel.hp")
    @Mapping(target = "defense", source = "cardModel.defense")
    @Mapping(target = "attack", source = "cardModel.attack")
    @Mapping(target = "price", source = "cardModel.price")
    @Mapping(target = "userDto", expression = "java( cardModel.getUserId() != null ? userClient.getUser(cardModel.getUserId()) : null)")
    public abstract CardDto toCardDto(CardModel cardModel);


    @Mapping(target = "id", source = "cardDto.id")
    @Mapping(target = "energy", source = "cardDto.energy")
    @Mapping(target = "hp", source = "cardDto.hp")
    @Mapping(target = "defense", source = "cardDto.defense")
    @Mapping(target = "attack", source = "cardDto.attack")
    @Mapping(target = "price", source = "cardDto.price")
    @Mapping(target = "userId", expression = "java(cardDto.getUserDto() != null ? cardDto.getUserDto().getId() : null)")
    @Mapping(target = "cardReferenceId", expression = "java(cardReferenceService.getCardReferenceIdByName(cardDto.getName()).getId())")
    public abstract CardModel toCardModel(CardDto cardDto);


    @Mapping(target = "id", source = "cardDto.id")
    @Mapping(target = "name", source = "cardDto.name")
    @Mapping(target = "description", source = "cardDto.description")
    @Mapping(target = "family", source = "cardDto.family")
    @Mapping(target = "affinity", source = "cardDto.affinity")
    @Mapping(target = "imgUrl", source = "cardDto.imgUrl")
    @Mapping(target = "smallImgUrl", source = "cardDto.smallImgUrl")
    public abstract CardReference toCardReference(CardDto cardDto);

}
