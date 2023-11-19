package com.cpe.irc5.asi2.grp1.chatHisto_manager.service;


import com.cpe.irc5.asi2.grp1.chatHisto_manager.dto.ChatHistoDTO;

import com.cpe.irc5.asi2.grp1.chatHisto_manager.mapper.ChatHistoMapper;
import com.cpe.irc5.asi2.grp1.chatHisto_manager.repository.ChatHistoModelRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
public class ChatHistoService {

    @Inject
    private ChatHistoModelRepository chatHistoModelRepository;
    @Inject
    private ChatHistoMapper chatHistoMapper;

    public void createChatHisto(ChatHistoDTO chatHistoDTO) {

        try {
            chatHistoModelRepository.save(chatHistoMapper.toChatHistoModel(chatHistoDTO));
        } catch(EmptyResultDataAccessException e) {
            System.err.printf(" Error in ChatHistoService,  %s %n", e);
        }
    }

    public List<ChatHistoDTO> getAllChatHisto() {
        return StreamSupport.stream(chatHistoModelRepository.findAll().spliterator(),false).map(chatHistoMapper::toChatHistoDTO).collect(toList());
    }



}
