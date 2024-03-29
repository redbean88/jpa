package steps;

import domain.Locker;
import domain.Member;
import domain.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class Step12 implements Step {
    public void logic(EntityManager em) {

        //다대다 단방향

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
        Member findMember = em.find(Member.class, member.getId());
        List<Product> products = member.getProducts();  // 객체 그래프 탐색
        products.forEach(
                product -> {
                    System.out.println("product.getName() = " + product.getName());
                }
        );

    }
}
