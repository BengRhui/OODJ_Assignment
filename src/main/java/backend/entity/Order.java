package backend.entity;

import backend.utility.Utility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class {@code Order} represents the order placed by the customer via the system.
 *
 * @author Beng Rhui (TP068495)
 */
public class Order {

    /**
     * Attributes for the {@code Order} object.<br>
     * A list that collects all orders is included.
     */
    private final static ArrayList<Order> orderList = new ArrayList<>();
    private String orderID;
    private Customer orderingCustomer;
    private Stall orderedStall;
    private DeliveryRunner runnerInCharge;
    private DiningType diningType;
    private String tableNumber;
    private String deliveryNote;
    private double orderPrice;
    private LocalDateTime orderedDate;
    private double tipsForRunner;
    private OrderStatus orderStatus;
    private HashMap<Item, Integer> orderItem;

    /**
     * Constructor to instantiate {@code Order} objects.
     *
     * @param orderID          The ID of the order
     * @param orderingCustomer The customer who places the order
     * @param orderedStall     The stall where the customer places the order
     * @param runnerInCharge   The delivery runner delivering the food (if available)
     * @param diningType       The dining type preferred by user
     * @param tableNumber      The table number of user (for eat-in)
     * @param deliveryNote     The delivery note that user wishes to inform runner
     * @param orderPrice       The total payment of the order
     * @param orderedDate      The date where the order is made
     * @param tipsForRunner    The delivery tips that customer gives to runner
     * @param orderStatus      The current status of order
     * @param orderItem        The list of items ordered by customer
     */
    public Order(String orderID, Customer orderingCustomer, Stall orderedStall, DeliveryRunner runnerInCharge,
                 DiningType diningType, String tableNumber, String deliveryNote, double orderPrice, LocalDateTime orderedDate,
                 double tipsForRunner, OrderStatus orderStatus, HashMap<Item, Integer> orderItem) {
        this.orderID = orderID;
        this.orderingCustomer = orderingCustomer;
        this.orderedStall = orderedStall;
        this.runnerInCharge = runnerInCharge;
        this.diningType = diningType;
        this.tableNumber = tableNumber;
        this.deliveryNote = deliveryNote;
        this.orderPrice = orderPrice;
        this.orderedDate = orderedDate;
        this.tipsForRunner = tipsForRunner;
        this.orderStatus = orderStatus;
        this.orderItem = orderItem;
    }

    /**
     * A method to return a list consisting of {@code Order} objects.
     *
     * @return An ArrayList containing all {@code Order} objects
     */
    public static ArrayList<Order> getOrderList() {
        return orderList;
    }

    /**
     * A method to add {@code Order} objects into the overall list.
     *
     * @param order The {@code Order} object to be added into list.
     */
    public static void addToOrderList(Order order) {
        orderList.add(order);
    }

    /**
     * A method to retrieve {@code Order} object based on order ID.
     *
     * @param orderID The ID of the order
     * @return {@code Order} object associated with the ID
     */
    public static Order getOrder(String orderID) {

        // Loop through the list of order objects
        for (Order order : orderList) {

            // Continue the loop if order ID does not match
            if (!order.orderID.equals(orderID)) {
                continue;
            }

            // Return the associated order object if order ID matches
            return order;
        }

        // Return null if no order ID matches
        return null;
    }

    /**
     * Getters and setters for {@code Order} class
     */
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Customer getOrderingCustomer() {
        return orderingCustomer;
    }

    public void setOrderingCustomer(Customer orderingCustomer) {
        this.orderingCustomer = orderingCustomer;
    }

    public Stall getOrderedStall() {
        return orderedStall;
    }

    public void setOrderedStall(Stall orderedStall) {
        this.orderedStall = orderedStall;
    }

    public DeliveryRunner getRunnerInCharge() {
        return runnerInCharge;
    }

    public void setRunnerInCharge(DeliveryRunner runnerInCharge) {
        this.runnerInCharge = runnerInCharge;
    }

    public DiningType getDiningType() {
        return diningType;
    }

