package TestingOnlineStore;

public class CheckOutDetails {
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    public CheckOutDetails() {
        this.street = "Schoenebergerstrasse 99";
        this.state = "Schwarzenberg";
        this.city = "Schwarzenberg";
        this.country = "Germany";
        this.zipCode = "08355";
    }

    public String getStreet() {
        return street;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
    public String getZipCode() {
        return zipCode;
    }
}
