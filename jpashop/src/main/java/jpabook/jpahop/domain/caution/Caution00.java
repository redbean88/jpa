package jpabook.jpahop.domain.caution;

import javax.persistence.*;


/**
 * 양방향 연관관계 주의
 * 연관관계 주인에 값을 할당해도 JPA에서는
 * 처리가 진행되지 않지만
 * 순수 객체 상태를 고려해서 항상 양쪽에 값을 설정
 * tip - 연관관계 편의 메소드를 추가
 *
 * 무한 루프를 조심
 * toString(), lombok , JSON 생성 라이브러리(엔티티 반환 금지 , DTO로 반환처리)
 */


public class Caution00 {

    public Caution00() {

        //하나만 생성 ( DB당 )
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //프랜젝션 단위별로 manager 생성 (쓰레드간에 공유 금지)
        EntityManager em = emf.createEntityManager();

        // 트렌젠션 안에서 데이터 처리
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            new Team();

            System.out.println("======================");
            //회원저장
            Member member = new Member();
            member.setName("member1");
            member.setTeamId(team);
           // member.setTeamId(team.getId());
            em.persist(member);

            System.out.println("======================");

            //조회
            Member findMember = em.find(Member.class, member.getId());
            System.out.println(String.format("findMember >> %s",findMember.getId()));
            System.out.println(String.format("findMemberName >> %s",findMember.getName()));

            Team findTeam = findMember.getTeamId();
            System.out.println(String.format("findTeam >> %s",findTeam.getName()));


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();



    }

}
