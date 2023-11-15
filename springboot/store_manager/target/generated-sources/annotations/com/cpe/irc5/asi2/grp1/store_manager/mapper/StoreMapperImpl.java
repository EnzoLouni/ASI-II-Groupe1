package com.cpe.irc5.asi2.grp1.store_manager.mapper;

import com.cpe.irc5.asi2.grp1.store_manager.model.StoreTransaction;
import com.cpe.irc5.asi2.grp1.store_manager.model.StoreTransaction.StoreTransactionBuilder;
import com.cpe.irc5.asi2.grp1.store_manager.publicstore.dto.StoreTransactionDto;
import com.cpe.irc5.asi2.grp1.store_manager.publicstore.dto.StoreTransactionDto.StoreTransactionDtoBuilder;
import com.cpe.irc5.asi2.grp1.store_manager.publicstore.enums.StoreAction;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-08T20:20:06+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class StoreMapperImpl extends StoreMapper {

    @Override
    public StoreTransactionDto toStoreTransactionDto(StoreTransaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        StoreTransactionDtoBuilder storeTransactionDto = StoreTransactionDto.builder();

        storeTransactionDto.id( transaction.getId() );
        storeTransactionDto.timestamp( transaction.getTimestamp() );

        storeTransactionDto.action( StoreAction.valueOf(transaction.getAction()) );

        return storeTransactionDto.build();
    }

    @Override
    public StoreTransaction toStoreTransaction(StoreTransactionDto transactionDto) {
        if ( transactionDto == null ) {
            return null;
        }

        StoreTransactionBuilder storeTransaction = StoreTransaction.builder();

        storeTransaction.id( transactionDto.getId() );
        storeTransaction.timestamp( transactionDto.getTimestamp() );

        storeTransaction.userId( transactionDto.getUserDto().getId() );
        storeTransaction.action( transactionDto.getAction().toString() );

        return storeTransaction.build();
    }
}
