package steps;

import domain.Member;
import domain.Team;

import javax.persistence.EntityManager;

public class Step10 implements Step {
    public void logic(EntityManager em) {

        //일대다 단방향

        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        Team team1 = new Team("Team1");
        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        em.persist(member1);
        em.persist(member2);
        em.persist(team1);
    }
}
