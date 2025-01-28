package com.slippery.orderservice.service;

import com.slippery.orderservice.dto.OrderDto;
import com.slippery.orderservice.models.Orders;

public interface OrderService {
    OrderDto createNewOrder(Orders orderDetails) throws Exception;
    OrderDto findById(Long id);
    OrderDto deleteById(Long id);
    OrderDto updateOrder(Long id,Orders orderDetails);
}
