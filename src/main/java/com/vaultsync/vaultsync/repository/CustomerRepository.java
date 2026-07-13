package com.vaultsync.vaultsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vaultsync.vaultsync.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmail(String email);

}