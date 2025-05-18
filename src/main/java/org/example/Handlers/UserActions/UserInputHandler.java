package org.example.Handlers.UserActions;

import org.example.Entities.User;
import org.example.Utils.ValidationUtil;

import java.util.Scanner;

public class UserInputHandler {
    private final Scanner scanner;

    public UserInputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getFullName() {
        while (true) {
            System.out.println("Введите ФИО пользователя:");
            String fullName = scanner.nextLine();
            try {
                ValidationUtil.validate(User.class, "fullName", fullName);
                return fullName;
            }
            catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public int getAge() {
        while (true) {
            System.out.println("Введите возраст пользователя:");
            int age = scanner.nextInt();
            scanner.nextLine();
            try {
                ValidationUtil.validate(User.class, "age", age);
                return age;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public String getPassportData() {
        while (true) {
            System.out.println("Введите серию и номер паспорта:");
            String passportData = scanner.nextLine();
            try {
                ValidationUtil.validate(User.class, "passportData", passportData);
                return passportData;
            }
            catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public String getEmail() {
        while (true) {
            System.out.println("Введите Email:");
            String email = scanner.nextLine();
            try {
                ValidationUtil.validate(User.class, "email", email);
                return email;
            }
            catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public int getUserAnswer() {
        System.out.println("1 - ФИО");
        System.out.println("2 - Возраст");
        System.out.println("3 - Паспортные данные");
        System.out.println("4 - Email");
        while (true) {
            System.out.println("Введите цифру:");
            int answer = scanner.nextInt();
            scanner.nextLine();
            if (answer < 1 || answer > 4) {
                System.out.println("Ошибка ввода");
            } else return answer;
        }
    }
}
