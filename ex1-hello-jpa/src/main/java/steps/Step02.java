package steps;

import domain.Member;

import javax.persistence.EntityManager;

/**
 * 영속 엔티티의 동일성 보장 예제
 */
public class Step02 implements Step {
    public void logic(EntityManager em) {

        Member member = new Member();
        member.setId(101L);
        member.setName("HelloJPA");

        em.persist(member);

        Member findMemeber1 = em.find(Member.class,101L); // 1차 캐시 사용
        Member findMemeber2 = em.find(Member.class,101L); // 1차 캐시 사용

        System.out.println(member == findMemeber1);
        System.out.println(findMemeber1 == findMemeber2);

    }
}
