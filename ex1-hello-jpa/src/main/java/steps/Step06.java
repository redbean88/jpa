package steps;

import domain.Member;

import javax.persistence.EntityManager;

/**
 * 플러시
 * 쓰기 지연 SQL 저장소 내용이 DB에 반영 (동기화)
 * 영속성 컨텍스트를 비우지 않음
 *
 * 발생 시점
 * em.flush() - 직접호출
 * 트랜젝션 커밋 - 플러시 자동 호출
 * JPQL 코드 실행 - 플러시 자동 호출
 */
public class Step06 implements Step {
    public void logic(EntityManager em) {

//        em.setFlushMode(FlushModeType.AUTO);    //기본값
//        em.setFlushMode(FlushModeType.COMMIT);    //커밋시에만

        Member member1 = new Member(150L , "A");

        em.flush();
        em.clear();
        System.out.println("============DB에 반영 완료===============");

        Member findMember = em.find(Member.class, 150L);
        findMember.setName("zzzzz");


    }
}
