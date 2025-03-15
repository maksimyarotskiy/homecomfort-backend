package com.homecomfort.homecomfort.repository;

import com.homecomfort.homecomfort.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
