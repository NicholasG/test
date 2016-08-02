package com.sombra.shop.category.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sombra.shop.good.domain.Good;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "categories" )
public class Category implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column( name = "name" )
    private String name;

    @JsonIgnore
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "category" )
    private Set<Good> goods = new HashSet<>();

    public Category( String name ) {
        this.name = name;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Set<Good> getGoods() {
        return goods;
    }

    public void setGoods( Set<Good> goods ) {
        this.goods = goods;
    }

}
