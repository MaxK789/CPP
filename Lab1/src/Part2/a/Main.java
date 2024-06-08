package Part2.a;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Введення кількості дробів
        System.out.print("Введіть кількість дробів: ");
        int k = scanner.nextInt();

        // Ініціалізація масиву дробів
        RationalFraction[] fractions = new RationalFraction[k];

        // Введення значень для масиву дробів
        for (int i = 0; i < k; i++) {
            System.out.print("Введіть чисельник дробу " + (i + 1) + ": ");
            int m = scanner.nextInt();
            System.out.print("Введіть знаменник дробу " + (i + 1) + ": ");
            int n = scanner.nextInt();
            fractions[i] = new RationalFraction(m, n);
        }

        // Виведення значень масиву дробів перед модифікацією
        System.out.println("Дроби перед модифікацією:");
        for (RationalFraction fraction : fractions) {
            System.out.println(fraction);
        }

        // Модифікація масиву
        modifyArray(fractions);

        // Виведення значень масиву дробів після модифікації
        System.out.println("Дроби після модифікації:");
        for (RationalFraction fraction : fractions) {
            System.out.println(fraction);
        }

        scanner.close();
    }

    public static void modifyArray(RationalFraction[] fractions) {
        for (int i = 0; i < fractions.length - 1; i += 2) {
            fractions[i] = fractions[i].add(fractions[i + 1]);
        }
    }
}