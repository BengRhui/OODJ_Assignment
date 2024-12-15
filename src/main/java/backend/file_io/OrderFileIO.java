package backend.file_io;

import backend.entity.*;
import backend.utility.Utility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class {@code OrderFileIO} contains the methods to read and write files related to orders.
 * @author Beng Rhui (TP068495)
 */
public class OrderFileIO extends FileIO {

    /**
     * Fixed variables to aid in coding.
     */
    private final static String ORDER_FILE_NAME = "order_information.txt";
    private final static int NUMBER_OF_INFORMATION_IN_FILE = 12;
    private final static int[] SPACING_SIZE = {10, 5, 5, 5, 15, 5, 60, 10, 20, 10, 50, 200};

    /**
     * A method to read file and initialize {@code Order} objects.
     */
    public static void readFile() {

        // Retrieve information from text file
        ArrayList<String[]> orderList = getListFromFile(ORDER_FILE_NAME);

        // Loop through each information
        for (String[] record : orderList) {

            // Create order object based and add to list
            Order newOrder = createOrderObject(record);
            Order.addToOrderList(newOrder);
        }
    }

    /**
     * A method to create {@code Order} objects based on records from file.
     * @param recordFromFile The string array from file
     * @return The {@code Order} object being created
     */
    public static Order createOrderObject(String[] recordFromFile) {

        // Retrieve information from the string array
        String orderID                   = recordFromFile[0];
        Customer orderingCustomer        = Customer.getCustomer(recordFromFile[1]);
        Stall orderedStall               = Stall.getStall(recordFromFile[2]);
        DeliveryRunner runnerInCharge    = DeliveryRunner.getRunner(recordFromFile[3]);
        String diningType                = recordFromFile[4];
        String tableNumber               = recordFromFile[5];
        String deliveryNote              = recordFromFile[6];
        double orderPrice                = Double.parseDouble(recordFromFile[7]);
        LocalDateTime orderedDate        = Utility.changeStringToTime(recordFromFile[8]);
        double tipsForRunner             = Double.parseDouble(recordFromFile[9]);
        String orderStatus               = recordFromFile[10];
        HashMap<Item, Integer> orderItem = Utility.changeStringToHashMap(recordFromFile[11]);

        // Create and return a new item object
        return new Order(
                orderID,
                orderingCustomer,
                orderedStall,
                runnerInCharge,
                diningType,
                tableNumber,
                deliveryNote,
                orderPrice,
                orderedDate,
                tipsForRunner,
                orderStatus,
                orderItem
        );
    }

    /**
     * A method to write {@code Order} objects into order information file.
     */
    public static void writeFile() {

        // Retrieve the list of all item objects
        ArrayList<Order> orderList = Order.getOrderList();

        // Create a list to store the information to be written to text file
        ArrayList<String[]> informationToFile = new ArrayList<>();

        // Loop through each item object
        for (Order order : orderList) {

            // Retrieve the information and add to list
            String[] record = new String[NUMBER_OF_INFORMATION_IN_FILE];

            // Retrieve information from order object
            record[0] = order.getOrderID();
            record[1] = order.getOrderingCustomer().getUserID();
            record[2] = order.getOrderedStall().getStallID();
            record[3] = order.getRunnerInCharge().getUserID();
            record[4] = order.getDiningType();
            record[5] = order.getTableNumber();
            record[6] = order.getDeliveryNote();
            record[7] = String.valueOf(order.getOrderPrice());
            record[8] = Utility.generateString(order.getOrderedDate());
            record[9] = String.valueOf(order.getTipsForRunner());
            record[10] = order.getOrderStatus();
            record[11] = Utility.generateString(order.getOrderItem());

            // Add the record into list
            informationToFile.add(record);
        }

        // Write the list to item file
        writeListToFile(ORDER_FILE_NAME, informationToFile, SPACING_SIZE);
    }
}
