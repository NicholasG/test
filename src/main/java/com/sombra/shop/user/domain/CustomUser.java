package com.sombra.shop.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "users" )
public class CustomUser implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column( name = "username", nullable = false, unique = true )
    private String username;

    @Column( name = "first_name" )
    private String firstName;

    @Column( name = "last_name" )
    private String lastName;

    @JsonIgnore
    @Column( name = "password" )
    private String password;

    @Column( name = "role" )
    private String role;

    @Column( name = "active" )
    private boolean active = true;

    public CustomUser() {
    }

    public CustomUser( String username, String firstName, String lastName, String password, String role, boolean active ) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole( String role ) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive( boolean active ) {
        this.active = active;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper( this )
                .add( "id", id )
                .add( "username", username )
                .add( "firstName", firstName )
                .add( "lastName", lastName )
                .add( "password", password )
                .add( "role", role )
                .add( "active", active )
                .toString();
    }
}
