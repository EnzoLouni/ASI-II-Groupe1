package com.cpe.irc5.asi2.grp1.card_manager.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CARD_REFERENCE", schema = "public")
public class CardReference extends CardBasics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", unique = true)
    private String name;
    private String description;
    @NotNull
    private String family;
    @NotNull
    private String affinity;
    private String imgUrl;
    private String smallImgUrl;

    public CardReference( CardBasics cBasics) {
        super(cBasics);
    }

    public CardReference(Integer id, String name, String description, String family, String affinity, String imgUrl, String smallImgUrl) {
        super(name, description, family, affinity, imgUrl, smallImgUrl);
        this.id=id;
    }
}
