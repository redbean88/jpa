package steps;

import domain.Member2;

import javax.persistence.EntityManager;

public class Step04 implements Step {
    public void logic(EntityManager em) {

        // DB 데이터가 없을 경우

        //같은 객체 등록
        Member2 member2 = new Member2();
        member2.setId(2L);
        member2.setName("HelloB");

        em.merge(member2);
        em.flush();
        em.clear();

        //과연?
        Member2 findMember2 = em.find(Member2.class, 2L);
        System.out.println("findMember2 = " + findMember2);


        Member2 findMember1 = em.find(Member2.class, 1L);
        System.out.println("findMember1 = " + findMember1);

    }
}
