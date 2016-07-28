package com.sombra.shop.picture.domain;

import com.sombra.shop.good.domain.Good;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "pictures" )
public class Picture implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column( name = "image_as_base64", length = 1_000_000 )
    private String image;

    @ManyToOne
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

    public String getImage() {
        return image;
    }

    public void setImage( String image ) {
        this.image = image;
    }

    public Good getGood() {
        return good;
    }

    public void setGood( Good good ) {
        this.good = good;
    }

}
