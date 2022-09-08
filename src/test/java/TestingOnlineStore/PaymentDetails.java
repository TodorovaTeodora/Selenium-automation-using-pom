package TestingOnlineStore;

public class PaymentDetails {
    private Brand cardBrand;
    private String cardNumber;
    private String cardHolderName;
    private String expirationMonth;
    private String expirationYear;
    private String cvc;


    public PaymentDetails(Brand cardBrand, String cardNumber, String cardHolderName, String expirationMonth, String expirationYear, String cvc) {
       this.cardBrand = cardBrand;
       this.cardHolderName = cardHolderName;
       this.cardNumber = cardNumber;
       this.expirationMonth = expirationMonth;
       this.expirationYear = expirationYear;
       this.cvc = cvc;
    }

    public PaymentDetails() {
        this.cardBrand = Brand.MASTER_CARD;
        this.cardHolderName = "Luke Skywalker";
        this.cardNumber = "5555555555554444";
        this.expirationMonth = "11";
        this.expirationYear = "2023";
        this.cvc = "123";
    }
    public Brand getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(Brand cardBrand) {
        this.cardBrand = cardBrand;
    }

    public String getcardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

}
