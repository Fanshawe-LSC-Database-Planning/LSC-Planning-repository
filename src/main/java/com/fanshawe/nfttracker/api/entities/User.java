package com.fanshawe.nfttracker.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.lang.Strings;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "user" )
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

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

    public void setDateUserAdded(Date dateUserAdded) {
        this.dateUserAdded = dateUserAdded;
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
