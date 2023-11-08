package com.cpe.irc5.asi2.grp1.store_manager.repository;

import com.cpe.irc5.asi2.grp1.store_manager.model.StoreTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<StoreTransaction, Integer> {
}