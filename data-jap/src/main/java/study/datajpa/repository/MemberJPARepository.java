package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import study.datajpa.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberJPARepository {

    @PersistenceContext
    private  EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public void delete(Member member){
        em.remove(member);
    }

    public List<Member> findAll(){
        //JPQL
        return em.createQuery("select m from Member m",Member.class).getResultList();
    }

    public long count(){
        return em.createQuery("select count(m) from Member m",Long.class).getSingleResult();
    }

    public Optional<Member> findById(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }


    public List<Member> findByUsernameAndAgeGreaterThan(String username , int age){
        return em.createQuery("select m from Member m where m.username = :username and m.age > :age")
                .setParameter("username" ,username)
                .setParameter("age", age)
                .getResultList();
    }

    public List<Member> findByUsername(String username){
        return em.createNamedQuery("Member.findByUsername", Member.class)
                .setParameter("username" , username)
                .getResultList();
    }

    public List<Member> findByPage(int age , int offset, int limit){
        return em.createQuery("select m from Member m where m.age = :age order by m.username desc")
                .setParameter("age" , age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public long totalcount(int age){
        return em.createQuery("select count(m) from Member m where m.age = :age ", Long.class)
                .setParameter("age", age)
                .getSingleResult();
    }

    public int bulkAgePlus(int age){
        return em.createQuery("update Member m set m.age = m.age + 1 where m.age >= :age ")
                .setParameter("age" , age)
                .executeUpdate();
    }

    public List<Member> findMemberJoinFetch(){
        return em.createQuery("select m from Member m join fetch m.team", Member.class)
                .getResultList();
    }
}
