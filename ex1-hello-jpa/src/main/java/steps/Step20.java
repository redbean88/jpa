package steps;

import domain.MemberC;
import domain.TeamC;

import javax.persistence.EntityManager;

public class Step20 implements Step {

    public void logic(EntityManager em) {

        TeamC cTeam = new TeamC();
        cTeam.setName("TeamA");
        em.persist(cTeam);

        MemberC cMember = new MemberC();
        cMember.setUsername("member1");
        cMember.setTeamId(cTeam.getId());
        em.persist(cMember);

        MemberC findMember = em.find(MemberC.class, cMember.getId());
        long teamId = findMember.getTeamId();
        TeamC findTeam = em.find(TeamC.class, teamId);
    }
}
