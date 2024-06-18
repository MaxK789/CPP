import java.util.*;

public class TravelAgency {
    private static TravelAgency instance = null;
    private List<User> users;
    private List<Property> properties;
    private List<Booking> bookings;

    private TravelAgency() {
        users = new ArrayList<>();
        properties = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    public static TravelAgency getInstance() {
        if (instance == null) {
            instance = new TravelAgency();
        }
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public List<Property> searchProperties() {
        return properties;
    }

    public Property getPropertyById(String id) {
        for (Property property : properties) {
            if (property.getId().equals(id)) {
                return property;
            }
        }
        return null;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public Booking getBookingById(String id) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(id)) {
                return booking;
            }
        }
        return null;
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Customer) {
                customers.add((Customer) user);
            }
        }
        return customers;
    }

    public List<Landlord> getLandlords() {
        List<Landlord> landlords = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Landlord) {
                landlords.add((Landlord) user);
            }
        }
        return landlords;
    }

    public List<Property> getPropertiesByLandlord(Landlord landlord) {
        List<Property> landlordProperties = new ArrayList<>();
        for (Property property : properties) {
            if (property.getLandlord().equals(landlord)) {
                landlordProperties.add(property);
            }
        }
        return landlordProperties;
    }
}
