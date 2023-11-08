package com.cpe.irc5.asi2.grp1.card_manager.mapper;

import com.cpe.irc5.asi2.grp1.card_manager.model.CardModel;
import com.cpe.irc5.asi2.grp1.public_cardDTO.dto.CardDTO;

public class DTOMapper {

    public static CardDTO fromCardModelToCardDTO(CardModel cM) {
        CardDTO cDto =new CardDTO();

        cDto.setId(cM.getId());
        cDto.setEnergy(cM.getEnergy());
        cDto.setHp(cM.getHp());
        cDto.setDefence(cM.getDefence());
        cDto.setAttack(cM.getAttack());
        cDto.setPrice(cM.getPrice());

        if (cM.getuser() != null) {
            cDto.setUserId(cM.getuser());
        } else {
            cDto.setUserId(null);
        }

        return cDto;
    }

    public static CardModel fromCardDtoToCardModel(CardDTO cD) {
        CardModel cm=new CardModel(cD);
        cm.setEnergy(cD.getEnergy());
        cm.setHp(cD.getHp());
        cm.setDefence(cD.getDefence());
        cm.setAttack(cD.getAttack());
        cm.setPrice(cD.getPrice());
        cm.setId(cD.getId());
        return cm;
    }


    /* public static UserDTO fromUserModelToUserDTO(UserModel uM) {
        UserDTO uDto =new UserDTO(uM);
        return uDto;
    } */

}
