package com.example.budgets;

import com.example.budgets.entity.Transaction;
import com.example.budgets.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class TransactionControllerTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void saveTransaction() {
        Transaction transaction1 = new Transaction("NewTransaction1", 100, 1L);
        transactionRepository.save(transaction1);
        Transaction transaction2 = new Transaction("NewTransaction2", 200, 2L);
        transactionRepository.save(transaction2);
        Transaction transaction3 = new Transaction("NewTransaction3", 300, 3L);
        transactionRepository.save(transaction3);

        Assertions.assertEquals(3, transactionRepository.findAll().size());
        transactionRepository.deleteAll();
    }

    @Test
    void readTransaction() {
        Transaction transaction1 = new Transaction("NewTransaction1", 100, 1L);
        transaction1.setTransactionID(1L);

        Assertions.assertEquals("NewTransaction1", transaction1.getTransactionName());
        Assertions.assertEquals(1L, transaction1.getTransactionID());
        Assertions.assertEquals(100, transaction1.getTransactionAmount());
        Assertions.assertEquals(1L, transaction1.getCategoryID());
        transactionRepository.deleteAll();
    }

    @Test
    void deleteTransaction() {
        Transaction transaction1 = new Transaction("NewTransaction1", 100, 1L);
        transactionRepository.save(transaction1);
        Transaction transaction2 = new Transaction("NewTransaction2", 200, 2L);
        transactionRepository.save(transaction2);
        Transaction transaction3 = new Transaction("NewTransaction3", 300, 3L);
        transactionRepository.save(transaction3);

        transactionRepository.deleteById(3L);

        Assertions.assertEquals(2, transactionRepository.findAll().size());
        Assertions.assertTrue(transactionRepository.findById(3L).isEmpty());
        transactionRepository.deleteAll();
    }

    @Test
    void editTransaction() {
        Transaction transaction1 = new Transaction("NewTransaction1", 100, 1L);
        transaction1.setTransactionID(1L);
        transactionRepository.save(transaction1);

        Optional<Transaction> transactionToUpdate = transactionRepository.findById(transaction1.getTransactionID());

        if (transactionToUpdate.isPresent()) {
            Transaction transaction = transactionToUpdate.get();
            transaction.setTransactionName("Edit Transaction Name");
            transaction.setTransactionAmount(400);

            transactionRepository.save(transaction);

            Optional<Transaction> updatedTransaction = transactionRepository.findById(transaction.getTransactionID());
            Assertions.assertTrue(updatedTransaction.isPresent());
            Assertions.assertEquals("Edit Transaction Name", updatedTransaction.get().getTransactionName());
            Assertions.assertEquals(400, updatedTransaction.get().getTransactionAmount());
        } else {
            Assertions.fail("Transaction not found for ID: " + transaction1.getTransactionID());
        }
        transactionRepository.deleteAll();
    }
    
}
