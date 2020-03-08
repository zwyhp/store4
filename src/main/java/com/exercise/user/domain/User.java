package com.exercise.user.domain;


import com.exercise.util.verify.VerifyError;
import com.exercise.util.verify.VerifyUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @NotNull(message = VerifyError.ID_NOT_NULL ,groups = {VerifyUpdate.class})
    @Column(name = "id")
    private int id;
    @NotEmpty(message = VerifyError.USER_NAME_NOT_NULL)
    @Column(name = "username")
    private String username;
    @NotEmpty(message = VerifyError.USER_PWD_NOT_NULL)
    @Column(name = "password")
    private String password;
    @NotNull(message = VerifyError.ROLE_ID_NOT_NULL)
    @Column(name = "role_id")
    private int roleId;
    @Email
    @NotEmpty(message = VerifyError.EMAIl_NOT_NULL)
    @Column(name = "email")
    private String email;
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    public User() {
    }

    public User(String username, String password, int roleid, String email, LocalDateTime createtime) {
        this.username = username;
        this.password = password;
        this.roleId = roleid;
        this.email = email;
        this.createTime = createtime;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void copy(User user){
        this.username = user.username;
        this.password = user.password;
        this.roleId = user.roleId;
        this.email = user.email;
    }
}
