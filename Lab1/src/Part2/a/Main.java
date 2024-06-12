package Part2.a;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Введіть кількість дробів: ");
            if (scanner.hasNextInt()) {
                k = scanner.nextInt();
                if (k % 2 == 0 && k > 0) {
                    validInput = true;
                } else {
                    System.out.println("Введіть парне число більше 0.");
                }
            } else {
                System.out.println("Некоректне введення.");
                scanner.next();
            }
        }

        RationalFraction[] fractions = new RationalFraction[k];

        for (int i = 0; i < k; i++) {
            int m = 0;
            int n = 0;
            boolean validFraction = false;

            while (!validFraction) {
                System.out.print("Введіть чисельник дробу " + (i + 1) + ": ");
                if (scanner.hasNextInt()) {
                    m = scanner.nextInt();
                } else {
                    System.out.println("Некоректне введення.");
                    scanner.next();
                    continue;
                }

                System.out.print("Введіть знаменник дробу " + (i + 1) + ": ");
                if (scanner.hasNextInt()) {
                    n = scanner.nextInt();
                    if (n != 0) {
                        validFraction = true;
                    } else {
                        System.out.println("Знаменник не може бути 0.");
                    }
                } else {
                    System.out.println("Некоректне введення.");
                    scanner.next();
                }
            }

            fractions[i] = new RationalFraction(m, n);
        }

        System.out.println("Дроби перед модифікацією:");
        for (RationalFraction fraction : fractions) {
            System.out.println(fraction);
        }

        modifyArray(fractions);

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