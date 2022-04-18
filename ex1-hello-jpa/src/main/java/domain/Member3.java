package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // JPA가 관리하는 객체
public class Member3 {


    public Member3() {}

    public Member3(String userName) {
        this.userName = userName;
    }

    public Member3(Long id, String userName) {
		this.id = id;
		this.userName = userName;
	}

    @Id
    @Column(name = "MEMBER_ID")
    private Long id;    // 타입은 숫자형

    @Column(name = "NAME" )
    private String userName;
    private int age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public String getUserName() {
        return userName;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
