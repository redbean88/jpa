package jpabook.jpahop;


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
        try{
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
            emf.close();
    }


}
