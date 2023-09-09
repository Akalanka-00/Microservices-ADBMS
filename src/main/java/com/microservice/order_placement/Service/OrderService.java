package com.microservice.order_placement.Service;

import com.microservice.order_placement.Entity.Order;
import com.microservice.order_placement.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Order createOrder(Order order) {
        return repository.save(order);
    }

    public Order getOrderById(int orderId) {
        return repository.findById(orderId).orElse(null);
    }

    public Order updateOrder(int orderId, Order updatedOrder) {
        return repository.save(updatedOrder);
    }

    public void deleteOrder(int orderId) {
        repository.deleteById(orderId);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }
}

