package Part2.b;

import Part2.a.RationalFraction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CircleTest {

    @Test
    public void testCalculateArea() {
        RationalFraction centerX = new RationalFraction(1, 2);
        RationalFraction centerY = new RationalFraction(3, 4);
        double radius = 5.0;

        Circle circle = new Circle(centerX, centerY, radius);


        assertEquals(78.5, circle.calculateArea(), 0.1);
    }

    @Test
    public void testCalculatePerimeter() {
        RationalFraction centerX = new RationalFraction(1, 2);
        RationalFraction centerY = new RationalFraction(3, 4);
        double radius = 5.0;

        Circle circle = new Circle(centerX, centerY, radius);


        assertEquals(31.4, circle.calculatePerimeter(), 0.1);
    }
}
