package com.example.budgets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionService {
    public static TransactionRepository transactionRepo;

    @Autowired
    public TransactionService(TransactionRepository repo) {
        TransactionService.transactionRepo = repo;
    }

}
