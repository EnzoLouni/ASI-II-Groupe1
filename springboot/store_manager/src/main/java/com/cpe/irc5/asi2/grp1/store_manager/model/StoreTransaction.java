package com.cpe.irc5.asi2.grp1.store_manager.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STORE_TRANSACTION", schema = "public")
public class StoreTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer userId;
    @Column
    private Integer cardId;
    @NotNull
    private String action;
    @Column
    private Date timestamp;

    public StoreTransaction(StoreTransactionTemp storeTransactionTemp) {
        this.userId=storeTransactionTemp.getUserId();
        this.cardId=storeTransactionTemp.getCardId();
        this.action=storeTransactionTemp.getAction();
        this.timestamp=storeTransactionTemp.getTimestamp();
    }
}
