package backend.entity;

import backend.file_io.TransactionFileIO;
import backend.utility.Utility;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class {@code Transaction} represents the transaction that occurs during purchasing items and topping up.
 *
 * @author Beng Rhui (TP068495)
 */
public class Transaction {

    /**
     * Attributes for the {@code Transaction} class.<br>
     * An overall list containing all transactions are also included.
     */
    private final static ArrayList<Transaction> transactionList = new ArrayList<>();
    private final static String[] receiptTableHeaders = new String[]{"Description", "Quantity", "Price"};
    private final static int[] receiptColumnPosition = new int[]{40, 325, 450};
    private String transactionID;
    private Customer customer;
    private LocalDateTime transactionTime;
    private double transactionAmount;
    private TransactionType transactionType;
    private PaymentMethod paymentMethod;

    /**
     * Constructor to instantiate {@code Transaction} object
     *
     * @param transactionID     The ID of the transaction
     * @param customer          The customer related to the transaction
     * @param transactionTime   The time when transaction took place
     * @param transactionAmount The amount involved in the transaction
     * @param transactionType   The types of transaction (cash in or cash out)
     * @param paymentMethod     The payment method involved
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
     * A method to return the list of transactions involved.
     *
     * @return ArrayList containing all {@code Transaction} objects
     */
    public static ArrayList<Transaction> getTransactionList() {

        // Sort transaction list in descending order based on time
        transactionList.sort(Comparator.comparing(Transaction::getTransactionTime).reversed());

        // Return transaction list
        return transactionList;
    }

    /**
     * A method to get transaction list after filtered using customer.
     *
     * @param customer The customer that will be used for filter
     * @return The filtered transaction list
     */
    public static ArrayList<Transaction> getTransactionList(Customer customer) {

        // Filter transaction based on customer ID, then sort them descending based on transaction time
        return transactionList.stream()
                .filter(transaction -> transaction.customer.userID.equals(customer.userID))
                .sorted(Comparator.comparing(Transaction::getTransactionTime).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A method to filter transaction list based on transaction type (cash in and cash out).
     *
     * @param customer        The customer involved
     * @param transactionType The transaction type used for filter
     * @return The filtered transaction list
     */
    public static ArrayList<Transaction> getTransactionList(Customer customer, TransactionType transactionType) {

        // Filter transaction list based on transaction type, and sort descending based on transaction time
        return getTransactionList(customer).stream()
                .filter(transaction -> transaction.getTransactionType() == transactionType)
                .sorted(Comparator.comparing(Transaction::getTransactionTime).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * A method to add transactions into an overall list.
     *
     * @param transaction The {@code Transaction} objects to be added to list
     */
    public static void addToList(Transaction... transaction) {

        // Throws an error if there is no transaction passed into the argument, or a null transaction is passed into argument
        if (transaction.length == 0 || Arrays.stream(transaction).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Arguments should contain at least one Transaction object");
        }

        // Add all the transactions from the arguments into the list
        transactionList.addAll(
                Arrays.asList(transaction)
        );
    }

    /**
     * A method to generate a new transaction ID.
     *
     * @return A new transaction ID that is not in use currently
     */
    public static String generateTransactionID() {

        // Declare a variable to store index
        int index = 1;

        // Start a loop
        while (true) {

            // Generate the transaction ID
            String generatedID = String.format("TRANS%04d", index);

            // Check if the ID is used
            boolean isIdUsed = transactionList.stream()
                    .anyMatch(transaction -> transaction.getTransactionID().equals(generatedID));

            // If the ID is not used, return the ID
            if (!isIdUsed) return generatedID;

            // If the ID is used, increment index and proceed to the next iteration
            index++;
        }
    }

    /**
     * A method to obtain transaction based on ID
     *
     * @param ID The ID that related to a transaction
     * @return The {@code Transaction} object corresponding to the ID
     */
    public static Transaction getTransaction(String ID) {

        // Loop through the list of transactions
        for (Transaction transaction : transactionList) {

            // Continue loop if ID does not match
            if (!transaction.getTransactionID().equals(ID)) {
                continue;
            }

            // Return transaction object if ID matches
            return transaction;
        }

        // Return null if there is no matching ID
        return null;
    }

    /**
     * A method to create records for a transaction
     *
     * @param customer          The customer involved
     * @param transactionAmount The amount of transaction
     * @param transactionType   The type of transaction
     * @param paymentMethod     The way customer paid for the transaction
     * @return {@code true} if the transaction is created successfully, else {@code false}
     */
    public static boolean createTransactionHistory(
            Customer customer,
            double transactionAmount,
            TransactionType transactionType,
            PaymentMethod paymentMethod) {

        // If the details are null, return false
        if (customer == null || transactionAmount <= 0 || transactionType == null || paymentMethod == null)
            return false;

        // Create a new transaction
        Transaction newTransaction = new Transaction(
                generateTransactionID(),
                customer,
                LocalDateTime.now(),
                transactionAmount,
                transactionType,
                paymentMethod
        );

        // Add the transaction to list
        addToList(newTransaction);

        // Write to file
        TransactionFileIO.writeFile();

        // Return true for successful operation
        return true;
    }

    /**
     * A method to delete all the transaction history of customers.
     *
     * @param customerID The ID of the customer
     * @return {@code true} if the operation is successful, else {@code false}
     */
    public static boolean deleteTransaction(String customerID) {

        // Return false if customer ID is blank
        if (customerID == null || customerID.isBlank()) return false;

        // Get the list of the associated transaction
        ArrayList<Transaction> customerTransaction = transactionList.stream()
                .filter(transaction -> transaction.getCustomer().getUserID().equals(customerID))
                .collect(Collectors.toCollection(ArrayList::new));

        // Delete these transactions from the array
        transactionList.removeAll(customerTransaction);

        // Write to file and return true
        TransactionFileIO.writeFile();
        return true;
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
     * A method to generate receipt in PDF format for transactions.
     * @return {@code true} if the receipt is generated successfully, else {@code false}
     */
    public boolean generateReceipt() {

        // Pass the current object to the method that generates PDF
        return Utility.generatePDF(this, receiptTableHeaders, receiptColumnPosition);
    }

    /**
     * A method to print out the information of the transaction
     *
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
         *
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
         *
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
         * A list of payment method that contains the possible top-up methods.
         */
        public final static String[] TOP_UP_METHOD = Arrays.stream(values())
                .filter(method -> method != E_WALLET)
                .map(PaymentMethod::toString)
                .toArray(String[]::new);

        /**
         * A method to convert string into {@code PaymentMethod}
         *
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
         *
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
}

