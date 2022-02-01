package steps;

import domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

public class Step00 implements Step {
    public void logic(EntityManager em) {

        Long id = 1L;
        Member member = new Member();
        member.setId(id);
        member.setName("길동");
        member.setAge(20);

        //등록
        em.persist(member);

        //수정
        member.setAge(30);

        //한 건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember = " + findMember.getName());
        System.out.println("findMember.getAge() = " + findMember.getAge());

        //목록 조회
        List<Member> member_m = em.createQuery("select m from TMember m", Member.class).getResultList();
        System.out.println("member_m = " + member_m);
        System.out.println("member.size = " + member_m.size());

        //삭제
        //em.remove(member);
    }
}