    public void setDiningType(DiningType diningType) {
        this.diningType = diningType;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public LocalDateTime getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(LocalDateTime orderedDate) {
        this.orderedDate = orderedDate;
    }

    public double getTipsForRunner() {
        return tipsForRunner;
    }

    public void setTipsForRunner(double tipsForRunner) {
        this.tipsForRunner = tipsForRunner;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public HashMap<Item, Integer> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(HashMap<Item, Integer> orderItem) {
        this.orderItem = orderItem;
    }

    /**
     * A method to return the {@code Order} object in string format.
     *
     * @return The string representation of the {@code Order} object
     */
    @Override
    public String toString() {
        return "Order ID: " + orderID + "\n" +
                "Ordering Customer: " + "\n" +
                orderingCustomer.toString() + "\n" +
                "Ordered Stall: " + "\n" +
                orderedStall.toString() + "\n" +
                "Runner in Charge: " + "\n" +
                runnerInCharge.toString() + "\n" +
                "Dining Type: " + diningType.toString() + "\n" +
                "Table Number: " + tableNumber + "\n" +
                "Delivery Note: " + deliveryNote + "\n" +
                "Order Price: " + orderPrice + "\n" +
                "Ordered Date: " + Utility.generateString(orderedDate) + "\n" +
                "Tips for Runner: " + tipsForRunner + "\n" +
                "Order Status: " + orderStatus.toString() + "\n" +
                "Order Items: " + Utility.generateString(orderItem);
    }

    /**
     * Enum {@code DiningType} represents the different types of dining methods a customer can choose.
     */
    public enum DiningType {

        /**
         * Fields available in {@code DiningType}.
         */
        DINE_IN, TAKEAWAY, DELIVERY;

        /**
         * A variable that stores the list of available dining options.
         */
        public final static String[] DINING_OPTIONS = Arrays.stream(DiningType.values())   // Get fields
                .map(DiningType::toString)                                                 // Convert to string
                .toArray(String[]::new);                                                   // Make it as String[]

        /**
         * A method to get {@code DiningType} from string input.
         *
         * @param input The string input representing dining types
         * @return The {@code DiningType} field corresponding to the input
         */
        public static DiningType getFromString(String input) {
            return Arrays.stream(DiningType.values())                          // Retrieve the values of enum
                    .filter(field -> field.toString().equalsIgnoreCase(input))  // Filter the values based on input
                    .findFirst()                                                // Find the first occurrence
                    .orElse(null);                                              // Return null if nothing matches
        }

        /**
         * A method that returns the corresponding string value of {@code DiningType}.
         *
         * @return The string representation of {@code DiningType}
         */
        @Override
        public String toString() {
            return switch (this) {
                case DINE_IN -> "Dine In";
                case TAKEAWAY -> "Takeaway";
                case DELIVERY -> "Delivery";
            };
        }
    }

    /**
     * Enum {@code OrderStatus} represents the different types of status an order can have.
     */
    public enum OrderStatus {

        /**
         * Fields for order status
         */
        WAITING_VENDOR_AND_RUNNER, WAITING_VENDOR, WAITING_RUNNER, VENDOR_PREPARING,
        READY_FOR_PICK_UP, RUNNER_DELIVERY, COMPLETED;

        /**
         * Variables containing different types of options for an order
         */
        public final static String[] OPTIONS_FOR_DINE_IN = Arrays.stream(OrderStatus.values())   // Get the fields
                .map(OrderStatus::toString)                                                      // Convert to string
                .filter(string -> !string.contains("Deliver") && !string.contains("Pick Up"))    // Remove delivery and pickup
                .toArray(String[]::new);                                                         // Return string array

        public final static String[] OPTIONS_FOR_TAKEAWAY = Arrays.stream(OrderStatus.values())  // Get the fields
                .map(OrderStatus::toString)                                                      // Convert to string
                .filter(string -> !string.contains("Deliver"))                                   // Remove delivery ones
                .toArray(String[]::new);                                                         // Return string array

        public final static String[] OPTIONS_FOR_DELIVERY = Arrays.stream(OrderStatus.values())  // Get the fields
                .map(OrderStatus::toString)                                                      // Convert to string
                .toArray(String[]::new);                                                         // Return string array

        /**
         * A method to get the corresponding {@code OrderStatus} from string input.
         *
         * @param input The string input provided by user
         * @return The {@code OrderStatus} corresponding to the string input
         */
        public static OrderStatus getFromString(String input) {
            return Arrays.stream(OrderStatus.values())                           // Get the values of enum
                    .filter(field -> field.toString().equalsIgnoreCase(input))   // Filter with input
                    .findFirst()                                                 // Find the first occurrence
                    .orElse(null);                                               // Return null if not found
        }

        /**
         * A method to return the string value of {@code OrderStatus}.
         *
         * @return The string representation of the fields of {@code OrderStatus}
         */
        @Override
        public String toString() {
            return switch (this) {
                case WAITING_VENDOR_AND_RUNNER -> "Waiting for Vendor and Delivery Confirmation";
                case WAITING_VENDOR -> "Waiting for Vendor Confirmation";
                case WAITING_RUNNER -> "Waiting for Delivery Confirmation";
                case VENDOR_PREPARING -> "Vendor Preparing";
                case READY_FOR_PICK_UP -> "Ready for Pick Up";
                case RUNNER_DELIVERY -> "Delivering by Runner";
                case COMPLETED -> "Completed";
            };
        }
    }
}
