package com.cpe.irc5.asi2.grp1.card_manager.repository;

import com.cpe.irc5.asi2.grp1.card_manager.model.CardModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardModelRepository extends CrudRepository<CardModel, Integer> {
    List<CardModel> findByUser(Integer id);
}

