package com.vaultsync.vaultsync.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vaultsync.vaultsync.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByAccountNumberOrderByTransactionDateDesc(String accountNumber);

    List<Transaction> findAllByOrderByTransactionDateDesc();

    @Query("SELECT COALESCE(SUM(t.amount),0) FROM Transaction t WHERE t.accountNumber=?1 AND t.transactionType='Deposit'")
    double getTodayDeposit(String accountNumber);

    @Query("SELECT COALESCE(SUM(t.amount),0) FROM Transaction t WHERE t.accountNumber=?1 AND t.transactionType='Withdraw'")
    double getTodayWithdraw(String accountNumber);

}