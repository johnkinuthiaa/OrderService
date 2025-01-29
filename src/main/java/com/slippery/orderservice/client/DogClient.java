package com.slippery.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="dog",url="https://dog.ceo/api/breeds/image/random")
public interface DogClient {
    @RequestMapping(method = RequestMethod.GET)
    Object fetchDogImage();
}
