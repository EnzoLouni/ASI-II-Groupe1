package com.cpe.irc5.asi2.grp1.store_manager.controller;

import com.cpe.irc5.asi2.grp1.store_manager.publicstore.dto.StoreOrder;
import com.cpe.irc5.asi2.grp1.store_manager.publicstore.dto.StoreTransactionDto;
import com.cpe.irc5.asi2.grp1.store_manager.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/transactions")
    public List<StoreTransactionDto> getTransactions() {
        return storeService.getTransactions();
    }

    @PostMapping("/sell")
    public boolean sell(@RequestBody @Valid StoreOrder order)
    {
        return false;//storeService.sell(order);
    }

    @PostMapping("/buy")
    public boolean buy(@RequestBody @Valid StoreOrder order)
    {
        return false;//storeService.buy(order);
    }
}
