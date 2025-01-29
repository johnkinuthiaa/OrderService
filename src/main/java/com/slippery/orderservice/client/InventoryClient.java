package com.slippery.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8082/api/v1/inventory",name = "inventory")
public interface InventoryClient {

    @RequestMapping(method = RequestMethod.GET,value = "/check-stock")
    InventoryDto checkInStock(@RequestParam String skuCode,@RequestParam Long quantity);

    @RequestMapping(method = RequestMethod.PUT,value="/update/items")
    InventoryDto updateInventoryItemsQuantity(@RequestParam String skuCode,@RequestParam Long quantity);
}
