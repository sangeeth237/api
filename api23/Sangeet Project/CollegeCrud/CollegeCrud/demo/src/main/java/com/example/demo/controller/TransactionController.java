// filepath: /c:/Users/ADMIN/Desktop/CollegeCrud/CollegeCrud/demo/src/main/java/com/example/demo/controller/TransactionController.java
package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.status(201).body(transactionService.createTransaction(transaction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Transaction>> getAllTransactions(Pageable pageable) {
        return ResponseEntity.ok(transactionService.getAllTransactions(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Transaction>> searchTransactions(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String date) {

        if (userId != null) {
            return ResponseEntity.ok(transactionService.searchByUserId(userId));
        } else if (date != null) {
            return ResponseEntity.ok(transactionService.searchByTransactionDate(LocalDate.parse(date)));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction updatedTransaction) {
        return ResponseEntity.ok(transactionService.updateTransaction(id, updatedTransaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}