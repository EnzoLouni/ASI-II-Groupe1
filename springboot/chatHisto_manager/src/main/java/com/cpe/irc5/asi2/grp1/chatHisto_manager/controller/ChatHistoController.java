package com.cpe.irc5.asi2.grp1.chatHisto_manager.controller;


import com.cpe.irc5.asi2.grp1.chatHisto_manager.dto.ChatHistoDTO;
import com.cpe.irc5.asi2.grp1.chatHisto_manager.dto.FakeDTO;
import com.cpe.irc5.asi2.grp1.chatHisto_manager.service.ChatHistoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/public")
public class ChatHistoController {

    @Value("${user.busName}")
    private String busName;

    @Inject
    private final ChatHistoService chatHistoService;

    @Inject
    private JmsTemplate jmsTemplate;


    /**
     *
     Méthode de test d'envoie
     des données dans la queue
     *
     */
    @GetMapping("/start")
    public String startDataSending() throws Exception {

        // Créer l'objet de données
        FakeDTO myData = new FakeDTO();
        myData.setUserId(3);
        myData.setDateReceive("1700143312716");
        myData.setMsg("Bonjour");
        myData.setLogin("ZOZZER");

        // Convertir l'objet en JSON
        String jsonData = myData.toJSON();

        // Envoi du message à la file spécifiée
        jmsTemplate.convertAndSend(busName, jsonData);

        return jsonData;
    }

    @GetMapping("/getHistoriques")
    public List<ChatHistoDTO> getChatHistos() {
        return chatHistoService.getAllChatHisto();
    }


}
