package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SubClass {

    @Id
    Long sub_id;

    public Long getSub_id() {
        return sub_id;
    }

    public void setSub_id(Long sub_id) {
        this.sub_id = sub_id;
    }
}
