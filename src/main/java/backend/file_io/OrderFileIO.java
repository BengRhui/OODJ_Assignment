package backend.file_io;

import backend.entity.*;
import backend.utility.Utility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class {@code OrderFileIO} contains the methods to read and write files related to orders.
 *
 * @author Beng Rhui (TP068495)
 */
public class OrderFileIO extends FileIO {

    /**
     * Fixed variables to aid in coding.
     */
    public final static String ORDER_FILE_NAME = "order_information.txt";
    public final static int NUMBER_OF_INFORMATION_IN_FILE = 12;
    public final static int[] SPACING_SIZE = {10, 5, 5, 5, 10, 15, 5, 60, 10, 20, 50, 200};

    /**
     * A method to read file and initialize {@code Order} objects.
     */
    public static void readFile() {

        // Reset list before reading files
        Order.getOrderList().clear();

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
     *
     * @param recordFromFile The string array from file
     * @return The {@code Order} object being created
     */
    public static Order createOrderObject(String[] recordFromFile) {

        // Retrieve information from the string array
        String orderID = recordFromFile[0];
        Customer orderingCustomer = recordFromFile[1].equalsIgnoreCase("null") ? null : Customer.getCustomer(recordFromFile[1]);
        Stall orderedStall = recordFromFile[2].equalsIgnoreCase("null") ? null : Stall.getStallByID(recordFromFile[2]);
        DeliveryRunner runnerInCharge = recordFromFile[3].equalsIgnoreCase("null") ? null : DeliveryRunner.getRunner(recordFromFile[3]);
        Double tipsForRunner = recordFromFile[4].equalsIgnoreCase("null") ? null : Double.parseDouble(recordFromFile[4]);
        Order.DiningType diningType = Order.DiningType.getFromString(recordFromFile[5]);
        String tableNumber = recordFromFile[6].equalsIgnoreCase("null") ? null : recordFromFile[6];
        String deliveryNote = recordFromFile[7];
        double orderPrice = Double.parseDouble(recordFromFile[8]);
        LocalDateTime orderedDate = Utility.changeStringToTime(recordFromFile[9]);
        Order.OrderStatus orderStatus = Order.OrderStatus.getFromString(recordFromFile[10]);
        Map<Item, Integer> orderItem = Utility.changeStringToHashMap(recordFromFile[10]);

        // Create and return a new item object
        return new Order(
                orderID,
                orderingCustomer,
                orderedStall,
                runnerInCharge,
                tipsForRunner,
                diningType,
                tableNumber,
                deliveryNote,
                orderPrice,
                orderedDate,
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
            record[1] = order.getOrderingCustomer() == null ? "null" : order.getOrderingCustomer().getUserID();
            record[2] = order.getOrderedStall() == null ? "null" : order.getOrderedStall().getStallID();
            record[3] = order.getRunnerInCharge() == null ? "null" : order.getRunnerInCharge().getUserID();
            record[4] = order.getTipsForRunner() == null ? "null" : String.valueOf(order.getTipsForRunner());
            record[5] = order.getDiningType().toString();
            record[6] = order.getTableNumber() == null ? "null" : order.getTableNumber();
            record[7] = order.getNoteToVendor();
            record[8] = String.valueOf(order.getOrderPrice());
            record[9] = Utility.generateString(order.getOrderedDate());
            record[10] = order.getOrderStatus().toString();
            record[11] = Utility.generateString(order.getOrderItem());

            // Add the record into list
            informationToFile.add(record);
        }

        // Write the list to item file
        writeListToFile(ORDER_FILE_NAME, informationToFile, SPACING_SIZE);
    }
}
