public class Booking {
    private String bookingId;
    private Property property;
    private Customer customer;
    private boolean paid;

    public Booking(String bookingId, Property property, Customer customer) {
        this.bookingId = bookingId;
        this.property = property;
        this.customer = customer;
        this.paid = false;
    }

    public String getBookingId() {
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
