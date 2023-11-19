package com.cpe.irc5.asi2.grp1.card_manager.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CARD_REFERENCE", schema = "public")
public class CardReference extends CardBasics {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "card_reference_sequence"),
                    @Parameter(name = "initial_value", value = "44"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
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

}
