package com.cpe.irc5.asi2.grp1.public_card.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class CardBasics {

    private String name;
    private String description;
    private String family;
    private String affinity;
    private String imgUrl;
    private String smallImgUrl;

    public CardBasics() {
        super();
    }

    public CardBasics(CardBasics c) {
        this(c.getName(), c.getDescription(), c.getFamily(), c.getAffinity(), c.getImgUrl(), c.getSmallImgUrl());
    }

    public CardBasics(String name, String description, String family, String affinity, String imgUrl,
                      String smallImgUrl) {
        super();
        this.name = name;
        this.description = description;
        this.family = family;
        this.affinity = affinity;
        this.imgUrl = imgUrl;
        this.smallImgUrl = smallImgUrl;
    }

}
