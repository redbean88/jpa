import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
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
}
