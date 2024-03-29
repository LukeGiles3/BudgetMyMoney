package com.example.budgets.service;

import com.example.budgets.entity.Transaction;
import com.example.budgets.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {
    public static TransactionRepository transactionRepo;

    @Autowired
    public TransactionService(TransactionRepository repo) {
        TransactionService.transactionRepo = repo;
    }

    public static List<Transaction> getAllTransactions() { return transactionRepo.findAll(); }

    public static void createNewTransaction(Transaction transaction) { transactionRepo.save(transaction); }

    public static void editTransaction(Long id, String name, float amount, Long categoryID) {
        Optional<Transaction> transactionToUpdate = transactionRepo.findById(id);
        Transaction transaction = transactionToUpdate.get();
        transaction.setTransactionName(name);
        transaction.setTransactionAmount(amount);
        transaction.setCategoryID(categoryID);
        transactionRepo.save(transaction);
    }

    public static Optional<Transaction> getTransactionByID(Long id) {
        return transactionRepo.findById(id);
    }

    public static void deleteTransaction(Long id) {
        transactionRepo.deleteById(id);
    }

    public static void deleteAllTransactions() { transactionRepo.deleteAll(); }
}
