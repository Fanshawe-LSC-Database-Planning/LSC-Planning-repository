package com.fanshawe.nfttracker.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private Date dateUserAdded;

    public Date getDateUserAdded() {
        return dateUserAdded;
    }
    public Date setDateUserAdded(Date dateUserAdded) {
        this.dateUserAdded = dateUserAdded;
        return dateUserAdded;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", userId=" + userId
                + ", dateUserAdded=" + dateUserAdded + "]";
    }


}
