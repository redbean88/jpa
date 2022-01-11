package steps;

import domain.Member;

import javax.persistence.EntityManager;

public class Step08 implements Step {
    public void logic(EntityManager em) {

        // DB 저장되어있을 경우
        // 비영속 상태 (new)
        Member member = new Member();
        member.setId(1L);
        member.setName("HelloA");

        //영속 상태 (managed)
        em.persist(member);

        em.flush();
        em.clear();

        //같은 객체 등록
        Member member2 = new Member();
        member2.setId(1L);
        member2.setName("HelloB");

        em.merge(member2);

        //과연?
        Member findMember = em.find(Member.class, 1L);
        System.out.println("findMember.getName() = " + findMember.getName());

    }
}
