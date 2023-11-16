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
public class CardModel extends CardBasics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Float energy;
    @NotNull
    private Float hp;
    @NotNull
    private Float defense;
    @NotNull
    private Float attack;
    @NotNull
    private Float price;
    private Integer userId;
    @NotNull
    private Integer cardReferenceId;
}
