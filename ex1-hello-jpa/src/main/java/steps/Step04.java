package steps;

import domain.Member;

import javax.persistence.EntityManager;

/**
 * 변경감지 (더티채킹)
 */
public class Step04 implements Step {
    public void logic(EntityManager em) {

        Member member1 = new Member(150L , "A");
        em.persist(member1);

        em.flush();
        em.clear();
        System.out.println("============DB에 반영 완료===============");

        Member findMember = em.find(Member.class, 150L);
        findMember.setName("zzzzz");

    }
}
