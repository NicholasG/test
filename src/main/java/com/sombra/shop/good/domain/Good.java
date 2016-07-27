package com.sombra.shop.good.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sombra.shop.cart.domain.Cart;
import com.sombra.shop.picture.domain.Picture;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "goods" )
public class Good {

    @Id
    @GeneratedValue
    private Long id;

    @Column( name = "name" )
    private String name;

    @Column( name = "description" )
    private String description;

    @Column( name = "price" )
    private Double price;

    @Column( name = "available" )
    private boolean available;

    @JsonIgnore
    @ManyToMany( targetEntity = Cart.class )
    private Set<Cart> carts = new HashSet<>();

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "good" )
    private Set<Picture> pictures = new HashSet<>();

    public Good() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice( Double price ) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable( boolean available ) {
        this.available = available;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts( Set<Cart> carts ) {
        this.carts = carts;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures( Set<Picture> pictures ) {
        this.pictures = pictures;
    }

}
