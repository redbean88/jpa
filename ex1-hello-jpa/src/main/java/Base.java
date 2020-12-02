import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Base {

    /**
     * JPA 기본 사용법
     */
    public void step00(){
        //하나만 생성 ( DB당 )
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //프랜젝션 단위별로 manager 생성 (쓰레드간에 공유 금지)
        EntityManager em = emf.createEntityManager();

        // 트렌젠션 안에서 데이터 처리
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //insert
        try {

            // 비영속 상태 (new)
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");

            //영속 상태 (managed)
            em.persist(member);
            Member findMemeber = em.find(Member.class,1L);
            System.out.println(findMemeber.getId());

            //준 영속상태(detached)
            //em.detach(findMemeber);

            //삭제 (remove)
            //em.remove(findMemeber);

            // 쿼리 적용
            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            // em.close();
        }

        //select
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        try{
            Member findMemeber = em.find(Member.class,1L);
            System.out.println(findMemeber.getId());
            tx2.commit();
        }catch (Exception e){
            tx2.rollback();
        }finally {
            em.close();
        }


        //update
        EntityTransaction tx3 = em.getTransaction();
        tx3.begin();
        try{
            Member findMemeber = em.find(Member.class,1L);
            findMemeber.setName("HelloJPA");
            tx.commit();
        }catch (Exception e){
            tx3.rollback();
        }finally {
            em.close();
        }

        //delete
        EntityTransaction tx4 = em.getTransaction();
        tx4.begin();
        try{
            Member findMemeber = em.find(Member.class,1L);
            em.remove(findMemeber);
        }catch (Exception e){
            tx4.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }


    /**
     * 1차 캐시 예제
     */
    public void step01(){

        //하나만 생성 ( DB당 )
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //프랜젝션 단위별로 manager 생성 (쓰레드간에 공유 금지)
        EntityManager em = emf.createEntityManager();

        // 트렌젠션 안에서 데이터 처리
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            em.persist(member);
            
            Member findMemeber = em.find(Member.class,101L); // 1차 캐시 사용
            System.out.println(String.format("findMemeber.id = %s",findMemeber.getId()));
            System.out.println(String.format("findMemeber.name = %s",findMemeber.getName()));

        }catch (Exception e){

        }finally {
            em.close();
        }
        emf.close();
    }

    /**
     * 영속 엔티티의 동일성 보장 예제
     */
    public void step02(){

        //하나만 생성 ( DB당 )
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //프랜젝션 단위별로 manager 생성 (쓰레드간에 공유 금지)
        EntityManager em = emf.createEntityManager();

        // 트렌젠션 안에서 데이터 처리
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            em.persist(member);

            Member findMemeber1 = em.find(Member.class,101L); // 1차 캐시 사용
            Member findMemeber2 = em.find(Member.class,101L); // 1차 캐시 사용

            System.out.println(findMemeber1 == findMemeber2);

        }catch (Exception e){

        }finally {
            em.close();
        }
        emf.close();
    }


    /**
     * 트랜젝션을 지원하는 쓰기 지연 (버퍼링 기능)
     */
    public void step03(){

        //하나만 생성 ( DB당 )
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //프랜젝션 단위별로 manager 생성 (쓰레드간에 공유 금지)
        EntityManager em = emf.createEntityManager();

        // 트렌젠션 안에서 데이터 처리
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member(150L , "A");
            Member member2 = new Member(160L , "B");

            em.persist(member1);
            em.persist(member2);

            System.out.println("===============================");

            tx.commit();
        }catch (Exception e){

        }finally {
            em.close();
        }
        emf.close();
    }

 /**
     * 변견감지 (더티채킹)
     */
    public void step04(){

        //하나만 생성 ( DB당 )
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //프랜젝션 단위별로 manager 생성 (쓰레드간에 공유 금지)
        EntityManager em = emf.createEntityManager();

        // 트렌젠션 안에서 데이터 처리
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = em.find(Member.class, 150L);
            member.setName("zzzzz");

            System.out.println("===================================-");

            tx.commit();
        }catch (Exception e){

        }finally {
            em.close();
        }
        emf.close();
    }


}
