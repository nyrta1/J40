package models;

public class BankCard {
    private Long id;
    private String username;
    private String cardNumber;
    private String cvv;
    private String expiryDate;
    private String bankName;

    public BankCard(String username, String cardNumber, String cvv, String expiryDate, String bankName) {
        this.username = username;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.bankName = bankName;
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

    public String getBankName() {
        return bankName;
    }
}
