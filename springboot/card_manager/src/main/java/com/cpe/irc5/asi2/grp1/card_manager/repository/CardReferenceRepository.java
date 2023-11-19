package com.cpe.irc5.asi2.grp1.card_manager.repository;

import com.cpe.irc5.asi2.grp1.card_manager.model.CardReference;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardReferenceRepository extends CrudRepository<CardReference, Integer> {
    @Query(value = "SELECT * FROM card_manager.public.card_reference ORDER BY RANDOM() LIMIT 5", nativeQuery = true)
    List<CardReference> findRandomCardReferences();

    @Query(value = "SELECT c FROM CardReference c WHERE c.name = :cardReferenceName")
    CardReference findIdByName(@Param("cardReferenceName") String cardReferenceName);
}
