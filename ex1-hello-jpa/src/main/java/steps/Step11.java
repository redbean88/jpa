package steps;

import domain.Member2;
import domain.Member3;

import javax.persistence.EntityManager;

public class Step11 implements Step {
    public void logic(EntityManager em) {

        // 기본생성자가 없을 경

        //같은 객체 등록
        Member3 member3 = new Member3(1L);
        System.out.println("member3 = " + member3);
        em.persist(member3);
        em.flush();
        em.clear();

        Member3 member31 = em.find(Member3.class, 1L);
        System.out.println(member31);
    }
}
