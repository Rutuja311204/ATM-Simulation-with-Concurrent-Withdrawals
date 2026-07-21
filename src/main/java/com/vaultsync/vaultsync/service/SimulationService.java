package com.vaultsync.vaultsync.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vaultsync.vaultsync.model.ThreadLog;
import com.vaultsync.vaultsync.service.SimulationService;
import org.springframework.stereotype.Service;
import com.vaultsync.vaultsync.repository.ThreadLogRepository;

@Service
public class SimulationService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ThreadLogRepository logRepository;

    private int runningThreads = 0;
    private int waitingThreads = 0;
    private int completedThreads = 0;
    private int blockedThreads = 0;

    public void startSimulation(String email){

        runningThreads = 3;
        waitingThreads = 0;
        completedThreads = 0;
        blockedThreads = 0;

        Thread t1 = new Thread(() -> {

            log("ATM-01","RUNNING","Waiting For Lock...");
            customerService.withdraw(email,1000);
            log("ATM-01","COMPLETED","Withdraw ₹1000 Successful");
            completedThreads++;

        });

        Thread t2 = new Thread(() -> {

            log("ATM-02","RUNNING","Waiting For Lock...");
            customerService.withdraw(email,1000);
            log("ATM-02","COMPLETED","Withdraw ₹1000 Successful");
            completedThreads++;

        });

        Thread t3 = new Thread(() -> {

            log("ATM-03","RUNNING","Waiting For Lock...");
            customerService.deposit(email,2000);
            log("ATM-03","COMPLETED","Deposit ₹2000 Successful");
            completedThreads++;

        });

        t1.start();
        t2.start();
        t3.start();

    }

    private void log(String thread,String status,String activity){

        ThreadLog l = new ThreadLog();

        l.setThreadName(thread);
        l.setStatus(status);
        l.setActivity(activity);
        l.setLogTime(LocalDateTime.now());

        logRepository.save(l);

    }

    public int getRunningThreads(){

        return runningThreads;

    }

    public int getWaitingThreads(){

        return waitingThreads;

    }

    public int getCompletedThreads(){

        return completedThreads;

    }

    public int getBlockedThreads(){

        return blockedThreads;

    }

    public List<ThreadLog> getLogs(){

        return logRepository.findAllByOrderByLogTimeDesc();

    }

}