package Part2.b;

import Part2.a.RationalFraction;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Введення кількості кіл
        System.out.print("Введіть кількість кіл: ");
        int k = scanner.nextInt();

        // Ініціалізація списку кіл
        List<Circle> circles = new ArrayList<>();

        // Введення значень для списку кіл
        for (int i = 0; i < k; i++) {
            System.out.print("Введіть чисельник координати X центру кола " + (i + 1) + ": ");
            int mX = scanner.nextInt();
            System.out.print("Введіть знаменник координати X центру кола " + (i + 1) + ": ");
            int nX = scanner.nextInt();

            System.out.print("Введіть чисельник координати Y центру кола " + (i + 1) + ": ");
            int mY = scanner.nextInt();
            System.out.print("Введіть знаменник координати Y центру кола " + (i + 1) + ": ");
            int nY = scanner.nextInt();

            System.out.print("Введіть радіус кола " + (i + 1) + ": ");
            double radius = scanner.nextDouble();

            RationalFraction centerX = new RationalFraction(mX, nX);
            RationalFraction centerY = new RationalFraction(mY, nY);
            circles.add(new Circle(centerX, centerY, radius));
        }

        // Визначення найбільшого і найменшого за площею кола
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

        // Визначення найбільшого і найменшого за периметром кола
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

        // Визначення груп кіл, центри яких лежать на одній прямій
        System.out.println("Групи кіл, центри яких лежать на одній прямій:");
        findCirclesOnSameLine(circles);
    }

    public static void findCirclesOnSameLine(List<Circle> circles) {
        for (int i = 0; i < circles.size(); i++) {
            for (int j = i + 1; j < circles.size(); j++) {
                for (int k = j + 1; k < circles.size(); k++) {
                    Circle c1 = circles.get(i);
                    Circle c2 = circles.get(j);
                    Circle c3 = circles.get(k);
                    if (areCollinear(c1, c2, c3)) {
                        System.out.println("Кола з центрами на одній прямій: " + c1 + ", " + c2 + ", " + c3);
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

        // Перевірка на колінеарність за допомогою визначника
        return x1.getM() * (y2.getM() - y3.getM()) + x2.getM() * (y3.getM() - y1.getM()) + x3.getM() * (y1.getM() - y2.getM()) == 0;
    }
}
