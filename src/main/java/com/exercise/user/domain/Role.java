package com.exercise.user.domain;

import com.exercise.util.verify.VerifyError;
import com.exercise.util.verify.VerifyUpdate;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "srole")
public class Role {
    @Id
    @GeneratedValue
    @NotNull(message = VerifyError.ID_NOT_NULL ,groups = {VerifyUpdate.class})
    @Column(name = "id")
    private int id;

    @NotEmpty(message = VerifyError.ROLE_NAME_NOT_NULL)
    @Column(name = "rname")
    private String name;

    @NotEmpty(message = VerifyError.ROLE_DESC_NOT_NULL)
    @Column(name = "rdesc")
    private String desc;

    @Column(name = "renable")
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

    public void copy(Role role){
        this.name = role.name;
        this.desc = role.desc;
        this.enable = role.enable;
    }
}
