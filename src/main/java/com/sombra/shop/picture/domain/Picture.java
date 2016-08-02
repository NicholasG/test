package com.sombra.shop.picture.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sombra.shop.good.domain.Good;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "pictures" )
public class Picture implements Serializable {

    @Id
    private Long id;

    @Column( name = "image_as_base64", length = 1_000_000 )
    private String imageAsString;

    @ManyToOne
    @JsonIgnore
    @JoinColumn( name = "good_id", nullable = false )
    private Good good;

    public Picture() {
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getImageAsString() {
        return imageAsString;
    }

    public void setImageAsString( String imageAsString ) {
        this.imageAsString = imageAsString;
    }

    public Good getGood() {
        return good;
    }

    public void setGood( Good good ) {
        this.good = good;
    }

}
