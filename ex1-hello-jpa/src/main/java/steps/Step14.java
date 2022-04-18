package steps;

import domain.*;

import javax.persistence.EntityManager;
import java.util.List;

public class Step14 implements Step {
    public void logic(EntityManager em) {

        // 다대다 양방향
        Product2 productA = new Product2();
        productA.setId(101L);
        productA.setName("상품!");
        em.persist(productA);

        Member2 member = new Member2();
        member.setId(100L);
        member.setUserName("회원1");
        em.persist(member);

        MemberProduct memberProduct = new MemberProduct();
        memberProduct.setMember(member);
        memberProduct.setProduct(productA);
        memberProduct.setOrderAmount(2);

        em.persist(memberProduct);
        
        em.flush();
        em.clear();
        
        //조회
        
        MemberProductId memberProductId = new MemberProductId();
        memberProductId.setProduct(101L);
        memberProductId.setMember(100L);

        MemberProduct findMemberProduct = em.find(MemberProduct.class, memberProductId);
        
        Member2 member2 = findMemberProduct.getMember();
        Product2 product2 = findMemberProduct.getProduct();

        System.out.println("member2.getName() = " + member2.getName());
        System.out.println("product2.getName() = " + product2.getName());

    }
}
