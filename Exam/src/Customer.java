public class Customer extends User {
    private double balance;

    public Customer(String username, String password) {
        super(username, password);
    }

    @Override
    public void showMenu() {
        System.out.println("1. Search Properties");
        System.out.println("2. Book Property");
        System.out.println("3. Pay for Booking");
        System.out.println("4. Logout");
    }
}
