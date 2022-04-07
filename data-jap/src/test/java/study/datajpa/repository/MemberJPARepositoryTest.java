package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
//@Rollback(value = false)
class MemberJPARepositoryTest {

    @Autowired
    MemberJPARepository memberJPARepository;

    @Test
    public void testMember(){
        Member member = new Member("memberA");
        Member savedMember = memberJPARepository.save(member);

        Member findMember = memberJPARepository.find(savedMember.getId());

        assertThat(findMember.getId()).isEqualTo(savedMember.getId());
        assertThat(findMember.getUsername()).isEqualTo(savedMember.getUsername());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    public void basicCRUD(){
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberJPARepository.save(member1);
        memberJPARepository.save(member2);

        Member findmember1 = memberJPARepository.findById(member1.getId()).get();
        Member findmember2 = memberJPARepository.findById(member2.getId()).get();
        assertThat(findmember1).isEqualTo(member1);
        assertThat(findmember2).isEqualTo(member2);

        List<Member> all = memberJPARepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        long count = memberJPARepository.count();
        assertThat(count).isEqualTo(2);

        memberJPARepository.delete(member1);
        memberJPARepository.delete(member2);

        long deletedCount = memberJPARepository.count();
        assertThat(deletedCount).isEqualTo(0);

    }

    @Test
    public void findByUsernameAndAgeGreaterThen(){
        Member m1 = new Member("member1" , 10);
        Member m2 = new Member("member1" , 15);
        memberJPARepository.save(m1);
        memberJPARepository.save(m2);

        List<Member> result = memberJPARepository.findByUsernameAndAgeGreaterThan("member1", 10);
        assertThat(result.get(0).getUsername()).isEqualTo(m2.getUsername());
        assertThat(result.get(0).getAge()).isEqualTo(m2.getAge());
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void findByUsername(){

        Member m1 = new Member("member1" , 10);
        Member m2 = new Member("member1" , 15);
        memberJPARepository.save(m1);
        memberJPARepository.save(m2);

        List<Member> result = memberJPARepository.findByUsername("member1");
        Member member = result.get(0);
        assertThat(member).isEqualTo(m1);
    }

    @Test
    public void paging(){

        memberJPARepository.save(new Member("member1", 10));
        memberJPARepository.save(new Member("member2", 10));
        memberJPARepository.save(new Member("member3", 10));
        memberJPARepository.save(new Member("member4", 10));
        memberJPARepository.save(new Member("member5", 10));

        int age = 10;
        int offset = 0;
        int limit = 3;

        List<Member> members = memberJPARepository.findByPage(age, offset, limit);
        long totalcount = memberJPARepository.totalcount(10);

        assertThat(members.size()).isEqualTo(3);
        assertThat(totalcount).isEqualTo(5);
    }
}