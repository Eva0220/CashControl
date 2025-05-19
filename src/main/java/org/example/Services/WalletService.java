package org.example.Services;

import org.example.Entities.Transaction;
import org.example.Entities.Wallet;
import org.example.Exceptions.WalletNotFoundException;

import java.math.BigDecimal;
import java.util.*;

public class WalletService {
    private final IStorageService<Wallet> storage;

    public WalletService(IStorageService<Wallet> storage) {
        this.storage = storage;
    }

    public UUID createWallet(UUID userId, String name, Wallet.Currency currency) {
        Wallet wallet = new Wallet(name, BigDecimal.ZERO, currency, userId);
        storage.save(wallet);
        System.out.println("Кошелек создан\n" + wallet);
        return wallet.getId();
    }

    public List<Wallet> getWallets(UUID userId) {
        List<Wallet> wallets = storage.findAllBy(wallet -> wallet.getUserId().equals(userId));
        if (!wallets.isEmpty()) {
            return wallets;
        } else {
            throw new WalletNotFoundException("Кошельки не найдены");
        }
    }

    public void deleteWallet(UUID walletId) {
        storage.delete(walletId);
        System.out.println("Кошелек удален");
    }

    public void updateWalletBalance(UUID walletId, Transaction.TransactionType transactionType, BigDecimal amount) {
        Wallet wallet = storage.findById(walletId);
        if (transactionType.equals(Transaction.TransactionType.DEPOSIT)) {
            wallet.setBalance(wallet.getBalance().add(amount));
        } else {
            wallet.setBalance(wallet.getBalance().subtract(amount));
        }
    }
}
