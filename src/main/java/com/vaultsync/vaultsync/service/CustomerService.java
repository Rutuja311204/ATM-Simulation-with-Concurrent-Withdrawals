package com.vaultsync.vaultsync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaultsync.vaultsync.model.Customer;
import com.vaultsync.vaultsync.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer register(Customer customer) {

    customer.setAccountNumber("ACC" + System.currentTimeMillis());

    customer.setBalance(0.0);

    customer.setStatus("Active");

    return repository.save(customer);
}

    public Customer login(String email, String pin) {

        Customer customer = repository.findByEmail(email);

        if (customer != null && customer.getPin().equals(pin)) {
            return customer;
        }

        return null;
    }

    public Customer getCustomerByEmail(String email) {
    return repository.findByEmail(email);
}
}