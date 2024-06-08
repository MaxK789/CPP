package Part2.a;

import Part2.a.RationalFraction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RationalFractionTest {

    @Test
    public void testSimplify() {
        RationalFraction fraction = new RationalFraction(2, 4);
        assertEquals(1, fraction.getM());
        assertEquals(2, fraction.getN());
    }

    @Test
    public void testAdd() {
        RationalFraction fraction1 = new RationalFraction(1, 2);
        RationalFraction fraction2 = new RationalFraction(1, 3);
        RationalFraction result = fraction1.add(fraction2);
        assertEquals(5, result.getM());
        assertEquals(6, result.getN());
    }

    @Test
    public void testToString() {
        RationalFraction fraction = new RationalFraction(1, 2);
        assertEquals("1/2", fraction.toString());
    }

    @Test
    public void testGcd() {
        RationalFraction fraction = new RationalFraction(8, 12);
        assertEquals(2, fraction.getM());
        assertEquals(3, fraction.getN());
    }
}
