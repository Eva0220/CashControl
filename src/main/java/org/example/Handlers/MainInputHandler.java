package org.example.Handlers;

import org.example.Handlers.UserActions.UserInputHandler;
import org.example.Handlers.UserActions.UserMenuHandler;
import org.example.Handlers.WalletActions.WalletMenuHandler;
import org.example.Services.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

@Component
public class MainInputHandler {
    private final UserService userService;
    private final UserInputHandler userInputHandler;
    private final UserMenuHandler userMenuHandler;
    private final WalletMenuHandler walletMenuHandler;
    private final Scanner scanner;

    public MainInputHandler(UserService userService, UserMenuHandler userMenuHandler, WalletMenuHandler walletMenuHandler, Scanner scanner, UserInputHandler userInputHandler) {
        this.userService = userService;
        this.userMenuHandler = userMenuHandler;
        this.walletMenuHandler = walletMenuHandler;
        this.scanner = scanner;
        this.userInputHandler = userInputHandler;
    }

    public void mainMenu() {
        while (true) {
            System.out.println("\nКоманды:");
            System.out.println("1 - Создать пользователя");
            System.out.println("0 - Выход");
            System.out.print("Введите команду: ");

            String command = scanner.nextLine();
            switch (command) {
                case "1" -> {
                    String fullName = userInputHandler.getFullName();
                    int age = userInputHandler.getAge();
                    String passportData = userInputHandler.getPassportData();
                    String email = userInputHandler.getEmail();
                    UUID userId = userService.createUser(fullName, age, passportData, email);
                    userMenu(userId);
                }
                case "0" -> System.exit(0);
                default -> System.out.println("Неверная команда");
            }
        }
    }

    private void userMenu(UUID userId) {
        while (true) {
            System.out.println("\n1 - Управление пользователем");
            System.out.println("2 - Управление кошельками");
            System.out.println("0 - Назад");
            System.out.print("Выберите опцию: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> userMenuHandler.handle(userId);
                case "2" -> walletMenuHandler.handle(userId);
                case "0" -> {
                    return;
                }
                default -> System.out.println("Неверная команда");
            }
        }
    }
}
