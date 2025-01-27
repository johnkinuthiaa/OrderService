package com.slippery.orderservice.repository;

import com.slippery.orderservice.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}
