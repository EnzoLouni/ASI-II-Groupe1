package com.cpe.irc5.asi2.grp1.card_manager.model;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class CardBasics {
    protected String name;
    protected String description;
    protected String family;
    protected String affinity;
    protected String imgUrl;
    protected String smallImgUrl;

    public CardBasics(CardBasics cBasics) {
        this(cBasics.name, cBasics.description, cBasics.family, cBasics.affinity, cBasics.imgUrl, cBasics.smallImgUrl);
    }
}
