package steps;

import domain.Locker;
import domain.Member;
import domain.Product;

import javax.persistence.EntityManager;

public class Step12 implements Step {
    public void logic(EntityManager em) {

        //다대다 단방향

        Product productA = new Product();
        productA.setId(101L);
        productA.setName("상품!");
        em.persist(productA);

        Member member = new Member();
        member.setId(101L);
        member.setUserName("회원1");
        member.getProducts().add(productA);
        em.persist(member);

    }
}
