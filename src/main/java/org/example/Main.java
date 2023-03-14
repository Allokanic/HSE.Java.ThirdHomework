package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final DateTimeFormatter DATE_OF_BIRTH_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.uuuu").withResolverStyle(
                    ResolverStyle.STRICT);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите фамилию имя и отчество: ");
        String fullName = scanner.nextLine();
        System.out.print("Введите дату рождения в формате дд.мм.гггг: ");
        String birthDateStr = scanner.nextLine();

        try {
            String[] nameParts = fullName.split(" ");
            String lastName = nameParts[0];
            String firstName = nameParts[1];
            String middleName = nameParts[2];

            LocalDate birthDate =
                    LocalDate.parse(birthDateStr, DATE_OF_BIRTH_FORMATTER);
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(birthDate, currentDate).getYears();
            if (age > 100) {
                throw new IllegalArgumentException();
            }
            char gender = getGenderByMiddleName(middleName);

            String result;

            if (age % 10 == 1 && age != 11) {
                result = "год";
            } else if (age % 10 > 1 && age % 10 < 5 && !List.of(12,13,14).contains(age)) {
                result = "года";
            } else {
                result = "лет";
            }

            System.out.printf("%s %s.%s. %c %d %s%n", lastName, firstName.charAt(0), middleName.charAt(0), gender, age, result );
        } catch (Exception e) {
            System.err.println("Ошибка: введенная дата рождения не является корректной.");
        }
    }


    private static char getGenderByMiddleName(String middleName) {
        char gender;
        if (middleName.endsWith("ч")) {
            gender = 'М';
        } else {
            gender = 'Ж';
        }
        return gender;
    }
}