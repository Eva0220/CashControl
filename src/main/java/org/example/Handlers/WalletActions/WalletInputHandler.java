package org.example.Handlers.WalletActions;

import org.example.Entities.Wallet;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class WalletInputHandler {
    private final Scanner scanner;

    public WalletInputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getName() {
        System.out.println("Введите название кошелька:");
        return scanner.nextLine();
    }

    public Wallet.Currency getCurrency() {
        Wallet.Currency[] currencies = Wallet.Currency.values();
        System.out.println("Выберите валюту для кошелька:");
        for (int i = 0; i < currencies.length; i++) {
            System.out.println((i + 1) + " - " + currencies[i]);
        }
        System.out.println("Введите число:");
        int answer = scanner.nextInt();
        scanner.nextLine();
        return currencies[answer - 1];
    }

    public Wallet selectWallet(List<Wallet> wallets) {
        for (int i = 0; i < wallets.size(); i++) {
            System.out.println((i+1) + " - " + wallets.get(i));
        }
        System.out.println("Выберите кошелек (введите цифру):");
        int answer = scanner.nextInt();
        scanner.nextLine();
        return wallets.get(answer-1);
    }
}
