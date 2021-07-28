package com.fanshawe.nfttracker.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.lang.Strings;

import javax.persistence.*;
import java.util.Date;
@Entity(name = "user" )
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;


    private Date CreationDate = new Date();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean validateMandatoryParameters() {
        if (Strings.hasText(getUsername()) && Strings.hasText(getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Professor [Id=" + Id + ", username=" + username + ", password=" + password
                + ", CreationDate=" + CreationDate
                + "]";
    }


}
