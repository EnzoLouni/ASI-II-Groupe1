package com.cpe.irc5.asi2.grp1.chatHisto_manager.repository;

import com.cpe.irc5.asi2.grp1.chatHisto_manager.model.ChatHistoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatHistoModelRepository extends CrudRepository<ChatHistoModel, Integer> {

}
