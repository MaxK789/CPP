public class Property {
    private String id;
    private String name;
    private String description;
    private double pricePerNight;
    private Landlord landlord;

    public Property(String id, String name, String description, double pricePerNight, Landlord landlord) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.landlord = landlord;
    }

    public String getId() {
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
