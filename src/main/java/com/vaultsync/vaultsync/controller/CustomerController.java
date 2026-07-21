package com.vaultsync.vaultsync.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vaultsync.vaultsync.model.Customer;
import com.vaultsync.vaultsync.model.Transaction;
import com.vaultsync.vaultsync.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerService service;

    // ================= REGISTER =================

    @PostMapping("/register")
    public Customer register(@RequestBody Customer customer) {

        return service.register(customer);

    }

    // ================= LOGIN =================

    @PostMapping("/login")
    public Customer login(@RequestParam String email,
                          @RequestParam String pin) {

        return service.login(email, pin);

    }

    // ================= ALL CUSTOMERS =================

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {

        return service.getAllCustomers();

    }

    // ================= CUSTOMER DETAILS =================

    @GetMapping("/{email}")
    public Customer getCustomer(@PathVariable String email) {

        return service.getCustomerByEmail(email);

    }

    // ================= DEPOSIT =================

    @PostMapping("/deposit")
    public Customer deposit(@RequestParam String email,
                            @RequestParam double amount) {

        return service.deposit(email, amount);

    }

    // ================= WITHDRAW =================

    @PostMapping("/withdraw")
    public Customer withdraw(@RequestParam String email,
                             @RequestParam double amount) {

        return service.withdraw(email, amount);

    }

    // ================= TRANSFER =================
    @PostMapping("/transfer")
public Customer transfer(@RequestParam String email,
                         @RequestParam String receiverAccount,
                         @RequestParam double amount){

    return service.transfer(email, receiverAccount, amount);

}

    // ================= TRANSACTION HISTORY =================

    @GetMapping("/transactions/{email}")
    public List<Transaction> getTransactions(@PathVariable String email) {

        return service.getTransactions(email);

    }

    @GetMapping("/transactions")
public List<Transaction> getAllTransactions(){

    return service.getAllTransactions();

}

    // ================= TODAY'S DEPOSIT =================

    @GetMapping("/todayDeposit/{email}")
    public double todayDeposit(@PathVariable String email) {

        return service.getTodayDeposit(email);

    }

    // ================= TODAY'S WITHDRAW =================

    @GetMapping("/todayWithdraw/{email}")
    public double todayWithdraw(@PathVariable String email) {

        return service.getTodayWithdraw(email);

    }

    @GetMapping("/id/{id}")
public Customer getCustomerById(@PathVariable Integer id){

    return service.getCustomerById(id);

}

@PutMapping("/update/{id}")
public Customer updateCustomer(@PathVariable Integer id,
                               @RequestBody Customer customer){

    return service.updateCustomer(id, customer);

}

@DeleteMapping("/delete/{id}")
public void deleteCustomer(@PathVariable Integer id){

    service.deleteCustomer(id);

}

}