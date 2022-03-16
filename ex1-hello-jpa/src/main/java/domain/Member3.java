package domain;

import javax.persistence.*;

@Entity(name = "Member3") // JPA가 관리하는 객체
@Table(name = "Member3")
public class Member3 {

    private Member3() {
    }

    public Member3(Long id) {
        this.id = id;
    }

    public Member3(String title) {
        this.title = title;
    }

    @Id
    private Long id;    // 타입은 숫자형
    private String title;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
