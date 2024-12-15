package backend.entity;

import backend.utility.Utility;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class {@code Transaction} represents the transaction that occurs during purchasing items and topping up.
 * @author Beng Rhui (TP068495)
 */
public class Transaction {

    /**
     * Attributes for the {@code Transaction} class.<br>
     * An overall list containing all transactions are also included.
     */
    private String transactionID;
    private Customer customer;
    private LocalDateTime transactionTime;
    private double transactionAmount;
    private TransactionType transactionType;
    private PaymentMethod paymentMethod;

    private final static ArrayList<Transaction> transactionList = new ArrayList<>();

    /**
     * Enum {@code TransactionType} consist of the different types of transactions involved
     */
    public enum TransactionType {

        /**
         * Default types of transaction
         */
        CASH_IN, CASH_OUT;

        /**
         * A method to convert string into {@code TransactionType}
         * @param transactionType The string input of transaction type
         * @return The {@code TransactionType} corresponding to the string input
         */
        public static TransactionType getType(String transactionType) {
            return switch (transactionType.toLowerCase()) {
                case "cash in" -> CASH_IN;
                case "cash out" -> CASH_OUT;
                default -> null;
            };
        }

        /**
         * A method to return the transaction types as strings
         * @return String representation of transaction types
         */
        @Override
        public String toString() {
            return switch (this) {
                case CASH_IN -> "Cash In";
                case CASH_OUT -> "Cash Out";
            };
        }
    }

    /**
     * Enum {@code PaymentMethod} consist of different payment methods available
     */
    public enum PaymentMethod {

        /**
         * Default types of payment method
         */
        QR_PAYMENT, CASH, DEBIT_CREDIT_CARD, E_WALLET;

        /**
         * A method to convert string into {@code PaymentMethod}
         * @param paymentMethod The string input of payment method
         * @return The {@code PaymentMethod} corresponding to the string input
         */
        public static PaymentMethod getType(String paymentMethod) {
            return switch (paymentMethod.toLowerCase()) {
                case "qr payment" -> QR_PAYMENT;
                case "cash" -> CASH;
                case "debit / credit card" -> DEBIT_CREDIT_CARD;
                case "e-wallet" -> E_WALLET;
                default -> null;
            };
        }

        /**
         * A method to return the payment methods in string
         * @return String representation of payment methods
         */
        @Override
        public String toString() {
            return switch (this) {
                case QR_PAYMENT -> "QR Payment";
                case CASH -> "Cash";
                case DEBIT_CREDIT_CARD -> "Debit / Credit Card";
                case E_WALLET -> "E-Wallet";
            };
        }
    }

    /**
     * Constructor to instantiate {@code Transaction} object
     * @param transactionID The ID of the transaction
     * @param customer The customer related to the transaction
     * @param transactionTime The time when transaction took place
     * @param transactionAmount The amount involved in the transaction
     * @param transactionType The types of transaction (cash in or cash out)
     * @param paymentMethod The payment method involved
     */
    public Transaction(String transactionID, Customer customer, LocalDateTime transactionTime,
                       double transactionAmount, TransactionType transactionType, PaymentMethod paymentMethod) {
        this.transactionID = transactionID;
        this.customer = customer;
        this.transactionTime = transactionTime;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.paymentMethod = paymentMethod;
    }

    /**
     * Getters and setters for the class {@code Transaction}
     */
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * A method to return the list of transactions involved.
     * @return ArrayList containing all {@code Transaction} objects
     */
    public static ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }

    /**
     * A method to add transactions into an overall list.
     * @param transaction The {@code Transaction} object to be added to list
     */
    public static void addToList(Transaction transaction) {
        transactionList.add(transaction);
    }

    /**
     * A method to print out the information of the transaction
     * @return The string representation of {@code Transaction} objects
     */
    @Override
    public String toString() {
        return "Transaction ID:" + transactionID + "\n" +
                "Customer Involved:" + "\n" +
                customer + "\n" +
                "Transaction Time:" + Utility.generateString(transactionTime) + "\n" +
                "Transaction Amount:" + transactionAmount + "\n" +
                "Transaction Type: " + transactionType.toString() + "\n" +
                "Payment Method: " + paymentMethod.toString();
    }
}

