package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TeamC3 {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private long id;
    private String name;

    @OneToMany(mappedBy = "team")
    List<MemberC3> members = new ArrayList<MemberC3>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MemberC3> getMembers() {
        return members;
    }

    public void setMembers(List<MemberC3> members) {
        this.members = members;
    }
}
