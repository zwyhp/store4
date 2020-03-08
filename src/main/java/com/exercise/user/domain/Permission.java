package com.exercise.user.domain;


import com.exercise.util.verify.VerifyError;
import com.exercise.util.verify.VerifyUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue
    @NotNull(message = VerifyError.ID_NOT_NULL ,groups = {VerifyUpdate.class})
    @Column(name = "id")
    private int id;
    @NotNull(message = VerifyError.PER_ROLE_NOT_NULL)
    @Column(name = "role_id")
    private int roleId;
    @NotEmpty(message = VerifyError.PER_SITE_NOT_NULL)
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
        this.roleId = role_id;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public void copy(Permission permission){
        this.roleId = permission.roleId;
        this.permission = permission.permission;
    }
}
