package com.vaultsync.vaultsync.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaultsync.vaultsync.model.ThreadLog;

public interface ThreadLogRepository extends JpaRepository<ThreadLog,Integer>{

    List<ThreadLog> findAllByOrderByLogTimeDesc();

}