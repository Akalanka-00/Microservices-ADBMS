package com.microservice.order_placement.Controller;

import com.microservice.order_placement.Entity.Order;
import com.microservice.order_placement.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {

        return service.createOrder(order);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable int orderId) {

        return service.getOrderById(orderId);
    }

    @PutMapping("/{orderId}")
    public Order updateOrder(@PathVariable int orderId, @RequestBody Order order) {

        return service.updateOrder(orderId, order);
    }

    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable int orderId) {

        service.deleteOrder(orderId);
        return "Order with ID " + orderId + " has been deleted.";
    }

    @GetMapping
    public List<Order> getAllOrders() {

        return service.getAllOrders();
    }
}
