package com.exercise.user.domain;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Null
    @Column(name = "username")
    private String username;
    @Null
    @Column(name = "password")
    private String password;
    @Null
    @Column(name = "role_id")
    private int roleid;
    @Null
    @Column(name = "email")
    private String email;
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createtime;

    public User() {
    }

    public User(String username, String password, int roleid, String email, LocalDateTime createtime) {
        this.username = username;
        this.password = password;
        this.roleid = roleid;
        this.email = email;
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }
}
