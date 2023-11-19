package com.cpe.irc5.asi2.grp1.user_manager.dtos;

import com.cpe.irc5.asi2.grp1.commons.model.DataBusObject;
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
public class UserDto extends DataBusObject {
    private Integer id;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private Double wallet;
}
