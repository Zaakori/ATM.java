package sample;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class ObservableTransaction implements Serializable {

    private SimpleStringProperty transactionNumber = new SimpleStringProperty();
    private SimpleStringProperty senderFullName = new SimpleStringProperty();
    private SimpleStringProperty receiverFullName = new SimpleStringProperty();
    private SimpleStringProperty moneyAmountTransfered = new SimpleStringProperty();
    private SimpleStringProperty dateAndTime = new SimpleStringProperty();
    private SimpleStringProperty moneyLeft = new SimpleStringProperty();

    private static final long serialVersionUID = 3L;


    public ObservableTransaction(int transactionNumber, String senderFullName, String receiverFullName,
                       double moneyAmountTransfered, String dateAndTime, double moneyLeft) {

        this.transactionNumber.set(Integer.toString(transactionNumber));
        this.senderFullName.set(senderFullName);
        this.receiverFullName.set(receiverFullName);
        this.moneyAmountTransfered.set(Double.toString(moneyAmountTransfered));
        this.dateAndTime.set(dateAndTime);
        this.moneyLeft.set(Double.toString(moneyLeft));

    }
}




