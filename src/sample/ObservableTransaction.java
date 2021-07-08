package sample;

import javafx.beans.property.SimpleStringProperty;

  // a copy of TransactionList, used to look at Transactions in TableView
public class ObservableTransaction {

    private final SimpleStringProperty transactionNumber = new SimpleStringProperty();
    private final SimpleStringProperty senderFullName = new SimpleStringProperty();
    private final SimpleStringProperty receiverFullName = new SimpleStringProperty();
    private final SimpleStringProperty moneyAmountTransfered = new SimpleStringProperty();
    private final SimpleStringProperty dateAndTime = new SimpleStringProperty();
    private final SimpleStringProperty moneyLeft = new SimpleStringProperty();


    public ObservableTransaction(int transactionNumber, String senderFullName, String receiverFullName,
                       double moneyAmountTransfered, String dateAndTime, double moneyLeft) {

        this.transactionNumber.set(Integer.toString(transactionNumber));
        this.senderFullName.set(senderFullName);
        this.receiverFullName.set(receiverFullName);
        this.moneyAmountTransfered.set(Double.toString(moneyAmountTransfered));
        this.dateAndTime.set(dateAndTime);
        this.moneyLeft.set(Double.toString(moneyLeft));
    }

    // it shows that those methods below are not used anywhere but it´s a lie, because
      // without them the TableView can´t use this class and won´t work


    public SimpleStringProperty transactionNumberProperty() {
        return transactionNumber;
    }

    public SimpleStringProperty senderFullNameProperty() {
        return senderFullName;
    }

    public SimpleStringProperty receiverFullNameProperty() {
        return receiverFullName;
    }

    public SimpleStringProperty moneyAmountTransferedProperty() {
        return moneyAmountTransfered;
    }

    public SimpleStringProperty dateAndTimeProperty() {
        return dateAndTime;
    }

    public SimpleStringProperty moneyLeftProperty() {
        return moneyLeft;
    }
}




