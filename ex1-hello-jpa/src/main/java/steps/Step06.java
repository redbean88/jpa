package steps;

import domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

public class Step06 implements Step {
    public void logic(EntityManager em) {

        em.persist(new Member(150L, "A"));
        em.persist(new Member(151L, "B"));
        em.persist(new Member(152L, "C"));

        System.out.println("====================");

        List<Member> member_m = em.createQuery("select m from TMember m", Member.class).getResultList();
        System.out.println("member.size = " + member_m.size());


    }
}
