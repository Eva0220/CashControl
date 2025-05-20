package org.example.Handlers.UserActions;

import org.example.Exceptions.UserNotFoundException;
import org.example.Services.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

@Component
public class UserMenuHandler {
    private final UserService userService;
    private final UserInputHandler userInputHandler;
    private final Scanner scanner;

    public UserMenuHandler(UserService userService, UserInputHandler userInputHandler, Scanner scanner) {
        this.userService = userService;
        this.userInputHandler = userInputHandler;
        this.scanner = scanner;
    }

    public void handle(UUID userId) {
        while (true) {
            System.out.println("\n1 - Просмотреть данные пользователя");
            System.out.println("2 - Обновить данные пользователя");
            System.out.println("3 - Удалить пользователя");
            System.out.println("0 - Назад");
            System.out.print("Выберите опцию: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> viewUser(userId);
                case "2" -> updateUser(userId);
                case "3" -> {
                    userService.deleteUser(userId);
                    return;
                }
                case "0" -> { return; }
                default -> System.out.println("Неверная команда");
            }
        }
    }

    private void viewUser(UUID userId) {
        try {
            System.out.println(userService.readUser(userId));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateUser(UUID userId) {
        try {
            String fullName = null;
            Integer age = null;
            String passportData = null;
            String email = null;

            switch (userInputHandler.getUserAnswer()) {
                case 1 -> fullName = userInputHandler.getFullName();
                case 2 -> age = userInputHandler.getAge();
                case 3 -> passportData = userInputHandler.getPassportData();
                case 4 -> email = userInputHandler.getEmail();
            }
            userService.updateUser(userId, fullName, age, passportData, email);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
