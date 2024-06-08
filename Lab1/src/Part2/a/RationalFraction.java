package Part2.a;

public class RationalFraction {
    private int m;
    private int n;

    public RationalFraction(int m, int n) {
        this.m = m;
        this.n = n;
        simplify();
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public void setM(int m) {
        this.m = m;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void simplify() {
        int gcd = gcd(m, n);
        this.m /= gcd;
        this.n /= gcd;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public RationalFraction add(RationalFraction other) {
        int newM = this.m * other.n + other.m * this.n;
        int newN = this.n * other.n;
        return new RationalFraction(newM, newN);
    }

    @Override
    public String toString() {
        return m + "/" + n;
    }
}