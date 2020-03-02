package com.exercise.user.domain;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "role_id")
    private int role_id;
    @Column(name = "permission")
    private String permission;
    @Column(name = "add_time")
    private LocalDateTime addTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    public Permission() {

    }
    public Permission(int id, int role_id, String permission, LocalDateTime addTime, LocalDateTime updateTime) {
        this.id = id;
        this.role_id = role_id;
        this.permission = permission;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
