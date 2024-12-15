package backend.file_io;

import backend.entity.Customer;
import backend.entity.Transaction;
import backend.utility.Utility;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class {@code TransactionFileIO} consists of the methods to read and write transaction files.
 *
 * @author Beng Rhui (TP068495)
 */
public class TransactionFileIO extends FileIO {

    /**
     * Fixed variables to help in coding.
     */
    private final static String TRANSACTION_FILE_NAME = "transaction_history.txt";
    private final static int NUMBER_OF_INFORMATION_IN_FILE = 6;
    private final static int[] SPACING_SIZE = {10, 5, 20, 10, 10, 20};

    /**
     * A method to read from transaction file and create {@code Transaction} objects.
     */
    public static void readFile() {

        // Retrieve list of transactions from text file
        ArrayList<String[]> transactionList = getListFromFile(TRANSACTION_FILE_NAME);

        // Loop through each record
        for (String[] transaction : transactionList) {

            // Create transaction objects based on the record and add to overall list
            Transaction newTransaction = createTransactionObject(transaction);
            Transaction.addToList(newTransaction);
        }
    }

    /**
     * A method to create {@code Transaction} objects from string record from text file.
     *
     * @param recordFromFile The string record obtained from text file
     * @return The {@code Transaction} object being created
     */
    public static Transaction createTransactionObject(String[] recordFromFile) {

        // Retrieve information from string array
        String transactionID = recordFromFile[0];
        Customer customer = Customer.getCustomer(recordFromFile[1]);
        LocalDateTime transactionTime = Utility.changeStringToTime(recordFromFile[2]);
        double transactionAmount = Double.parseDouble(recordFromFile[3]);
        Transaction.TransactionType transactionType = Transaction.TransactionType.getType(recordFromFile[4]);
        Transaction.PaymentMethod paymentMethod = Transaction.PaymentMethod.getType(recordFromFile[5]);

        // Create and return new transaction object
        return new Transaction(
                transactionID,
                customer,
                transactionTime,
                transactionAmount,
                transactionType,
                paymentMethod
        );
    }

    /**
     * A method to write {@code Transaction} objects into file.
     */
    public static void writeFile() {

        // Retrieve the list of all transaction objects
        ArrayList<Transaction> transactionList = Transaction.getTransactionList();

        // Create a list to store data to be written to file
        ArrayList<String[]> information = new ArrayList<>();

        // Loop through each transaction object
        for (Transaction transaction : transactionList) {

            // Retrieve the necessary information and add to list
            String[] record = new String[NUMBER_OF_INFORMATION_IN_FILE];
            record[0] = transaction.getTransactionID();
            record[1] = transaction.getCustomer().getUserID();
            record[2] = Utility.generateString(transaction.getTransactionTime());
            record[3] = Double.toString(transaction.getTransactionAmount());
            record[4] = transaction.getTransactionType().toString();
            record[5] = transaction.getPaymentMethod().toString();
            information.add(record);
        }

        // Write information into file
        writeListToFile(TRANSACTION_FILE_NAME, information, SPACING_SIZE);
    }
}
