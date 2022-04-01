package com.jpabook.jpashop.api;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderItem;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.repository.OrderRepository;
import com.jpabook.jpashop.repository.OrderSearch;
import com.jpabook.jpashop.repository.order.query.OrderQueryDto;
import com.jpabook.jpashop.repository.order.query.OrderQueryRepository;
import com.jpabook.jpashop.repository.order.simpleRepository.OrderSimpleQueryDto;
import com.jpabook.jpashop.repository.order.simpleRepository.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAll(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();

            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.forEach(orderItem -> orderItem.getItem().getName());
        }
        return all;
    }

    @GetMapping("/api/v2/orders")
    public List<OrderDTO> ordersV2() {
        List<Order> orders = orderRepository.findAll(new OrderSearch());
        return orders.stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }
    @GetMapping("/api/v3/orders")
    public List<OrderDTO> ordersV3() {
        List<Order> orders = orderRepository.findAllwithItem(new OrderSearch());
        return orders.stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }
    @GetMapping("/api/v3.1/orders")
    public List<OrderDTO> ordersV3_page(
            @RequestParam(value = "offset",defaultValue = "0") int offset,
            @RequestParam(value = "limit" , defaultValue = "100") int limit
    ) {
        List<Order> orders = orderRepository.findAllwithMemberDelivery(offset , limit);
        return orders.stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }


    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4(
    ) {
        return orderQueryRepository.findOrderQueryDtos();
    }

    @Data
    static class OrderDTO {

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDTO> orderItems;

        public OrderDTO(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            orderItems = order.getOrderItems().stream()
                    .map(OrderItemDTO::new)
                    .collect(Collectors.toList());
        }
    }

    @Data
    static class OrderItemDTO {

        private String itemName;
        private int orderPrice;
        private int count;

        public OrderItemDTO(OrderItem orderItem) {
            this.itemName = orderItem.getItem().getName();
            this.orderPrice = orderItem.getOrderPrice();
            this.count = orderItem.getCount();

        }
    }
}
