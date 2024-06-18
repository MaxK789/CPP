public class BookingProcessor extends Thread {
    private Property property;
    private Customer customer;

    public BookingProcessor(Property property, Customer customer) {
        this.property = property;
        this.customer = customer;
    }

    @Override
    public void run() {
        System.out.println("Processing booking for property ID: " + property.getId());
        try {
            // Simulate booking processing time (wait for 3 seconds)
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Booking booking = new Booking(property, customer);
        TravelAgency.getInstance().addBooking(booking);
        System.out.println("Booking successful. Booking ID: " + booking.getBookingId());
    }
}
