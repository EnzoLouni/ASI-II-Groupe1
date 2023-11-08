package com.cpe.irc5.asi2.grp1.store_manager.publicstore.dto;


import com.cpe.irc5.asi2.grp1.store_manager.publicstore.enums.StoreAction;
import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDTO;
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
public class StoreTransactionDto {
    private Integer id;
    private UserDTO userDto;
    //private CardDto cardDto;
    private StoreAction action;
    private Date timestamp;
}
