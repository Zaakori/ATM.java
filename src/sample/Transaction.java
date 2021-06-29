package sample;

import javafx.beans.property.SimpleStringProperty;
import java.io.Serializable;


public class Transaction implements Serializable {

    private int transactionNumber;
    private String senderFullName;
    private String receiverFullName;
    private double moneyAmountTransfered;
    private String dateAndTime;
    private double moneyLeft;

    private static final long serialVersionUID = 2L;

    public Transaction(int transactionNumber, String senderFullName, String receiverFullName, double moneyAmountTransfered,
                       String dateAndTime, double moneyLeft) {
        this.transactionNumber = transactionNumber;
        this.senderFullName = senderFullName;
        this.receiverFullName = receiverFullName;
        this.moneyAmountTransfered = moneyAmountTransfered;
        this.dateAndTime = dateAndTime;
        this.moneyLeft = moneyLeft;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public String getSenderFullName() {
        return senderFullName;
    }

    public String getReceiverFullName() {
        return receiverFullName;
    }

    public double getMoneyAmountTransfered() {
        return moneyAmountTransfered;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public double getMoneyLeft() {
        return moneyLeft;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionNumber=" + transactionNumber +
                ", senderFullName='" + senderFullName + '\'' +
                ", receiverFullName='" + receiverFullName + '\'' +
                ", moneyAmountTransfered=" + moneyAmountTransfered +
                ", dateAndTime=" + dateAndTime +
                ", moneyLeft=" + moneyLeft +
                '}';
    }
}
