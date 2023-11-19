package com.cpe.irc5.asi2.grp1.store_manager.repository;

import com.cpe.irc5.asi2.grp1.store_manager.model.StoreTransactionTemp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreTempRepository extends CrudRepository<StoreTransactionTemp, Integer> {
    StoreTransactionTemp findByCardIdAndAction(@Param("cardId") Integer cardId, @Param("action") String action);
}
