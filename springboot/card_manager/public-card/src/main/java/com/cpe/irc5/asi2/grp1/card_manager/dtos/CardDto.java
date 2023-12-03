package com.cpe.irc5.asi2.grp1.card_manager.dtos;

import com.cpe.irc5.asi2.grp1.commons.model.DataBusObject;
import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto extends DataBusObject {
    private Integer id;
    private String name;
    private String description;
    private String family;
    private String affinity;
    private String imgUrl;
    private String smallImgUrl;
    private Double energy;
    private Double hp;
    private Double defense;
    private Double attack;
    private Double price;
    private UserDto userDto;
}
