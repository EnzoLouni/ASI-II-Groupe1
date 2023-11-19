package com.cpe.irc5.asi2.grp1.chatHisto_manager.dto;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatHistoDTO {

    @Null
    private Integer id;
    @NotEmpty
    private Integer userId;
    @NotEmpty
    private Date dateReceive;
    @NotEmpty
    private String msg;

    @NotEmpty
    private String login;

}
