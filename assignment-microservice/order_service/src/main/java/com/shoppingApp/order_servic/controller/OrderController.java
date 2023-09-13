package com.shoppingApp.order_servic.controller;

import com.shoppingApp.order_servic.dto.OrderRequest;
import com.shoppingApp.order_servic.model.Order;
import com.shoppingApp.order_servic.repository.OrderRepository;
import com.shoppingApp.order_servic.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor


public class OrderController {


    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public String placeOrder(@RequestBody OrderRequest orderRequest){

        orderService.placeOrder(orderRequest);
        return "order place successfully";

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable Long id){
        Order deleteOrder=orderRepository.findById(id).orElse(null);


        orderRepository.delete(deleteOrder);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/order")

    public List<Order> getAllUsers(){
        return orderRepository.findAll();
    }



}
