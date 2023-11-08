package com.cpe.irc5.asi2.grp1.public_card.client;

@FeignClient(name = "cardClient", url = "${card.ribbon.listOfServers}/cardapi")
public class CardClient {


    @GetMapping("/cards_to_sell")
    List<CardDTO> getCardsToSell();

    @GetMapping("/Rand_card")
    boolean getRandCard(@RequestParam("nbr") String nbr);

}
