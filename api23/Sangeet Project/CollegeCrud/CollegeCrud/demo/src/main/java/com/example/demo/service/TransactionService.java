package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        transaction.setTransactionDate(LocalDate.now()); // Auto-set transaction date
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Page<Transaction> getAllTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    public List<Transaction> searchByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public List<Transaction> searchByTransactionDate(LocalDate date) {
        return transactionRepository.findByTransactionDate(date);
    }

    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        return transactionRepository.findById(id).map(transaction -> {
            transaction.setUserId(updatedTransaction.getUserId());
            transaction.setClothingItemId(updatedTransaction.getClothingItemId());
            transaction.setTransactionAmount(updatedTransaction.getTransactionAmount());
            return transactionRepository.save(transaction);
        }).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}