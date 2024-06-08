package Part1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть розмір матриці n: ");
        int n = scanner.nextInt();

        Matrix matrix = new Matrix(n);
        matrix.fillRandom();

        System.out.println("Початкова матриця:");
        matrix.printMatrix();

        matrix.rearrangeByRowSum();

        System.out.println("Перегрупована матриця:");
        matrix.printMatrix();
    }
}