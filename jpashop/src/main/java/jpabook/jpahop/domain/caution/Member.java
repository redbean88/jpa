package jpabook.jpahop.domain.caution;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "USERNMAE")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team teamId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeamId() {
        return teamId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
    }
}
