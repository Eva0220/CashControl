package org.example.Handlers.WalletActions;

import org.example.Entities.Wallet;
import org.example.Exceptions.WalletNotFoundException;
import org.example.Handlers.TransactionActions.TransactionMenuHandler;
import org.example.Services.WalletService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class WalletMenuHandler {
    private final WalletService walletService;
    private final WalletInputHandler walletInputHandler;
    private final TransactionMenuHandler transactionMenuHandler;
    private final Scanner scanner;

    public WalletMenuHandler(WalletService walletService, WalletInputHandler walletInputHandler, TransactionMenuHandler transactionMenuHandler, Scanner scanner) {
        this.walletService = walletService;
        this.walletInputHandler = walletInputHandler;
        this.transactionMenuHandler = transactionMenuHandler;
        this.scanner = scanner;
    }

    public void handle(UUID userId) {
        while (true) {
            System.out.println("\n1 - Создать кошелёк");
            System.out.println("2 - Показать мои кошельки");
            System.out.println("3 - Управление моими кошельками");
            System.out.println("0 - Назад");
            System.out.print("Выберите опцию: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> createWallet(userId);
                case "2" -> {
                    try {
                        System.out.println(walletService.getWallets(userId));
                    }
                    catch (WalletNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case "3" -> selectWallet(userId);
                case "0" -> { return; }
                default -> System.out.println("Неверная команда");
            }
        }
    }

    private void createWallet(UUID userId) {
        try {
            String name = walletInputHandler.getName();
            Wallet.Currency currency = walletInputHandler.getCurrency();
            UUID walletId = walletService.createWallet(userId, name, currency);
            walletActions(walletId);
        } catch (WalletNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void selectWallet(UUID userId) {
        try {
            List<Wallet> wallets = walletService.getWallets(userId);
            Wallet selectedWallet = walletInputHandler.selectWallet(wallets);
            walletActions(selectedWallet.getId());
        } catch (WalletNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void walletActions(UUID walletId) {
        while (true) {
            System.out.println("\n1 - Удалить кошелёк");
            System.out.println("2 - Управление транзакциями");
            System.out.println("0 - Назад");
            System.out.print("Выберите опцию: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    try {
                        walletService.deleteWallet(walletId);
                        return;
                    } catch (WalletNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "2" -> transactionMenuHandler.handle(walletId);
                case "0" -> { return; }
                default -> System.out.println("Неверная команда");
            }
        }
    }
}
