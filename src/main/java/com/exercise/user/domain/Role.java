package com.exercise.user.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "desc")
    private String desc;
    @Column(name = "enable")
    private int enable;
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    public Role() {

    }

    public Role(String name, String desc, int enable, LocalDateTime createTime, LocalDateTime updateTime) {
        this.name = name;
        this.desc = desc;
        this.enable = enable;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
