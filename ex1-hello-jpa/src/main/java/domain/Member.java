package domain;

import javax.persistence.*;

@Entity(name = "TMember") // JPA가 관리하는 객체
@Table(name = "Member")
public class Member {

	
    public Member() {}

	public Member(Long id, String userName) {
		super();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Team getTeam() {
        return team;
    }


    /**
     * 연관관계 편의 메서드
     * 한쪽에만 만들어 준다
     * @return
     */
    public void setTeam(Team team) {
        this.team = team;

        if(!team.getMembers().contains(this)){
            team.getMembers().add(this);
        }
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
