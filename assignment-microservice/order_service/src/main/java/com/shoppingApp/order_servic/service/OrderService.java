package com.shoppingApp.order_servic.service;

import com.shoppingApp.order_servic.dto.OrderLineItemsDto;
import com.shoppingApp.order_servic.dto.OrderRequest;
import com.shoppingApp.order_servic.model.Order;
import com.shoppingApp.order_servic.model.OrderLineItems;
import com.shoppingApp.order_servic.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
       List<OrderLineItems>orderLineItems= orderRequest.getOrderLineItemsDtoList().stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);

    }
    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItems.getQuantity());
        orderLineItems.setSkuCode(orderLineItems.getSkuCode());

        return  orderLineItems;

    }
}
