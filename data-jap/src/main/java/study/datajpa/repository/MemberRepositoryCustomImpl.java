package study.datajpa.repository;

import study.datajpa.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{

    EntityManager em;

    public MemberRepositoryCustomImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m").getResultList();
    }
}
