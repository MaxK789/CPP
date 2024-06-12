package Part1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Введіть розмір матриці n: ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n > 1) {
                    validInput = true;
                } else {
                    System.out.println("Введіть число більше 1.");
                }
            } else {
                System.out.println("Некоректне введення.");
                scanner.next();
            }
        }

        Matrix matrix = new Matrix(n);
        matrix.fillRandom();

        System.out.println("Початкова матриця:");
        matrix.printMatrix();

        matrix.rearrangeByRowSum();

        System.out.println("Перегрупована матриця:");
        matrix.printMatrix();
    }
}