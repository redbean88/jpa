package steps;

import domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * 1차 캐시 예제
 */
public class Step01 implements Step {
    public void logic(EntityManager em) {

        Member member = new Member();
        member.setId(101L);
        member.setName("HelloJPA");

        em.persist(member);

        Member findMemeber = em.find(Member.class,101L); // 1차 캐시 사용
        System.out.println(String.format("findMemeber.id = %s",findMemeber.getId()));
        System.out.println(String.format("findMemeber.name = %s",findMemeber.getName()));

    }
}
