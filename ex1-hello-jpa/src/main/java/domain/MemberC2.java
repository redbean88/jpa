package domain;

import javax.persistence.*;

@Entity
public class MemberC2 {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private TeamC2 team;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public TeamC2 getTeam() {
        return team;
    }

    public void setTeam(TeamC2 team) {
        this.team = team;
    }
}
