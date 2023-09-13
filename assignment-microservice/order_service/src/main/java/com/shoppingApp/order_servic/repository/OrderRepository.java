package com.shoppingApp.order_servic.repository;

import com.shoppingApp.order_servic.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order,Long> {
}
