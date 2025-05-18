package org.example.Handlers.TransactionActions;

import org.example.Entities.Transaction;
import org.example.Exceptions.TransactionNotFoundException;
import org.example.Services.TransactionService;
import org.example.Services.WalletService;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

public class TransactionMenuHandler {
    private final TransactionService transactionService;
    private final WalletService walletService;
    private final TransactionInputHandler transactionInputHandler;
    private final Scanner scanner;

    public TransactionMenuHandler(TransactionService transactionService, WalletService walletService, TransactionInputHandler transactionInputHandler, Scanner scanner) {
        this.transactionService = transactionService;
        this.walletService = walletService;
        this.transactionInputHandler = transactionInputHandler;
        this.scanner = scanner;
    }

    public void handle(UUID walletId) {
        while (true) {
            System.out.println("\n1 - Создать транзакцию");
            System.out.println("2 - Удалить транзакцию");
            System.out.println("3 - Показать все транзакции");
            System.out.println("0 - Назад");
            System.out.print("Выберите опцию: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> createTransaction(walletId);
                case "2" -> deleteTransaction(walletId);
                case "3" -> {
                    try {
                        System.out.println(transactionService.getTransactions(walletId));
                    }
                    catch (TransactionNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case "0" -> { return; }
                default -> System.out.println("Неверная команда");
            }
        }
    }

    private void createTransaction(UUID walletId) {
        Transaction.TransactionType type = transactionInputHandler.chooseTransactionType();
        BigDecimal amount = transactionInputHandler.getAmount();
        transactionService.createTransaction(walletId, type, amount);
        walletService.updateWalletBalance(walletId, type, amount);
    }

    private void deleteTransaction(UUID walletId) {
        try {
            System.out.println(transactionService.getTransactions(walletId));
            UUID transactionId = transactionInputHandler.getId();
            Transaction transaction = transactionService.getTransactionById(transactionId);
            System.out.print("Удалить выбранную транзакцию? (да/нет): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("да")) {
                transactionService.deleteTransaction(transactionId);
                walletService.updateWalletBalance(walletId, transaction.getType(), transaction.getAmount());
            }
        }
        catch (TransactionNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
