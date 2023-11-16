package com.cpe.irc5.asi2.grp1.store_manager.controller;

import com.cpe.irc5.asi2.grp1.store_manager.dto.StoreOrder;
import com.cpe.irc5.asi2.grp1.store_manager.dto.StoreTransactionDto;
import com.cpe.irc5.asi2.grp1.store_manager.service.StoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.MessageNotWriteableException;
import javax.validation.Valid;
import java.net.ConnectException;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/transactions")
    public List<StoreTransactionDto> getTransactions() {
        return storeService.getTransactions();
    }

    @PostMapping("/sell")
    public void sell(@RequestBody @Valid StoreOrder order) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        storeService.sellRequest(order);
    }

    @PostMapping("/buy")
    public void buy(@RequestBody @Valid StoreOrder order) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        storeService.buyRequest(order);
    }
}
