package com.example.budgets;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransactionController {
    private final TransactionRepository repo;
    TransactionController(TransactionRepository repo) {
        this.repo = repo;
    }

    @GetMapping(value = "/allTransactions")
    public List<Transaction> getAllTransactions() {
        return TransactionService.getAllTransactions();
    }

    @PostMapping(value = "/createNewTransaction/save")
    public void createNewTransaction(@RequestBody Transaction transaction) {
        TransactionService.createNewTransaction(transaction);
    }

    @PostMapping(value = "/editTransaction")
    public void editTransaction(@RequestParam(name = "id") Long ID, @RequestParam(name = "transactionName") String name, @RequestParam(name = "transactionAmount") float amount, @RequestParam(name = "budgetID") Long budgetID, @RequestParam(name = "categoryID") Long categoryID) {
        TransactionService.editTransaction(ID, name, amount, budgetID, categoryID);
    }

    @GetMapping(value = "/transaction")
    public Optional<Transaction> getTransactionByID(@RequestParam(name = "id") Long ID) {
        return TransactionService.getTransactionByID(ID);
    }

    @DeleteMapping(value = "/deleteTransaction")
    public void deleteTransaction(@RequestParam(name = "id") Long id) {
        TransactionService.deleteTransaction(id);
    }

}
