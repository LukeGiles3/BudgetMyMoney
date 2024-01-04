package com.example.budgets;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionController {
    private final TransactionRepository repo;
    TransactionController(TransactionRepository repo) {
        this.repo = repo;
    }

}
