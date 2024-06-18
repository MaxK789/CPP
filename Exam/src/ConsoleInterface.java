import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {
    private Scanner scanner;
    private TravelAgency travelAgency;

    public ConsoleInterface() {
        scanner = new Scanner(System.in);
        travelAgency = TravelAgency.getInstance();
        initializeDefaultData();
    }

    public void start() {
        while (true) {
            System.out.println("Welcome to the Online Travel Agency!");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            int choice = readIntegerInput();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        User user = travelAgency.authenticate(username, password);
        if (user != null) {
            handleUserActions(user);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private void register() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.println("Register as: 1. Customer 2. Landlord");
        int type = readIntegerInput();
        if (type == 1 || type == 2) {
            User user = (type == 1) ? new Customer(username, password) : new Landlord(username, password);
            travelAgency.addUser(user);
            System.out.println("Registration successful.");
        } else {
            System.out.println("Invalid choice. Please enter 1 for Customer or 2 for Landlord.");
        }
    }

    private void handleUserActions(User user) {
        boolean loggedIn = true;
        while (loggedIn) {
            user.showMenu();
            int choice = readIntegerInput();
            if (user instanceof Customer) {
                loggedIn = handleCustomerActions((Customer) user, choice);
            } else if (user instanceof Landlord) {
                loggedIn = handleLandlordActions((Landlord) user, choice);
            }
        }
    }

    private boolean handleCustomerActions(Customer customer, int choice) {
        switch (choice) {
            case 1:
                searchProperties();
                break;
            case 2:
                bookProperty(customer);
                break;
            case 3:
                payForBooking(customer);
                break;
            case 4:
                return false; // Logout
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
        return true; // Stay logged in
    }

    private void searchProperties() {
        System.out.println("Available properties:");
        for (Property property : travelAgency.searchProperties()) {
            System.out.println(property);
        }
    }

    private void bookProperty(Customer customer) {
        searchProperties();
        System.out.print("Enter property ID to book: ");
        String propertyId = scanner.nextLine();
        Property property = travelAgency.getPropertyById(Integer.parseInt(propertyId));
        if (property != null) {
            Booking booking = new Booking(property, customer);
            travelAgency.addBooking(booking);
            System.out.println("Booking successful. Booking ID: " + booking.getBookingId());
        } else {
            System.out.println("Invalid property ID.");
        }
    }

    private void payForBooking(Customer customer) {
        List<Booking> customerBookings = travelAgency.getBookingsByCustomer(customer);
        if (customerBookings.isEmpty()) {
            System.out.println("You have no bookings to pay for.");
            return;
        }

        System.out.println("Your bookings:");
        for (Booking booking : customerBookings) {
            System.out.println("Booking ID: " + booking.getBookingId() + ", Property: " + booking.getProperty().getName());
        }

        System.out.print("Enter booking ID to pay: ");
        String bookingIdStr = scanner.nextLine();
        int bookingId = Integer.parseInt(bookingIdStr);

        Booking booking = travelAgency.getBookingById(bookingId);
        if (booking != null && !booking.isPaid()) {
            PaymentProcessor paymentProcessor = new PaymentProcessor(booking);
            paymentProcessor.start();
            // Assuming payment processing is synchronous and successful
            booking.setPaid(true);
            travelAgency.removeBooking(booking); // Remove booking from the list
            System.out.println("Payment successful for Booking ID: " + booking.getBookingId());
        } else {
            System.out.println("Invalid booking ID or booking already paid.");
        }

        handleUserActions(customer);
    }

    private boolean handleLandlordActions(Landlord landlord, int choice) {
        switch (choice) {
            case 1:
                addProperty(landlord);
                break;
            case 2:
                editProperty();
                break;
            case 3:
                viewPropertiesByLandlord(landlord);
                break;
            case 4:
                return false; // Logout
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
        return true; // Stay logged in
    }

    private void addProperty(Landlord landlord) {
        System.out.print("Property name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Price per night: ");
        double pricePerNight = readDoubleInput();
        Property property = new Property(name, description, pricePerNight, landlord);
        travelAgency.addProperty(property);
        System.out.println("Property added successfully. Property ID: " + property.getId());
    }

    private void editProperty() {
        viewProperties();
        System.out.print("Enter property ID to edit: ");
        String propertyId = scanner.nextLine();
        Property property = travelAgency.getPropertyById(Integer.parseInt(propertyId));
        if (property != null) {
            System.out.print("New name: ");
            String name = scanner.nextLine();
            System.out.print("New description: ");
            String description = scanner.nextLine();
            System.out.print("New price per night: ");
            double pricePerNight = readDoubleInput();
            // For simplicity, create a new property and replace the old one.
            travelAgency.addProperty(new Property(name, description, pricePerNight, property.getLandlord()));
            System.out.println("Property updated successfully.");
        } else {
            System.out.println("Invalid property ID.");
        }
    }

    private void viewPropertiesByLandlord(Landlord landlord) {
        System.out.println("Your properties:");
        for (Property property : travelAgency.getPropertiesByLandlord(landlord)) {
            System.out.println(property);
        }
    }

    private void viewProperties() {
        System.out.println("All properties:");
        for (Property property : travelAgency.searchProperties()) {
            System.out.println(property);
        }
    }

    private void initializeDefaultData() {
        // Add default landlords
        Landlord landlord1 = new Landlord("John", "123");
        Landlord landlord2 = new Landlord("Dan", "123");
        travelAgency.addUser(landlord1);
        travelAgency.addUser(landlord2);

        // Add default customers
        Customer customer1 = new Customer("Alex", "123");
        Customer customer2 = new Customer("Bill", "123");
        travelAgency.addUser(customer1);
        travelAgency.addUser(customer2);

        // Add default properties with sequential IDs
        Property property1 = new Property("Ocean View Apartment", "A beautiful apartment with an ocean view.", 150.00, landlord1);
        Property property2 = new Property("Mountain Cabin", "A cozy cabin in the mountains.", 100.00, landlord1);
        Property property3 = new Property("City Center Studio", "A modern studio in the city center.", 200.00, landlord2);
        travelAgency.addProperty(property1);
        travelAgency.addProperty(property2);
        travelAgency.addProperty(property3);
    }

    private int readIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private double readDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        ConsoleInterface consoleInterface = new ConsoleInterface();
        consoleInterface.start();
    }
}
