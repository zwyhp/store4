package com.exercise.log.domin;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log")
public class LogDomin {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "u_name")
    private String uName;
    @Column(name = "action")
    private String action;
    @Column(name = "time")
    private LocalDateTime time;

    public LogDomin() {

    }
    public LogDomin(int id, String uName, String action, LocalDateTime time) {
        this.id = id;
        this.uName = uName;
        this.action = action;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
