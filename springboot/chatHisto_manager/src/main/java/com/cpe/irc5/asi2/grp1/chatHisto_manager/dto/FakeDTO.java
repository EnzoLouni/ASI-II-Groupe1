package com.cpe.irc5.asi2.grp1.chatHisto_manager.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FakeDTO {

    @Null
    private Integer id;
    @NotEmpty
    private Integer userId;
    @NotEmpty
    private String dateReceive;
    @NotEmpty
    private String msg;

    @NotEmpty
    private String login;

    public String toJSON() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}
