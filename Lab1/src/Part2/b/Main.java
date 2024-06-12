package Part2.b;

import Part2.a.RationalFraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = 0;
        while (true) {
            System.out.print("Введіть кількість кіл (не менше трьох): ");
            if (scanner.hasNextInt()) {
                k = scanner.nextInt();
                if (k >= 3) {
                    break;
                } else {
                    System.out.println("Кількість кіл повинна бути не менше трьох.");
                }
            } else {
                System.out.println("Неправильний формат введених даних.");
                scanner.next();
            }
        }

        List<Circle> circles = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            System.out.println("Для кола " + (i + 1) + ":");
            RationalFraction centerX = inputRationalFraction(scanner, "X");
            RationalFraction centerY = inputRationalFraction(scanner, "Y");

            double radius;
            while (true) {
                System.out.print("Введіть радіус кола: ");
                if (scanner.hasNextDouble()) {
                    radius = scanner.nextDouble();
                    if (radius > 0) {
                        break;
                    } else {
                        System.out.println("Радіус повинен бути більше нуля.");
                    }
                } else {
                    System.out.println("Неправильний формат введених даних.");
                    scanner.next();
                }
            }

            circles.add(new Circle(centerX, centerY, radius));
        }

        Circle maxAreaCircle = circles.get(0);
        Circle minAreaCircle = circles.get(0);

        for (Circle circle : circles) {
            if (circle.calculateArea() > maxAreaCircle.calculateArea()) {
                maxAreaCircle = circle;
            }
            if (circle.calculateArea() < minAreaCircle.calculateArea()) {
                minAreaCircle = circle;
            }
        }

        System.out.println("Коло з найбільшою площею: " + maxAreaCircle);
        System.out.println("Коло з найменшою площею: " + minAreaCircle);

        Circle maxPerimeterCircle = circles.get(0);
        Circle minPerimeterCircle = circles.get(0);

        for (Circle circle : circles) {
            if (circle.calculatePerimeter() > maxPerimeterCircle.calculatePerimeter()) {
                maxPerimeterCircle = circle;
            }
            if (circle.calculatePerimeter() < minPerimeterCircle.calculatePerimeter()) {
                minPerimeterCircle = circle;
            }
        }

        System.out.println("Коло з найбільшим периметром: " + maxPerimeterCircle);
        System.out.println("Коло з найменшим периметром: " + minPerimeterCircle);

        System.out.println("Групи кіл, центри яких лежать на одній прямій:");
        findCirclesOnSameLine(circles);

        scanner.close();
    }

    public static void findCirclesOnSameLine(List<Circle> circles) {
        for (int i = 0; i < circles.size(); i++) {
            for (int j = i + 1; j < circles.size(); j++) {
                for (int k = j + 1; k < circles.size(); k++) {
                    Circle c1 = circles.get(i);
                    Circle c2 = circles.get(j);
                    Circle c3 = circles.get(k);
                    if (areCollinear(c1, c2, c3)) {
                        System.out.println(c1 + ", " + c2 + ", " + c3);
                    }
                }
            }
        }
    }

    public static boolean areCollinear(Circle c1, Circle c2, Circle c3) {
        RationalFraction x1 = c1.getCenterX();
        RationalFraction y1 = c1.getCenterY();
        RationalFraction x2 = c2.getCenterX();
        RationalFraction y2 = c2.getCenterY();
        RationalFraction x3 = c3.getCenterX();
        RationalFraction y3 = c3.getCenterY();

        return x1.getM() * (y2.getM() - y3.getM()) + x2.getM() * (y3.getM() - y1.getM()) + x3.getM() * (y1.getM() - y2.getM()) == 0;
    }

    private static RationalFraction inputRationalFraction(Scanner scanner, String axis) {
        int m, n;
        while (true) {
            System.out.print("Введіть чисельник координати " + axis + " центру кола: ");
            if (scanner.hasNextInt()) {
                m = scanner.nextInt();
                System.out.print("Введіть знаменник координати " + axis + " центру кола: ");
                if (scanner.hasNextInt()) {
                    n = scanner.nextInt();
                    if (n != 0) {
                        break;
                    } else {
                        System.out.println("Знаменник не може бути нуль.");
                    }
                } else {
                    System.out.println("Неправильний формат введених даних.");
                    scanner.next();
                }
            } else {
                System.out.println("Неправильний формат введених даних.");
                scanner.next();
            }
        }
        return new RationalFraction(m, n);
    }
}
