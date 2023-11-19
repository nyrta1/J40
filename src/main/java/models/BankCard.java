package models;

public class BankCard {
    private Long id;
    private String username;
    private String cardNumber;
    private String cvv;
    private String expiryDate;

    public BankCard(String username, String cardNumber, String cvv, String expiryDate) {
        this.username = username;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public String getUsername() {
        return username;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
