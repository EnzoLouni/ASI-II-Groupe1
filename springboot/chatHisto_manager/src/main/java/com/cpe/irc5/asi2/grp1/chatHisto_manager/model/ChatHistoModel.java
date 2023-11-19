package com.cpe.irc5.asi2.grp1.chatHisto_manager.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CHAT_HISTORIQUE", schema = "public")
public class ChatHistoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Date dateReceive;
    private String msg;

    private String login;


}
