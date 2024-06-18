public class Landlord extends User {

    public Landlord(String username, String password) {
        super(username, password);
    }

    @Override
    public void showMenu() {
        System.out.println("1. Add Property");
        System.out.println("2. Edit Property");
        System.out.println("3. View Properties");
        System.out.println("4. Logout");
    }
}
