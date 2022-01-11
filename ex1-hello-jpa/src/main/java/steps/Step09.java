package steps;

import domain.Member;

import javax.persistence.EntityManager;

public class Step09 implements Step {
    public void logic(EntityManager em) {

        // DB 데이터가 없을 경우

        //같은 객체 등록
        Member member2 = new Member();
        member2.setId(1L);
        member2.setName("HelloB");

        em.merge(member2);
        em.flush();
        em.clear();

        //과연?
        Member findMember = em.find(Member.class, 1L);
        System.out.println("findMember.getName() = " + findMember.getName());

    }
}
