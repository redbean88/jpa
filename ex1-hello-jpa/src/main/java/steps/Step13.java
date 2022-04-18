package steps;

import domain.Member;
import domain.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class Step13 implements Step {
    public void logic(EntityManager em) {

        // 다대다 양방향
        Product productA = new Product();
        productA.setName("상품!");
        em.persist(productA);

        Member member = new Member();
        member.setUserName("회원1");
        member.getProducts().add(productA);
        em.persist(member);

        em.flush();
        em.clear();

        // 조회
        Product product = em.find(Product.class, productA.getId());
        List<Member> members = product.getMembers();
        members.forEach(m -> System.out.println("m.getUserName() = " + m.getUserName()));
    }
}
