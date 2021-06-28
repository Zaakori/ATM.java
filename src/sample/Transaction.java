package sample;

import javafx.beans.property.SimpleStringProperty;
import java.io.Serializable;


public class Transaction implements Serializable {

    private SimpleStringProperty transactionNumber;
    private SimpleStringProperty senderFullName;
    private SimpleStringProperty receiverFullName;
    private SimpleStringProperty moneyAmountTransfered;
    private SimpleStringProperty dateAndTime;
    private SimpleStringProperty moneyLeft;

    private long serialVersionUID = 2L;

    public Transaction(int transactionNumber, String senderFullName, String receiverFullName,
                       double moneyAmountTransfered, String dateAndTime, double moneyLeft) {

        this.transactionNumber.set(Integer.toString(transactionNumber));
        this.senderFullName.set(senderFullName);
        this.receiverFullName.set(receiverFullName);
        this.moneyAmountTransfered.set(Double.toString(moneyAmountTransfered));
        this.dateAndTime.set(dateAndTime);
        this.moneyLeft.set(Double.toString(moneyLeft));

    }

    public int getTransactionNumber() {
        return Integer.parseInt(transactionNumber.get());
    }

    public double getMoneyLeft() {
        return Double.parseDouble(moneyLeft.get());
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
