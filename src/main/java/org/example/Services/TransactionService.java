package org.example.Services;

import org.example.Entities.Transaction;
import org.example.Entities.Wallet;
import org.example.Exceptions.TransactionNotFoundException;
import org.example.Exceptions.WalletNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class TransactionService {
    private final IStorageService<Transaction> storage;

    public TransactionService(IStorageService<Transaction> storage) {
        this.storage = storage;
    }


    public void createTransaction(UUID walletId, Transaction.TransactionType transactionType, BigDecimal amount) {
        Transaction transaction = new Transaction(transactionType, amount, LocalDateTime.now(), walletId);
        storage.save(transaction.getId(), transaction);
        System.out.println("Транзакция создана");
    }

    public void deleteTransaction(UUID transactionId) {
        storage.delete(transactionId);
        System.out.println("Транзакция удалена");
    }

    public Transaction getTransactionById(UUID transactionId) {
        Transaction transaction = storage.findById(transactionId);
        if (transaction != null) {
            return transaction;
        } else {
            throw new TransactionNotFoundException("Транзакции не найдены");
        }
    }

    public List<Transaction> getTransactions(UUID walletId) {
        List<Transaction> transactions = storage.findAllBy(wallet -> wallet.getWalletId().equals(walletId));
        if (!transactions.isEmpty()) {
            return transactions;
        } else {
            throw new TransactionNotFoundException("Транзакции не найдены");
        }
    }
}
