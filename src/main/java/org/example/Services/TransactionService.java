package org.example.Services;

import org.example.Entities.Transaction;
import org.example.Exceptions.TransactionNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransactionService {
    private final IStorageService<Transaction> transactionStorage;

    public TransactionService(IStorageService<Transaction> transactionStorage) {
        this.transactionStorage = transactionStorage;
    }

    public void createTransaction(UUID walletId, Transaction.TransactionType transactionType, BigDecimal amount) {
        Transaction transaction = new Transaction(transactionType, amount, LocalDateTime.now(), walletId);
        transactionStorage.save(transaction);
        System.out.println("Транзакция создана");
    }

    public void deleteTransaction(UUID transactionId) {
        transactionStorage.delete(transactionId);
        System.out.println("Транзакция удалена");
    }

    public Transaction getTransactionById(UUID transactionId) {
        Transaction transaction = transactionStorage.findById(transactionId);
        if (transaction != null) {
            return transaction;
        } else {
            throw new TransactionNotFoundException("Транзакции не найдены");
        }
    }

    public List<Transaction> getTransactions(UUID walletId) {
        List<Transaction> transactions = transactionStorage.findAllBy(wallet -> wallet.getWalletId().equals(walletId));
        if (!transactions.isEmpty()) {
            return transactions;
        } else {
            throw new TransactionNotFoundException("Транзакции не найдены");
        }
    }
}
