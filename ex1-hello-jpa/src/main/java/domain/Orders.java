package domain;

import javax.persistence.*;

@Entity
public class Orders {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member3 member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product3 product;

    private int orderAmout;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member3 getMember() {
        return member;
    }

    public void setMember(Member3 member) {
        this.member = member;
    }

    public Product3 getProduct() {
        return product;
    }

    public void setProduct(Product3 product) {
        this.product = product;
    }

    public int getOrderAmout() {
        return orderAmout;
    }

    public void setOrderAmout(int orderAmout) {
        this.orderAmout = orderAmout;
    }
}
