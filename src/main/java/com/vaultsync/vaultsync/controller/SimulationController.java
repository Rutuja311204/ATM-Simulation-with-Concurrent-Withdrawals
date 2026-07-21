package com.vaultsync.vaultsync.controller;

import java.util.List;

import com.vaultsync.vaultsync.model.ThreadLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vaultsync.vaultsync.model.ThreadLog;
import com.vaultsync.vaultsync.service.SimulationService;

@RestController
@RequestMapping("/simulation")
@CrossOrigin("*")
public class SimulationController {

    @Autowired
    private SimulationService service;

    @PostMapping("/start")
    public String start(@RequestParam String email) {

        service.startSimulation(email);

        return "Simulation Started";
    }

    @GetMapping("/running")
    public int running() {

        return service.getRunningThreads();
    }

    @GetMapping("/waiting")
    public int waiting() {

        return service.getWaitingThreads();
    }

    @GetMapping("/completed")
    public int completed() {

        return service.getCompletedThreads();
    }

    @GetMapping("/blocked")
    public int blocked() {

        return service.getBlockedThreads();
    }

    @GetMapping("/logs")
    public List<ThreadLog> logs() {

        return service.getLogs();
    }

}