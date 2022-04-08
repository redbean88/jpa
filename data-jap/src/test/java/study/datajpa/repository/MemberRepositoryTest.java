package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.domain.Member;
import study.datajpa.domain.Team;
import study.datajpa.dto.MemberDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    public void testMember() {
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).orElseGet(() -> new Member("default"));

        assertThat(findMember.getId()).isEqualTo(savedMember.getId());
        assertThat(findMember.getUsername()).isEqualTo(savedMember.getUsername());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Member findmember1 = memberRepository.findById(member1.getId()).get();
        Member findmember2 = memberRepository.findById(member2.getId()).get();
        assertThat(findmember1).isEqualTo(member1);
        assertThat(findmember2).isEqualTo(member2);

        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        memberRepository.delete(member1);
        memberRepository.delete(member2);

        long deletedCount = memberRepository.count();
        assertThat(deletedCount).isEqualTo(0);

    }


    @Test
    public void findByUsernameAndAgeGreaterThen() {
        Member m1 = new Member("member1", 10);
        Member m2 = new Member("member1", 15);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("member1", 10);
        assertThat(result.get(0).getUsername()).isEqualTo(m2.getUsername());
        assertThat(result.get(0).getAge()).isEqualTo(m2.getAge());
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void findByUsername() {

        Member m1 = new Member("member1", 10);
        Member m2 = new Member("member1", 15);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsername("member1");
        Member member = result.get(0);
        assertThat(member).isEqualTo(m1);
    }

    @Test
    public void findUser() {

        Member m1 = new Member("member1", 10);
        Member m2 = new Member("member2", 15);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findUser("member1", 10);
        Member member = result.get(0);
        assertThat(member).isEqualTo(m1);
    }

    @Test
    public void findUsernames() {

        Member m1 = new Member("member1", 10);
        Member m2 = new Member("member2", 15);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> result = memberRepository.findUsernames();
        result.forEach(System.out::println);
    }

    @Test
    public void findMemberDto() {
        Team team = new Team("TeamA");
        teamRepository.save(team);

        Member m1 = new Member("member1", 10);
        m1.setTeam(team);
        memberRepository.save(m1);


        List<MemberDto> result = memberRepository.findMemberDto();
        result.forEach(System.out::println);
    }

    @Test
    public void findByNames() {

        Member m1 = new Member("member1", 10);
        Member m2 = new Member("member2", 15);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("member1", "member2"));
        result.forEach(System.out::println);
    }

    @Test
    public void findReturnType() {

        Member m1 = new Member("member1", 10);
        Member m2 = new Member("member2", 15);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findListByUsername("member1");
        result.forEach(System.out::println);

        Member member1 = memberRepository.findMemberByUsername("member1");
        System.out.println("member1 = " + member1);

        Optional<Member> optionalMember = memberRepository.findOptionalByUsername("member1");
        System.out.println("optionalMember = " + optionalMember.get());

    }

    @Test
    public void paging() {

        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        int offset = 0;
        int limit = 3;

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        Page<Member> page = memberRepository.findByAge(age, pageRequest);

        List<Member> members = page.getContent();
        assertThat(members.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
    }

    @Test
    public void optimaziedpaging() {

        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        int offset = 0;
        int limit = 3;

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        Page<Member> page = memberRepository.findOptimizedByAge(age, pageRequest);
        page.map(m -> new MemberDto(m.getId(), m.getUsername(), null));

        List<Member> members = page.getContent();
        assertThat(members.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
    }

    @Test
    public void paging4slice() {

        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        int offset = 0;
        int limit = 3;

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        Slice<Member> page = memberRepository.findSliceByAge(age, pageRequest);

        List<Member> members = page.getContent();
        assertThat(members.size()).isEqualTo(3);
//        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0);
//        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
    }

    @Test
    public void bulkUpdate() {
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 29));
        memberRepository.save(new Member("member4", 39));
        memberRepository.save(new Member("member5", 49));

        int resultCount = memberRepository.bulkAgePlus(20);

        List<Member> member5 = memberRepository.findByUsername("member5");
        assertThat(resultCount).isEqualTo(3);
        assertThat(member5.get(0).getAge()).isEqualTo(50);
    }

    @Test
    public void findMemberLazy() {

        Team team1 = new Team("TeamA");
        Team team2 = new Team("TeamB");
        teamRepository.save(team1);
        teamRepository.save(team2);

        Member member1 = new Member("member1", 10, team1);
        Member member2 = new Member("member2", 12, team2);
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> findAll = memberRepository.findAll();

        findAll.forEach(member -> {
            System.out.println("member.getUsername() = " + member.getUsername());
            System.out.println("member.getTeam().getClass() = " + member.getTeam().getClass());
            System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
        });

        List<Member> entityGraphByUsername = memberRepository.findEntityGraphByUsername("member2");
        System.out.println("entityGraphByUsername.get(0).getTeam().getName() = " + entityGraphByUsername.get(0).getTeam().getName());
    }

    @Test
    public void queryHint() {
        Member member = new Member("member1", 10);
        memberRepository.save(member);
        em.flush();
        em.clear();

        Member findMember = memberRepository.findReadOnlyByUsername("member1");
        findMember.setUsername("member2");

        em.flush();;
    }
    @Test
    public void lock() {
        Member member = new Member("member1", 10);
        memberRepository.save(member);
        em.flush();
        em.clear();

        Member findMember = memberRepository.findLockByUsername("member1");
    }
    
    @Test
    public void memberCustom() {
        Member member = new Member("member1", 10);
        memberRepository.save(member);
        em.flush();
        em.clear();

        List<Member> memberCustom = memberRepository.findMemberCustom();
    }
}