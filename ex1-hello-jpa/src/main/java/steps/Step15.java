package steps;

import domain.*;

import javax.persistence.EntityManager;

public class Step15 implements Step {
    public void logic(EntityManager em) {

        // 다대다 양방향
        Product3 productA = new Product3();
        productA.setId(101L);
        productA.setName("상품!");
        em.persist(productA);

        Member3 member = new Member3();
        member.setId(100L);
        member.setUserName("회원1");
        em.persist(member);

        Orders order = new Orders();
        order.setMember(member);
        order.setProduct(productA);
        order.setOrderAmout(2);
        em.persist(order);

        em.flush();
        em.clear();
        
        //조회
        
        Long orderId = 1L;
        Orders findOrder = em.find(Orders.class, orderId);

        Member3 findMember = order.getMember();
        Product3 findProduct = order.getProduct();

        System.out.println("findMember.getName() = " + findMember.getName());
        System.out.println("findProduct.getName() = " + findProduct.getName());
        System.out.println("order.getOrderAmout() = " + order.getOrderAmout());
    }
}
