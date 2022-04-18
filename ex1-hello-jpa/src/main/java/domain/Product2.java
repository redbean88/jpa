package domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product2 {

    @Id
    @Column(name = "PRODUCT_ID")
    private Long id;
    private String name;

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
}
