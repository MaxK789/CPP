package Part2.b;

import Part2.a.RationalFraction;

public class Circle {
    private RationalFraction centerX;
    private RationalFraction centerY;
    private double radius;

    public Circle(RationalFraction centerX, RationalFraction centerY, double radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public RationalFraction getCenterX() {
        return centerX;
    }

    public RationalFraction getCenterY() {
        return centerY;
    }

    public double getRadius() {
        return radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle[center=(" + centerX + ", " + centerY + "), radius=" + radius + "]";
    }
}
