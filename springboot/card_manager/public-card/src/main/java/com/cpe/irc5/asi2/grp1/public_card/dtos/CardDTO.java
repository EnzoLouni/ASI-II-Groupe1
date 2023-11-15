package com.cpe.irc5.asi2.grp1.public_card.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @NoArgsConstructor
@AllArgsConstructor
public class CardDTO extends CardBasics {

    private Integer id;
    private float energy;
    private float hp;
    private float defence;
    private float attack;
    private float price;
    private Integer userId;

    public CardDTO() { }

}
