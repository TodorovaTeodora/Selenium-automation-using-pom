package TestingOnlineStore;

public enum Brand {
    DINERS("Diners"),
    DISCOVER("Discover"),
    MASTER_CARD("Mastercard"),
    VISA("Visa");

    Brand(String brandName) {
        this.brandName = brandName;
    }
    private String brandName;

    public String getBrandName() {
        return brandName;
    }
}
