package com.homecomfort.homecomfort.repository;

import com.homecomfort.homecomfort.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
