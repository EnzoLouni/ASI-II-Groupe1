package com.cpe.irc5.asi2.grp1.card_manager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CARD_MODEL", schema = "public")
public class CardModel extends CardBasics implements Comparable<CardModel>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Double energy;
    @NotNull
    private Double hp;
    @NotNull
    private Double defense;
    @NotNull
    private Double attack;
    @NotNull
    private Double price;
    private Integer userId;
    @NotNull
    private Integer cardReferenceId;

    @Override
    public int compareTo(CardModel c) {
        return this.id - c.id;
    }
}
