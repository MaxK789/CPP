public class Booking {
    private int bookingId;
    private Property property;
    private Customer customer;
    private boolean paid;

    private static int nextBookingId = 1; // Static variable to keep track of next available ID

    public Booking(Property property, Customer customer) {
        this.bookingId = nextBookingId++;
        this.property = property;
        this.customer = customer;
        this.paid = false;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Property getProperty() {
        return property;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Property: " + property.getName() + ", Customer: " + customer.getUsername() + ", Paid: " + paid;
    }
}
