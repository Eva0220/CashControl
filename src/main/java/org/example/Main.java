package org.example;

import org.example.Entities.Transaction;
import org.example.Entities.User;
import org.example.Entities.Wallet;
import org.example.Handlers.*;
import org.example.Handlers.TransactionActions.TransactionInputHandler;
import org.example.Handlers.TransactionActions.TransactionMenuHandler;
import org.example.Handlers.UserActions.UserInputHandler;
import org.example.Handlers.UserActions.UserMenuHandler;
import org.example.Handlers.WalletActions.WalletInputHandler;
import org.example.Handlers.WalletActions.WalletMenuHandler;
import org.example.Services.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Хранилища
        IStorageService<User> userStorage = new MapStorageService<>();
        IStorageService<Wallet> walletStorage = new MapStorageService<>();
        IStorageService<Transaction> transactionStorage = new MapStorageService<>();
        // Сервисы
        UserService userService = new UserService(userStorage);
        WalletService walletService = new WalletService(walletStorage);
        TransactionService transactionService = new TransactionService(transactionStorage);

        // Input Handlers
        UserInputHandler userInputHandler = new UserInputHandler(scanner);
        WalletInputHandler walletInputHandler = new WalletInputHandler(scanner);
        TransactionInputHandler transactionInputHandler = new TransactionInputHandler(scanner);

        // Menu Handlers (UI)
        TransactionMenuHandler transactionMenuHandler = new TransactionMenuHandler(transactionService,walletService, transactionInputHandler, scanner);
        WalletMenuHandler walletMenuHandler = new WalletMenuHandler(walletService, walletInputHandler, transactionMenuHandler, scanner);
        UserMenuHandler userMenuHandler = new UserMenuHandler(userService, userInputHandler, scanner);

        // Основной обработчик
        MainInputHandler mainInputHandler = new MainInputHandler(userService, userMenuHandler, walletMenuHandler, scanner, userInputHandler);
        mainInputHandler.mainMenu();
    }
}
