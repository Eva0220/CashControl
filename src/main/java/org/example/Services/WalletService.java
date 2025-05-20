package org.example.Services;

import org.example.Entities.Transaction;
import org.example.Entities.Wallet;
import org.example.Exceptions.WalletNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class WalletService {
    private final IStorageService<Wallet> walletStorage;

    public WalletService(IStorageService<Wallet> walletStorage) {
        this.walletStorage = walletStorage;
    }

    public UUID createWallet(UUID userId, String name, Wallet.Currency currency) {
        Wallet wallet = new Wallet(name, BigDecimal.ZERO, currency, userId);
        walletStorage.save(wallet);
        System.out.println("Кошелек создан\n" + wallet);
        return wallet.getId();
    }

    public List<Wallet> getWallets(UUID userId) {
        List<Wallet> wallets = walletStorage.findAllBy(wallet -> wallet.getUserId().equals(userId));
        if (!wallets.isEmpty()) {
            return wallets;
        } else {
            throw new WalletNotFoundException("Кошельки не найдены");
        }
    }

    public void deleteWallet(UUID walletId) {
        walletStorage.delete(walletId);
        System.out.println("Кошелек удален");
    }

    public void updateWalletBalance(UUID walletId, Transaction.TransactionType transactionType, BigDecimal amount) {
        Wallet wallet = walletStorage.findById(walletId);
        if (transactionType.equals(Transaction.TransactionType.DEPOSIT)) {
            wallet.setBalance(wallet.getBalance().add(amount));
        } else {
            wallet.setBalance(wallet.getBalance().subtract(amount));
        }
    }
}
