package com.vaultsync.vaultsync.model;

public class ThreadMonitorStatus {

    private int runningThreads;

    private int waitingThreads;

    private int completedThreads;

    private int blockedThreads;

    public int getRunningThreads() {
        return runningThreads;
    }

    public void setRunningThreads(int runningThreads) {
        this.runningThreads = runningThreads;
    }

    public int getWaitingThreads() {
        return waitingThreads;
    }

    public void setWaitingThreads(int waitingThreads) {
        this.waitingThreads = waitingThreads;
    }

    public int getCompletedThreads() {
        return completedThreads;
    }

    public void setCompletedThreads(int completedThreads) {
        this.completedThreads = completedThreads;
    }

    public int getBlockedThreads() {
        return blockedThreads;
    }

    public void setBlockedThreads(int blockedThreads) {
        this.blockedThreads = blockedThreads;
    }

}