package com.cpe.irc5.asi2.grp1.store_manager.dto;


import com.cpe.irc5.asi2.grp1.card_manager.dtos.CardDto;
import com.cpe.irc5.asi2.grp1.store_manager.enums.StoreAction;
import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreTransactionDto{
    private Integer id;
    private UserDto userDto;
    private CardDto cardDto;
    private StoreAction action;
    private Date timestamp;
}
