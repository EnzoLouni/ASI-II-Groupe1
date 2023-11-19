package com.cpe.irc5.asi2.grp1.chatHisto_manager.mapper;

import com.cpe.irc5.asi2.grp1.chatHisto_manager.dto.ChatHistoDTO;
import com.cpe.irc5.asi2.grp1.chatHisto_manager.model.ChatHistoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.FIELD;

@Mapper(injectionStrategy = FIELD, componentModel = "spring")
public abstract class ChatHistoMapper {

    @Mapping(target = "id", source = "chatHistoModel.id")
    @Mapping(target = "userId", source = "chatHistoModel.userId")
    @Mapping(target = "dateReceive", source = "chatHistoModel.dateReceive")
    @Mapping(target = "msg", source = "chatHistoModel.msg")
    public abstract ChatHistoDTO toChatHistoDTO(ChatHistoModel chatHistoModel);

    @Mapping(target = "id", source = "chatHistoDTO.id")
    @Mapping(target = "userId", source = "chatHistoDTO.userId")
    @Mapping(target = "dateReceive", source = "chatHistoDTO.dateReceive")
    @Mapping(target = "msg", source = "chatHistoDTO.msg")
    public abstract ChatHistoModel toChatHistoModel(ChatHistoDTO chatHistoDTO);

}
