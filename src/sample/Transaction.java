package sample;

import java.time.*;

public class Transaction {

    private int transactionNumber = 1;
    private String whoIsSending;
    private String whoIsReceiving;
    private double moneyAmount;
    private LocalDateTime dateAndTime;

    public Transaction(int transactionNumber, String whoIsSending, String whoIsReceiving, double moneyAmount, LocalDateTime dateAndTime) {
        this.transactionNumber = transactionNumber;
        this.whoIsSending = whoIsSending;
        this.whoIsReceiving = whoIsReceiving;
        this.moneyAmount = moneyAmount;
        this.dateAndTime = dateAndTime;
    }
}
