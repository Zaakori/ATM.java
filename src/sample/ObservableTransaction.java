package sample;

import javafx.beans.property.SimpleStringProperty;


public class ObservableTransaction {

    private SimpleStringProperty transactionNumber = new SimpleStringProperty();
    private SimpleStringProperty senderFullName = new SimpleStringProperty();
    private SimpleStringProperty receiverFullName = new SimpleStringProperty();
    private SimpleStringProperty moneyAmountTransfered = new SimpleStringProperty();
    private SimpleStringProperty dateAndTime = new SimpleStringProperty();
    private SimpleStringProperty moneyLeft = new SimpleStringProperty();


    public ObservableTransaction(int transactionNumber, String senderFullName, String receiverFullName,
                       double moneyAmountTransfered, String dateAndTime, double moneyLeft) {

        this.transactionNumber.set(Integer.toString(transactionNumber));
        this.senderFullName.set(senderFullName);
        this.receiverFullName.set(receiverFullName);
        this.moneyAmountTransfered.set(Double.toString(moneyAmountTransfered));
        this.dateAndTime.set(dateAndTime);
        this.moneyLeft.set(Double.toString(moneyLeft));

    }

    public String getTransactionNumber() {
        return transactionNumber.get();
    }

    public SimpleStringProperty transactionNumberProperty() {
        return transactionNumber;
    }

    public String getSenderFullName() {
        return senderFullName.get();
    }

    public SimpleStringProperty senderFullNameProperty() {
        return senderFullName;
    }

    public String getReceiverFullName() {
        return receiverFullName.get();
    }

    public SimpleStringProperty receiverFullNameProperty() {
        return receiverFullName;
    }

    public String getMoneyAmountTransfered() {
        return moneyAmountTransfered.get();
    }

    public SimpleStringProperty moneyAmountTransferedProperty() {
        return moneyAmountTransfered;
    }

    public String getDateAndTime() {
        return dateAndTime.get();
    }

    public SimpleStringProperty dateAndTimeProperty() {
        return dateAndTime;
    }

    public String getMoneyLeft() {
        return moneyLeft.get();
    }

    public SimpleStringProperty moneyLeftProperty() {
        return moneyLeft;
    }
}




