package steps;

import domain.Member;

import javax.persistence.EntityManager;

/**
 * 트랜젝션을 지원하는 쓰기 지연 (버퍼링 기능)
 */
public class Step03 implements Step {
    public void logic(EntityManager em) {

        Member member1 = new Member(150L , "A");
        Member member2 = new Member(160L , "B");

        em.persist(member1);
        em.persist(member2);

        System.out.println("============이선 이후로 DB에 반영한다===============");

    }
}
