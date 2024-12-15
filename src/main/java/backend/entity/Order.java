package backend.entity;

import backend.utility.Utility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class {@code Order} represents the order placed by the customer via the system.
 *
 * @author Beng Rhui (TP068495)
 */
public class Order {

    private final static ArrayList<Order> orderList = new ArrayList<>();
    public final String[] DINING_TYPES = {"Dine in", "Takeaway", "Delivery"};
    public final String[] ORDER_STATUS = {"Waiting for Vendor and Delivery", "Waiting for Vendor", "Waiting for Delivery"};
    /**
     * Attributes for the {@code Order} object.<br>
     * A list that collects all orders is included.<br>
     * Two lists containing delivery types and order status are also included.
     */
    private String orderID;
    private Customer orderingCustomer;
    private Stall orderedStall;
    private DeliveryRunner runnerInCharge;
    private String diningType;
    private String tableNumber;
    private String deliveryNote;
    private double orderPrice;
    private LocalDateTime orderedDate;
    private double tipsForRunner;
    private String orderStatus;
    private HashMap<String, Integer> orderItem;

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
                 String diningType, String tableNumber, String deliveryNote, double orderPrice, LocalDateTime orderedDate,
                 double tipsForRunner, String orderStatus, HashMap<String, Integer> orderItem) {
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

    public String getDiningType() {
        return diningType;
    }

    public void setDiningType(String diningType) {
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public HashMap<String, Integer> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(HashMap<String, Integer> orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    public String toString() {

        StringBuilder itemString = new StringBuilder();
        for (HashMap.Entry<String, Integer> entry : orderItem.entrySet()) {
            String currentItem = "- " + entry.getKey() + " x " + entry.getValue() + "\n";
            itemString.append(currentItem);
        }

        return "Order ID: " + orderID + "\n" +
                "Ordering Customer: " + "\n" +
                orderingCustomer.toString() + "\n" +
                "Ordered Stall: " + "\n" +
                orderedStall.toString() + "\n" +
                "Runner in Charge: " + "\n" +
                runnerInCharge.toString() + "\n" +
                "Dining Type: " + diningType + "\n" +
                "Table Number: " + tableNumber + "\n" +
                "Delivery Note: " + deliveryNote + "\n" +
                "Order Price: " + orderPrice + "\n" +
                "Ordered Date: " + Utility.generateString(orderedDate) + "\n" +
                "Tips for Runner: " + tipsForRunner + "\n" +
                "Order Status: " + orderStatus + "\n" +
                "Order Items: " + itemString;
    }
}
