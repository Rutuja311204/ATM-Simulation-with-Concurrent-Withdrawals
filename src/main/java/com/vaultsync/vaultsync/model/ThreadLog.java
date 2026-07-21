package com.vaultsync.vaultsync.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "thread_logs")
public class ThreadLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer logId;

    @Column(name = "thread_name")
    private String threadName;

    @Column(name = "operation")
    private String activity;

    @Column(name = "status")
    private String status;

    @Column(name = "log_time")
    private LocalDateTime logTime;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

}