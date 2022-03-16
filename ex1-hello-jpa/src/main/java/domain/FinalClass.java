package domain;

import javax.persistence.*;

@Entity
public final class FinalClass {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
