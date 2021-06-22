package sample;

import java.io.Serializable;
import java.time.*;

public class Transaction implements Serializable {

    private int transactionNumber;
    private String senderFullName;
    private String receiverFullName;
    private double moneyAmountTransfered;
    private LocalDateTime dateAndTime;
    private double moneyLeft;

    private long serialVersionUID = 2L;

    public Transaction(int transactionNumber, String senderFullName, String receiverFullName,
                       double moneyAmountTransfered, LocalDateTime dateAndTime, double moneyLeft) {

        this.transactionNumber = transactionNumber;
        this.senderFullName = senderFullName;
        this.receiverFullName = receiverFullName;
        this.moneyAmountTransfered = moneyAmountTransfered;
        this.dateAndTime = dateAndTime;
        this.moneyLeft = moneyLeft;
    }
}
