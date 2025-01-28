package com.slippery.orderservice.controller;

import com.slippery.orderservice.dto.OrderDto;
import com.slippery.orderservice.models.Orders;
import com.slippery.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping(value = "/create",consumes = "application/json")
    public ResponseEntity<OrderDto> createNewOrder(@RequestBody Orders orderDetails) throws Exception {
        return ResponseEntity.ok(orderService.createNewOrder(orderDetails));
    }
    @GetMapping(value = "/{id}/get",produces = "application/json")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.findById(id));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<OrderDto> delete(@RequestParam Long id) {
        return ResponseEntity.ok(orderService.deleteById(id));
    }
}
