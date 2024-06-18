public class Property {
    private int id;
    private String name;
    private String description;
    private double pricePerNight;
    private Landlord landlord;

    private static int nextId = 1; // Static variable to keep track of next available ID

    public Property(String name, String description, double pricePerNight, Landlord landlord) {
        this.id = nextId++;
        this.name = name;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.landlord = landlord;
    }

    // Getter methods

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public Landlord getLandlord() {
        return landlord;
    }

    @Override
    public String toString() {
        return "Property ID: " + id + ", Name: " + name + ", Description: " + description + ", Price per night: $" + pricePerNight;
    }
}
