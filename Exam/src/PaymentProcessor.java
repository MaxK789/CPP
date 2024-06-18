public class PaymentProcessor extends Thread {
    private Booking booking;

    public PaymentProcessor(Booking booking) {
        this.booking = booking;
    }

    @Override
    public void run() {
        System.out.println("Processing payment for booking ID: " + booking.getBookingId());
        try {
            Thread.sleep(2000); // Simulate payment processing time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        booking.setPaid(true);
        System.out.println("Payment successful for booking ID: " + booking.getBookingId());
    }
}
