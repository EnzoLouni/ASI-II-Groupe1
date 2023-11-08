package com.cpe.irc5.asi2.grp1.card_manager.repository;

import com.cpe.irc5.asi2.grp1.card_manager.model.CardModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardModelRepository extends CrudRepository<CardModel, Integer> {
    List<CardModel> findByUserId(Integer id);
}

