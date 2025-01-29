package com.slippery.orderservice.service.impl;

import com.slippery.orderservice.client.InventoryClient;
import com.slippery.orderservice.client.InventoryDto;
import com.slippery.orderservice.dto.OrderDto;
import com.slippery.orderservice.models.OrderStatus;
import com.slippery.orderservice.models.Orders;
import com.slippery.orderservice.repository.OrderRepository;
import com.slippery.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class OrderServiceImplementation implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient client;

    public OrderServiceImplementation(OrderRepository orderRepository, InventoryClient client) {
        this.orderRepository = orderRepository;
        this.client = client;
    }

    @Override
    public OrderDto createNewOrder(Orders orderDetails)  {
        InventoryDto checkInStock =client.checkInStock(orderDetails.getSkuCode(),orderDetails.getItemsOrdered());
        OrderDto response =new OrderDto();
        if(checkInStock.isInStock()){
            InventoryDto inventoryDto =client.updateInventoryItemsQuantity(orderDetails.getSkuCode(),orderDetails.getItemsOrdered());
            log.info(inventoryDto.getMessage());
            orderDetails.setStatus(OrderStatus.PENDING.name());
            orderRepository.save(orderDetails);
            response.setMessage("Order created");
            log.info("order created");
            response.setStatusCode(200);
            response.setOrder(orderDetails);
        }else{
            response.setMessage(checkInStock.getMessage());
            response.setStatusCode(204);
        }
        return response;


    }

    @Override
    public OrderDto findById(Long id) {
        OrderDto response =new OrderDto();
        Optional<Orders> existingOrder =orderRepository.findById(id);
        if(existingOrder.isEmpty()){
            response.setMessage("Order not found");
            log.info("Order not found");
            response.setStatusCode(404);
            return response;
        }
        response.setStatusCode(200);
        response.setMessage("Order with id "+id);
        response.setOrder(existingOrder.get());
        return response;
    }

    @Override
    public OrderDto deleteById(Long id) {
        OrderDto response =new OrderDto();
        Optional<Orders> existingOrder =orderRepository.findById(id);
        if(existingOrder.isEmpty()){
            response.setMessage("item not found");
            response.setStatusCode(404);
            log.info("item not found");
            return response;
        }
        response.setMessage("item deleted");
        log.info("item deleted");
        response.setStatusCode(204);
        return response;
    }

    @Override
    public OrderDto updateOrder(Long id, Orders orderDetails) {
        return null;
    }
}
