package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.domain.item.Book;
import com.jpabook.jpashop.exception.NotEnoughStockException;
import com.jpabook.jpashop.repository.MemberRepository;
import com.jpabook.jpashop.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Id;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    Member member = new Member();
    Book book = new Book();

    @Before
    public void setup(){
        member.setName("john");
        member.setAddress(new Address("LA","1st","123-231"));
        em.persist(member);

        book.setName("JAP");
        book.setPrice(10000);
        book.setStotckQuantity(10);
        em.persist(book);
    }

    @Test
    public void 상품주문(){

        Long orderId = orderService.order(member.getId(), book.getId(), 2);

        Order one = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER,one.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, one.getOrderItems().size());
        assertEquals("주문 가격은 가역 * 수량이다.", 10000* 2, one.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.getStotckQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과(){

        orderService.order(member.getId(), book.getId(), 11);

        fail("재소수량 오류 발생");

    }

    @Test
    public void 주문취소(){
        Long order = orderService.order(member.getId(), book.getId(), 2);

        orderService.cancelOrder(order);

        Order one = orderRepository.findOne(order);

        assertEquals("주문 취소시 상태는 CANCEL 이다.",OrderStatus.CANCEL, one.getStatus());
        assertEquals("주문 취소수 수량은 복구 되어야 한다.", 10, book.getStotckQuantity());

    }



}