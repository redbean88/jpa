package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.*;
import com.jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initData {

    private final InitService initService;

    @PostConstruct
    private void setup(){
        initService.doInit();
        initService.doInit2();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void doInit() {
            Member member = new Member();
            member.setName("userA");
            member.setAddress(new Address("서울", "1", "12313"));
            em.persist(member);


            Book book1 = new Book();
            book1.setName("JPA1 BOOK");
            book1.setPrice(1000);
            book1.setStockQuantity(100);
            em.persist(book1);

            Book book2 = new Book();
            book2.setName("JPA2 BOOK");
            book2.setPrice(2000);
            book2.setStockQuantity(100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 1000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 2000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void doInit2() {
            Member member = new Member();
            member.setName("userB");
            member.setAddress(new Address("부산", "1", "12313"));
            em.persist(member);


            Book book1 = new Book();
            book1.setName("SPRING1 BOOK");
            book1.setPrice(1000);
            book1.setStockQuantity(100);
            em.persist(book1);

            Book book2 = new Book();
            book2.setName("SPRING2 BOOK");
            book2.setPrice(2000);
            book2.setStockQuantity(100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 1000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 2000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }
    }
}
