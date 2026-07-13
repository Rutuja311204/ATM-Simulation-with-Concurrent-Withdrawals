package com.vaultsync.vaultsync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vaultsync.vaultsync.model.Customer;
import com.vaultsync.vaultsync.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/register")
public Customer register(@RequestBody Customer customer) {

    try {
        return service.register(customer);
    } catch (Exception e) {
        e.printStackTrace();
        throw e;
    }

}

    @PostMapping("/login")
    public Customer login(@RequestParam String email,
                          @RequestParam String pin) {

        return service.login(email, pin);
    }

   @GetMapping("/{email}")
public Customer getCustomer(@PathVariable String email) {
    return service.getCustomerByEmail(email);
}
}