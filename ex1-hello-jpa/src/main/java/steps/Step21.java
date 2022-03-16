package steps;

import domain.MemberC2;
import domain.TeamC2;

import javax.persistence.EntityManager;

public class Step21 implements Step{

    public void logic(EntityManager em) {

        TeamC2 cTeam = new TeamC2();
        cTeam.setName("TeamA");
        em.persist(cTeam);

        MemberC2 cMember = new MemberC2();
        cMember.setUsername("member1");
        cMember.setTeam(cTeam);
        em.persist(cMember);

        MemberC2 findMember = em.find(MemberC2.class, cMember.getId());
        TeamC2 team = findMember.getTeam();
        System.out.println("team.getId() = " + team.getId());

    }
}
