package steps;

import domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

public class Step01 implements Step {
    public void logic(EntityManager em) {

        // 비영속 상태 (new)
        Member member = new Member();
        member.setId(1L);
        member.setName("HelloA");

        //영속 상태 (managed)
        em.persist(member);
        Member findMemeber = em.find(Member.class, 1L);

        System.out.println("findMemeber.getName() = " + findMemeber.getName());

    }
}
