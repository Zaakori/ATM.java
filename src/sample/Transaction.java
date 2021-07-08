package sample;

import java.io.Serializable;

  // it´s a Transaction, simply put it´s just a record with all the info about the Transaction
public class Transaction implements Serializable {

    private final int transactionNumber;
    private final String senderFullName;
    private final String receiverFullName;
    private final double moneyAmountTransfered;
    private final String dateAndTime;
    private final double moneyLeft;

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
