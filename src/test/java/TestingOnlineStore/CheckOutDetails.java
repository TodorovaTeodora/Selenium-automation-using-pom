package TestingOnlineStore;

public class CheckOutDetails {
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    public CheckOutDetails() {
        this.street = "101 Bulgaria Boulevard";
        this.state = "Sofia-city";
        this.city = "Sofia";
        this.country = "Bulgaria";
        this.zipCode = "1000";
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
