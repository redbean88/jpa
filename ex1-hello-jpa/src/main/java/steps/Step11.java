package steps;

import domain.Locker;
import domain.Member;
import domain.Team;

import javax.persistence.EntityManager;

public class Step11 implements Step {
    public void logic(EntityManager em) {

        //일대다 단방향

        Member member1 = new Member("member1");
        Locker locker1 = new Locker("locker1");

        member1.setLocker(locker1);

        em.persist(member1);
        em.persist(locker1);
    }
}
