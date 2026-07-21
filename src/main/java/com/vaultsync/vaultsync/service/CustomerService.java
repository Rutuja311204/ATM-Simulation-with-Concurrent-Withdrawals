package com.vaultsync.vaultsync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaultsync.vaultsync.model.Customer;
import com.vaultsync.vaultsync.model.Transaction;
import com.vaultsync.vaultsync.repository.CustomerRepository;
import com.vaultsync.vaultsync.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private TransactionRepository transactionRepository;

    // ================= REGISTER =================

    public Customer register(Customer customer) {

        customer.setAccountNumber("ACC" + System.currentTimeMillis());
        customer.setBalance(0.0);
        customer.setStatus("Active");

        return repository.save(customer);
    }

    // ================= LOGIN =================

    public Customer login(String email, String pin) {

        Customer customer = repository.findByEmail(email);

        if (customer != null && customer.getPin().equals(pin)) {
            return customer;
        }

        return null;
    }

    // ================= GET ALL CUSTOMERS =================

    public List<Customer> getAllCustomers() {

        return repository.findAll();

    }

    // ================= DEPOSIT =================

    public synchronized Customer deposit(String email, double amount) {

        Customer customer = repository.findByEmail(email);

        if (customer == null)
            return null;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        customer.setBalance(customer.getBalance() + amount);

        repository.save(customer);

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(customer.getAccountNumber());
        transaction.setCustomerName(customer.getFullName());
        transaction.setTransactionType("Deposit");
        transaction.setAmount(amount);
        transaction.setStatus("Success");
        transaction.setRemarks("Cash Deposit");
        transaction.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(transaction);

        return customer;
    }

    // ================= WITHDRAW =================

    public synchronized Customer withdraw(String email, double amount) {

        Customer customer = repository.findByEmail(email);

        if (customer == null)
            return null;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (customer.getBalance() < amount)
            return customer;

        customer.setBalance(customer.getBalance() - amount);

        repository.save(customer);

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(customer.getAccountNumber());
        transaction.setCustomerName(customer.getFullName());
        transaction.setTransactionType("Withdraw");
        transaction.setAmount(amount);
        transaction.setStatus("Success");
        transaction.setRemarks("Cash Withdraw");
        transaction.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(transaction);

        return customer;
    }

    // ================= TRANSFER =================

   public synchronized Customer transfer(String email,
                                      String receiverAccount,
                                      double amount){

    Customer sender = repository.findByEmail(email);

    Customer receiver = repository.findByAccountNumber(receiverAccount);

    if(sender == null || receiver == null){

        return null;

    }

    if(sender.getBalance() < amount){

        return sender;

    }

    try{

        Thread.sleep(3000);

    }catch(Exception e){

        e.printStackTrace();

    }

    sender.setBalance(sender.getBalance() - amount);

    receiver.setBalance(receiver.getBalance() + amount);

    repository.save(sender);

    repository.save(receiver);

    Transaction transaction = new Transaction();

    transaction.setAccountNumber(sender.getAccountNumber());
    transaction.setCustomerName(sender.getFullName());

    transaction.setTransactionType("Transfer");

    transaction.setAmount(amount);

    transaction.setStatus("Success");

    transaction.setRemarks("Transferred To : " + receiver.getAccountNumber());

    transaction.setTransactionDate(java.time.LocalDateTime.now());

    transactionRepository.save(transaction);

    return sender;

}

    // ================= TRANSACTIONS =================

    public List<Transaction> getTransactions(String email) {

        Customer customer = repository.findByEmail(email);

        return transactionRepository.findByAccountNumberOrderByTransactionDateDesc(
                customer.getAccountNumber());

    }

    // ================= TODAY DEPOSIT =================

    public double getTodayDeposit(String email) {

        Customer customer = repository.findByEmail(email);

        return transactionRepository.getTodayDeposit(customer.getAccountNumber());

    }

    // ================= TODAY WITHDRAW =================

    public double getTodayWithdraw(String email) {

        Customer customer = repository.findByEmail(email);

        return transactionRepository.getTodayWithdraw(customer.getAccountNumber());

    }

    // ================= CUSTOMER DETAILS =================

    public Customer getCustomerByEmail(String email) {

        return repository.findByEmail(email);

    }
    public Customer getCustomerById(Integer id){

    return repository.findById(id).orElse(null);

}

public Customer updateCustomer(Integer id, Customer updatedCustomer){

    Customer customer = repository.findById(id).orElse(null);

    if(customer == null){

        return null;

    }

    customer.setFullName(updatedCustomer.getFullName());
    customer.setPhone(updatedCustomer.getPhone());
    customer.setAddress(updatedCustomer.getAddress());
    customer.setAccountType(updatedCustomer.getAccountType());
    customer.setStatus(updatedCustomer.getStatus());

    return repository.save(customer);

}

public void deleteCustomer(Integer id){

    repository.deleteById(id);

}

public List<Transaction> getAllTransactions(){

    return transactionRepository.findAllByOrderByTransactionDateDesc();

}

}