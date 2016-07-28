package com.sombra.shop.cart.domain;

import com.sombra.shop.good.domain.Good;
import com.sombra.shop.user.domain.CustomUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "carts" )
public class Cart implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column( name = "amount" )
    private int amount;

    @ManyToMany( targetEntity = Good.class, mappedBy = "carts" )
    private Set<Good> goods = new HashSet<>();

    @OneToOne
    @MapsId
    private CustomUser user;

    public Cart() {
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount( int amount ) {
        this.amount = amount;
    }

    public Set<Good> getGoods() {
        return goods;
    }

    public void setGoods( Set<Good> goods ) {
        this.goods = goods;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser( CustomUser user ) {
        this.user = user;
    }

}
