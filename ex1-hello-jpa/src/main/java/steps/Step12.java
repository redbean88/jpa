package steps;

import domain.FinalClass;
import domain.Member;
import domain.Member3;
import domain.SubClass;

import javax.persistence.EntityManager;

public class Step12 implements Step {
    public void logic(EntityManager em) {

        // 지연로딩 안됨

        //같은 객체 등록
        FinalClass finalClass = new FinalClass();
        finalClass.setId(1L);
        em.persist(finalClass);
        em.flush();
        em.clear();

        FinalClass finalClass1 = em.getReference(FinalClass.class, finalClass.getId());

    }
}
