package backend.entity;

import backend.file_io.OrderFileIO;
import backend.notification.CustomerNotification;
import backend.notification.DeliveryRunnerNotification;
import backend.notification.VendorNotification;
import backend.utility.Utility;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class {@code Order} represents the order placed by the customer via the system.
 *
 * @author Beng Rhui (TP068495)
 */
public class Order {

    /**
     * Attributes for the {@code Order} object.<br>
     * A list that collects all orders and the lists used to write Excel files are included.
     */
    private final static ArrayList<Order> orderList = new ArrayList<>();
    private final static String[] orderHeaderList = {
            "Order ID",
            "Order Time",
            "Customer Name",
            "Customer Contact No.",
            "Stall Name",
            "Delivery Runner Name",
            "Table Number",
            "Item Name",
            "Price Per Unit (RM)",
            "Quantity",
            "Order Amount (RM)",
            "Order Status",
            "Delivery Notes"
    };
    private final static int[] orderColumnWidth = {
            256 * 15,
            256 * 25,
            256 * 25,
            256 * 25,
            256 * 25,
            256 * 25,
            256 * 15,
            256 * 30,
            256 * 20,
            256 * 10,
            256 * 20,
            256 * 25,
            256 * 40
    };

    private String orderID;
    private Customer orderingCustomer;
    private Stall orderedStall;
    private DeliveryRunner runnerInCharge;
    private Double tipsForRunner;
    private DiningType diningType;
    private String tableNumber;
    private String noteToVendor;
    private double orderPrice;
    private LocalDateTime orderedDate;
    private OrderStatus orderStatus;
    private Map<Item, Integer> orderItem;

    /**
     * Constructor to instantiate {@code Order} objects.
     *
     * @param orderID          The ID of the order
     * @param orderingCustomer The customer who places the order
     * @param orderedStall     The stall where the customer places the order
     * @param runnerInCharge   The delivery runner delivering the food (if available)
     * @param diningType       The dining type preferred by user
     * @param tableNumber      The table number of user (for eat-in)
     * @param noteToVendor     The note that user wishes to inform vendor when preparing for food
     * @param orderPrice       The total payment of the order
     * @param orderedDate      The date where the order is made
     * @param orderStatus      The current status of order
     * @param orderItem        The list of items ordered by customer
     */
    public Order(String orderID, Customer orderingCustomer, Stall orderedStall, DeliveryRunner runnerInCharge,
                 Double tipsForRunner, DiningType diningType, String tableNumber, String noteToVendor, double orderPrice,
                 LocalDateTime orderedDate, OrderStatus orderStatus, Map<Item, Integer> orderItem) {
        this.orderID = orderID;
        this.orderingCustomer = orderingCustomer;
        this.orderedStall = orderedStall;
        this.runnerInCharge = runnerInCharge;
        this.tipsForRunner = tipsForRunner;
        this.diningType = diningType;
        this.tableNumber = tableNumber;
        this.noteToVendor = noteToVendor;
        this.orderPrice = orderPrice;
        this.orderedDate = orderedDate;
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
     * @param order The {@code Order} objectS to be added into list.
     */
    public static void addToOrderList(Order... order) {

        // Throws an error if there is no order passed into the argument, or a null order is passed into argument
        if (order.length == 0 || Arrays.stream(order).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Arguments should contain at least one Order object");
        }

        // Add all the orders from the arguments into the list
        orderList.addAll(
                Arrays.asList(order)
        );
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
     * A method to change the customer attributes of the associated orders to null.
     *
     * @param customerID The ID of the customer
     * @return {@code true} if the operation is successful, else {@code false}
     */
    public static boolean changeCustomerToNull(String customerID) {

        // Reject empty inputs
        if (customerID == null || customerID.isBlank()) return false;

        // Loop through each order
        for (Order order : orderList) {

            // If the customer attribute is not null and the ID matches
            if (order.getOrderingCustomer() != null &&
                    order.getOrderingCustomer().getUserID().equals(customerID)) {

                // Set the customer attribute to null
                order.setOrderingCustomer(null);
            }
        }

        // Write to file
        OrderFileIO.writeFile();

        // Return true after successful operation
        return true;
    }

    /**
     * A method to change the stall attributes of the associated orders to null.
     *
     * @param stallID The ID of the stall
     * @return {@code true} if the operation is successful, else {@code false}
     */
    public static boolean changeStallToNull(String stallID) {

        // Reject empty inputs
        if (stallID == null || stallID.isBlank()) return false;

        // Loop through each order
        for (Order order : orderList) {

            // If the stall attribute is not null and the ID matches
            if (order.getOrderedStall() != null &&
                    order.getOrderedStall().getStallID().equals(stallID)) {

                // Set the stall attribute to null
                order.setOrderedStall(null);
            }
        }

        // Write to file
        OrderFileIO.writeFile();

        // Return true after successful operation
        return true;
    }

    /**
     * A method to change the delivery runner attributes of the associated orders to null.
     *
     * @param runnerID The ID of the delivery runner
     * @return {@code true} if the operation is successful, else {@code false}
     */
    public static boolean changeRunnerToNull(String runnerID) {

        // Reject empty inputs
        if (runnerID.isBlank()) return false;

        // Loop through each order
        for (Order order : orderList) {

            // If the delivery runner attribute is not null and the ID matches
            if (order.getRunnerInCharge() != null &&
                    order.getRunnerInCharge().getUserID().equals(runnerID)) {

                // Set the delivery runner attribute to null
                order.setRunnerInCharge(null);
            }
        }

        // Write to file
        OrderFileIO.writeFile();

        // Return true after successful operation
        return true;
    }

    /**
     * A method to retrieve the overall list of orders that falls within a timeframe.
     *
     * @param filter The timeframe used to filter the order
     * @return The filtered list of orders
     */
    public static ArrayList<Order> filterOrder(Utility.TimeframeFilter filter) {

        // Get the starting and ending time for the filter
        LocalDateTime[] timeRange = Utility.getFilterStartAndEndTime(filter);
        LocalDateTime startTime = timeRange[0];
        LocalDateTime endTime = timeRange[1];

        // Filter the orders based on time
        return getOrderList().stream()
                .filter(order ->
                        !order.getOrderedDate().isBefore(startTime) &&
                                !order.getOrderedDate().isAfter(endTime))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A method to retrieve the relevant vendor orders based on the vendor and the filter
     *
     * @param vendor The vendor object
     * @param filter The timeframe used
     * @return An array list consisting of the filtered list of orders
     */
    public static ArrayList<Order> filterOrder(Vendor vendor, Utility.TimeframeFilter filter) {

        // Check if vendor is null
        if (vendor == null) return null;

        // Get the overall list of orders based on filters
        ArrayList<Order> overallOrder = filterOrder(filter);

        // Get the list of orders associated to vendor
        return overallOrder.stream()
                .filter(order -> order.getOrderedStall().getStallID().equals(vendor.getStall().getStallID()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A method to retrieve the list of orders associated to the runner based on a timeframe.
     *
     * @param runner The runner involved
     * @param filter The timeframe set
     * @return A filtered list of orders
     */
    public static ArrayList<Order> filterOrder(DeliveryRunner runner, Utility.TimeframeFilter filter) {

        // Check if runner is null
        if (runner == null) return null;

        // Get the overall list of orders based on filters
        ArrayList<Order> overallOrder = filterOrder(filter);

        // Get the list of orders associated to runner
        return overallOrder.stream()
                .filter(order -> order.getRunnerInCharge() != null && order.getRunnerInCharge().getUserID().equals(runner.getUserID()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A method to return a filtered order list based on vendor.
     *
     * @param vendor The vendor associated with the order
     * @return A filtered array list based on stall associated with vendor
     */
    public static ArrayList<Order> filterOrder(Vendor vendor) {

        // Return null if the vendor is null
        if (vendor == null) return null;

        // Filter order list based on the associated stall ID
        return getOrderList().stream()
                .filter(order -> order.getOrderedStall() != null && order.getOrderedStall().getStallID().equals(vendor.getStall().getStallID()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A method to filter order list based on the delivery runner.
     *
     * @param runner The delivery runner associated with the order
     * @return A filtered list of orders based on delivery runner
     */
    public static ArrayList<Order> filterOrder(DeliveryRunner runner) {

        // Return null if the input is null
        if (runner == null) return null;

        // Filter order list based on runner
        return getOrderList().stream()
                .filter(order -> order.getRunnerInCharge() != null && order.getRunnerInCharge().getUserID().equals(runner.getUserID()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A method to export the associated date to an Excel file.
     *
     * @param filter The timeframe set to export the Excel file
     * @return {@code true} if the Excel file is created successfully, else {@code false}
     */
    public static boolean exportDataToExcel(Utility.TimeframeFilter filter) {

        // Get the data to be passed into the method to generate Excel file
        ArrayList<Order> dataList = filterOrder(filter);

        // Get the timeframe involved with the filer
        LocalDateTime[] timeframe = Utility.getFilterStartAndEndTime(filter);

        // Return the method that generates the Excel file
        return Utility.downloadAsExcel(orderHeaderList, orderColumnWidth, "Order Records", dataList, timeframe);
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

    public Double getTipsForRunner() {
        return tipsForRunner;
    }

    public void setTipsForRunner(Double tipsForRunner) {
        this.tipsForRunner = tipsForRunner;
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

    public String getNoteToVendor() {
        return noteToVendor;
    }

    public void setNoteToVendor(String noteToVendor) {
        this.noteToVendor = noteToVendor;
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Map<Item, Integer> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(Map<Item, Integer> orderItem) {
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
                "Ordering Customer: " +
                (orderingCustomer == null ? "null" : "\n" + orderingCustomer) + "\n" +
                "Ordered Stall: " + "\n" +
                orderedStall.toString() + "\n" +
                "Runner in Charge: " + (runnerInCharge == null ? "null" : "\n" + runnerInCharge) + "\n" +
                "Tips for Runner: " + tipsForRunner + "\n" +
                "Dining Type: " + diningType.toString() + "\n" +
                "Table Number: " + tableNumber + "\n" +
                "Delivery Note: " + noteToVendor + "\n" +
                "Order Price: " + orderPrice + "\n" +
                "Ordered Date: " + Utility.generateString(orderedDate) + "\n" +
                "Order Status: " + orderStatus.toString() + "\n" +
                "Order Items: " + Utility.generateString(orderItem);
    }

    /**
     * A method to change order status to either "waiting for runner" or "vendor preparing" depending on the order.
     * We have three different cases to consider here:<br>
     * Case 1: Dining type is {@code DINE_IN} or {@code TAKEAWAY} (runner is not involved) - status is {@code WAITING_VENDOR}<br>
     * Case 2: Dining type is {@code DELIVERY} and runner has not accepted order - status is {@code WAITING_VENDOR_AND_RUNNER}<br>
     * Case 3: Dining type is {@code DELIVERY} and runner has accepted order - status is {@code WAITING_VENDOR}
     *
     * @return {@code 1} if order is accepted by vendor and notification is created<br>
     * {@code 0} if order is not accepted and notification is not created<br>
     * {@code -1} if vendor accepts order but notification fails to be created
     */
    public int vendorAcceptOrder() {

        // Retrieve the order's dining type (different dining type has different logic)
        DiningType orderDiningType = this.getDiningType();
        OrderStatus currentStatus = this.getOrderStatus();

        // Case 1 and 3
        if (currentStatus == OrderStatus.WAITING_VENDOR) {

            // Change the order status to "vendor preparing"
            this.setOrderStatus(OrderStatus.VENDOR_PREPARING);

            // Create a notification to alert users - Case 3 (runner is involved)
            if (this.getRunnerInCharge() != null) {
                boolean runnerNotificationStatus = DeliveryRunnerNotification.createNewNotification(
                        "Order " + this.getOrderID() + " Preparing",
                        "The order " + this.getOrderID() + " is now being prepared. Please be ready to pick up at stall " + this.getOrderedStall().getStallID() + ".",
                        this.getRunnerInCharge()
                );
                if (!runnerNotificationStatus) return -1;
            }

            // Create notifications for customers and vendors - Case 1 and 3
            boolean customerNotificationStatus = CustomerNotification.createNewNotification(
                    "Your Order " + this.getOrderID() + " is Preparing",
                    "Your order " + this.getOrderID() + " is now being prepared by our vendor.",
                    this.getOrderingCustomer()
            );
            if (!customerNotificationStatus) return -1;

            boolean vendorNotificationStatus = VendorNotification.createNewNotification(
                    "Order " + this.getOrderID() + " is Preparing",
                    "Order " + this.getOrderID() + " has been updated to preparing.",
                    this.getOrderedStall()
            );
            if (!vendorNotificationStatus) return -1;

            // Write into file
            OrderFileIO.writeFile();

            // Return 1 to indicate success modification
            return 1;
        }

        // Case 2
        if (currentStatus == OrderStatus.WAITING_VENDOR_AND_RUNNER && orderDiningType == DiningType.DELIVERY) {

            // Change the order status to "waiting for runner"
            this.setOrderStatus(OrderStatus.WAITING_RUNNER);

            // Create notification for vendor and customer (no delivery runner coz runner hasn't accepted the order)
            boolean customerNotificationStatus = CustomerNotification.createNewNotification(
                    "Order " + this.getOrderID() + " Accepted by Vendor",
                    "Your order " + this.getOrderID() + " has been accepted by vendor. We're currently assigning a delivery runner to pick it up.",
                    this.getOrderingCustomer()
            );
            if (!customerNotificationStatus) return -1;

            boolean vendorNotificationStatus = VendorNotification.createNewNotification(
                    "Order " + this.getOrderID() + " Accepted - Awaiting Runner", // Change
                    "The order " + this.getOrderID() + " has been accepted. A delivery runner will be assigned shortly to pick it up.",
                    this.getOrderedStall()
            );
            if (!vendorNotificationStatus) return -1;

            // Write into file
            OrderFileIO.writeFile();

            // Return true to indicate success modification
            return 1;
        }

        // False is returned if the method is called wrongly (e.g. if the current order is not in waiting state)
        return 0;
    }

    /**
     * A method for vendor to cancel an order. Few cases to consider:<br>
     * Case 1: Runner is not involved (dine-in, takeaway and delivery - runner hasn't accepted)<br>
     * Case 2: Runner is involved (delivery - runner has accepted)<br>
     *
     * @return {@code 1} if order is cancelled successfully and notification is created<br>
     * {@code 0} if order is cancelled unsuccessfully and notification is not created<br>
     * {@code -1} if vendor cancels order but notification fails to be created
     */
    public int vendorCancelOrder() {

        // Check if the method is called correctly
        if (this.getOrderStatus() != OrderStatus.WAITING_VENDOR_AND_RUNNER && this.getOrderStatus() != OrderStatus.WAITING_VENDOR) {
            return 0;
        }

        // Change the status of order to "cancelled"
        this.setOrderStatus(OrderStatus.CANCELLED);

        // Create a notification to indicate that the order is cancelled - returns -1 if notification fails to create
        // Runner
        if (this.getRunnerInCharge() != null) {
            boolean runnerNotification = DeliveryRunnerNotification.createNewNotification(
                    "Order Cancelled",
                    "The order " + this.getOrderID() + " has been cancelled due to rejection of the vendor.",
                    this.getRunnerInCharge()
            );
            if (!runnerNotification) return -1;
        }

        // Customer
        boolean customerNotification = CustomerNotification.createNewNotification(
                "Order Rejected by Vendor",
                "We're sorry to inform that your order " + this.getOrderID() + " has been rejected by the vendor. Please consider placing a new order.",
                this.getOrderingCustomer()
        );
        if (!customerNotification) return -1;

        // Vendor
        boolean vendorNotification = VendorNotification.createNewNotification(
                "Order Rejected",
                "You have rejected the order " + this.getOrderID() + ".",
                this.getOrderedStall()
        );
        if (!vendorNotification) return -1;

        // Write into file
        OrderFileIO.writeFile();

        // Return 1 for successful operation
        return 1;
    }

    /**
     * A method to change the status of an order that has been accepted
     *
     * @param status The order status to be changed
     * @return {@code 1} for successful change and notification created<br>
     * {@code 0} for unsuccessful change and notification not created<br>
     * {@code -1} for successful change but notification not created
     */
    public int vendorUpdateOrderStatus(OrderStatus status) {

        // Check if the initial order status is valid
        if (this.getOrderStatus() != OrderStatus.VENDOR_PREPARING && this.getOrderStatus() != OrderStatus.READY_FOR_PICK_UP) {
            return 0;
        }

        // Return 0 if both initial and new status are the same
        if (this.getOrderStatus() == status) {
            return 0;
        }

        // Change the status if status are different
        this.setOrderStatus(status);

        // Notification if vendor revert from other types to "preparing"
        if (status == OrderStatus.VENDOR_PREPARING) {

            // Customer notification for revert
            CustomerNotification.createNewNotification(
                    "Order Status Updated to Preparing",
                    "Your order " + this.getOrderID() + " has been reverted to 'Preparing' status by the vendor. We will notify you once it's ready.",
                    this.getOrderingCustomer()
            );

            // Vendor notification for revert
            VendorNotification.createNewNotification(
                    "Order Status Updated to Preparing",
                    "You have reverted the status of order " + this.getOrderID() + " to 'Preparing'. Please update the status again once the preparation is complete.",
                    this.getOrderedStall()
            );

            // Delivery runner notification for revert (if applicable)
            if (this.getRunnerInCharge() != null) {
                DeliveryRunnerNotification.createNewNotification(
                        "Order Status Updated to Preparing",
                        "The status of order " + this.getOrderID() + " has been reverted to 'Preparing' by vendor.",
                        this.getRunnerInCharge()
                );
            }

            // Write into file
            OrderFileIO.writeFile();

            // Return 1 to indicate successful modification
            return 1;

            // Notification if status updated to ready for pickup
        } else if (status == OrderStatus.READY_FOR_PICK_UP) {

            // Vendor notification for all orders
            boolean vendorNotification = VendorNotification.createNewNotification(
                    "Order Marked as Ready",
                    "You have marked the order " + this.getOrderID() + " as 'Ready for Pickup'.",
                    this.getOrderedStall()
            );
            if (!vendorNotification) return -1;

            // Different notification for different orders
            switch (this.getDiningType()) {

                // If user choose dine-in
                case DINE_IN -> {
                    boolean customerNotification = CustomerNotification.createNewNotification(
                            "Your Order is Ready!",
                            "Your order " + this.getOrderID() + " is ready. We will shortly send it to your table " + this.getTableNumber() + ".",
                            this.getOrderingCustomer()
                    );
                    if (!customerNotification) return -1;
                }

                // If the user choose takeaway
                case TAKEAWAY -> {
                    boolean customerNotification = CustomerNotification.createNewNotification(
                            "Your Order is Ready!",
                            "Your order " + this.getOrderID() + " is ready at stall " + this.getOrderedStall() + ". Please collect at your earliest convenience.",
                            this.getOrderingCustomer()
                    );
                    if (!customerNotification) return -1;
                }

                // If the order is to be delivered
                case DELIVERY -> {

                    // Customer notification for delivery
                    boolean customerNotification = CustomerNotification.createNewNotification(
                            "Your Order is Ready!",
                            "Your order " + this.getOrderID() + " is ready. Runner " + this.getRunnerInCharge().getName() + " will pick-up your order shortly.",
                            this.getOrderingCustomer()
                    );
                    if (!customerNotification) return -1;

                    // Runner notification for delivery
                    boolean runnerNotification = DeliveryRunnerNotification.createNewNotification(
                            "Your Order is Ready!",
                            "The order " + this.getOrderID() + " is ready at stall " + this.getOrderedStall() + ". Please proceed to the stall to collect it.",
                            this.getRunnerInCharge()
                    );
                    if (!runnerNotification) return -1;
                }
            }

            // Write into file
            OrderFileIO.writeFile();

            // Return 1 for successful modification
            return 1;

            // When the status is marked as complete (only applicable for dine in and takeaway orders)
        } else if (status == OrderStatus.COMPLETED && (this.getDiningType() == DiningType.DINE_IN || this.getDiningType() == DiningType.TAKEAWAY)) {

            // Customer notification for complete order
            boolean customerNotification = CustomerNotification.createNewNotification(
                    "You have received your order!",
                    "You have successfully received your order " + this.getOrderID() + ". Enjoy your meal!",
                    this.getOrderingCustomer()
            );
            if (!customerNotification) return -1;

            // Vendor notification for delivery
            boolean vendorNotification = VendorNotification.createNewNotification(
                    "Order Received by Customer",
                    "The order " + this.getOrderID() + " is received by customer.",
                    this.getOrderedStall()
            );
            if (!vendorNotification) return -1;

            // Write into file
            OrderFileIO.writeFile();

            // Return 1 for successful modification
            return 1;
        }

        // Return 0 to indicate unsuccessful modification (does not fit preparing, ready for pickup or customer received)
        return 0;
    }

    /**
     * A method for delivery runner to accept an order.
     *
     * @return {@code true} if the delivery runner accepts the order and notification is created successfully, {@code false} otherwise
     */
    public boolean runnerAcceptOrder() {

        // Perform nothing if the order is wrong (not a delivery order)
        if (this.getDiningType() != DiningType.DELIVERY) {
            return false;
        }

        // Change status according to different types of initial status
        switch (this.getOrderStatus()) {

            // When the initial status is waiting for vendor and runner
            case WAITING_VENDOR_AND_RUNNER -> {

                // Create notification
                boolean customerNotification = CustomerNotification.createNewNotification(
                        "Order Accepted by Runner",
                        "Your order " + this.getOrderID() + " is accepted by runner " + this.getRunnerInCharge().getName() + ". Please give us a moment for vendors to accept this order.",
                        this.getOrderingCustomer()
                );
                if (!customerNotification) return false;

                boolean runnerNotification = DeliveryRunnerNotification.createNewNotification(
                        "Order Accepted",
                        "You have accepted the order " + this.getOrderID() + ". Please wait for the confirmation from the vendor side (" + this.getOrderedStall().getStallID() + ").",
                        this.getRunnerInCharge()
                );
                if (!runnerNotification) return false;

                // Change status
                this.setOrderStatus(OrderStatus.WAITING_VENDOR);
            }

            // When initial status is waiting runner
            case WAITING_RUNNER -> {

                // Create notification
                boolean customerNotification = CustomerNotification.createNewNotification(
                        "Order Accepted by Runner",
                        "Your order " + this.getOrderID() + " has been accepted by runner " + this.getRunnerInCharge().getUserID() + ". The vendor will now start to prepare your order.",
                        this.getOrderingCustomer()
                );
                if (!customerNotification) return false;

                boolean vendorNotification = VendorNotification.createNewNotification(
                        "Order Assigned to Runner",
                        "Order " + this.getOrderID() + " has been assigned to runner " + this.getRunnerInCharge().getUserID() + ". You may start preparing the food.",
                        this.getOrderedStall()
                );
                if (!vendorNotification) return false;

                boolean runnerNotification = DeliveryRunnerNotification.createNewNotification(
                        "Order Accepted",
                        "You have accepted the order " + this.getOrderID() + ". Please head to the stall " + this.getOrderedStall().getStallID() + " to pick-up the order.",
                        this.getRunnerInCharge()
                );
                if (!runnerNotification) return false;

                // Change status
                this.setOrderStatus(OrderStatus.VENDOR_PREPARING);
            }

            // Return false for other initial status
            default -> {
                return false;
            }
        }

        // Write to file
        OrderFileIO.writeFile();

        // Return true for successful operation
        return true;
    }

    /**
     * A method for delivery runner to update the status of an order.<br>
     * Note: The status cannot be reverted after choosing it<br>
     * (heading to stall (involves two status: vendor preparing and ready to pick up) -> heading to customer address -> order complete)
     *
     * @param status The new status to be updated
     * @return {@code 1} if status is updated and notification is created<br>
     * {@code 0} if status is not updated and notification is not created<br>
     * {@code -1} if status can be updated but notification fails to be created
     */
    public int runnerUpdateOrderStatus(OrderStatus status) {

        // Only applicable for delivery orders
        if (this.getDiningType() != DiningType.DELIVERY) return 0;

        // If status remains the same, do nothing
        if (this.getOrderStatus() == status) return 0;

        // Change order status
        this.setOrderStatus(status);

        // If status is changed to delivering (on the way to customer address)
        if (status == OrderStatus.RUNNER_DELIVERY) {

            // Customer notification
            boolean customerNotification = CustomerNotification.createNewNotification(
                    "Your Order is On its Way!",
                    "Runner " + this.getRunnerInCharge().getUserID() + " is on his/her way to deliver your order " + this.getOrderID() + ". Get ready to collect your meals in a while!",
                    this.getOrderingCustomer()
            );
            if (!customerNotification) return -1;

            // Vendor notification
            boolean vendorNotification = VendorNotification.createNewNotification(
                    "Order collected by Runner",
                    "The order " + this.getOrderID() + " is picked up by runner " + this.getRunnerInCharge().getUserID() + ".",
                    this.getOrderedStall()
            );
            if (!vendorNotification) return -1;

            // Runner notification
            boolean runnerNotification = DeliveryRunnerNotification.createNewNotification(
                    "Delivery in Progress",
                    "You have picked up order " + this.getOrderID() + ". Please proceed to deliver it to customer in a safely manner.",
                    this.getRunnerInCharge()
            );
            if (!runnerNotification) return -1;

            // Write to files
            OrderFileIO.writeFile();

            // Return 1 for successful modification
            return 1;

            // If status is changed to complete (customer has received order)
        } else if (status == OrderStatus.COMPLETED) {

            // Customer notification
            boolean customerNotification = CustomerNotification.createNewNotification(
                    "Order Delivered!",
                    "Your order " + this.getOrderID() + " has been delivered. Enjoy your meal!",
                    this.getOrderingCustomer()
            );
            if (!customerNotification) return -1;

            // Vendor notification
            boolean vendorNotification = VendorNotification.createNewNotification(
                    "Delivery Completed",
                    "You have successfully delivered the order " + this.getOrderID() + ".",
                    this.getOrderedStall()
            );
            if (!vendorNotification) return -1;

            // Runner notification
            boolean runnerNotification = DeliveryRunnerNotification.createNewNotification(
                    "Order Completed",
                    "Runner " + this.getRunnerInCharge().getUserID() + " has successfully delivered the order " + this.getOrderID() + " to the customer.",
                    this.getRunnerInCharge()
            );
            if (!runnerNotification) return -1;

            // Write to files
            OrderFileIO.writeFile();

            // Return 1 for successful modification
            return 1;
        }

        // Return 0 for other status which are irrelevant
        return 0;
    }

    /**
     * A method for delivery runner to reject an order.
     *
     * @return {@code true} if the order is rejected successfully, otherwise {@code false} if notification is not created successfully
     */
    public boolean runnerRejectOrder() {

        // Check if the correct type of order is implemented by the method
        if (this.getDiningType() != DiningType.DELIVERY) return false;
        if (this.getOrderStatus() != OrderStatus.WAITING_VENDOR_AND_RUNNER && this.getOrderStatus() != OrderStatus.WAITING_RUNNER)
            return false;

        // Mark the current runner as unavailable
        boolean markAvailability = this.getRunnerInCharge().updateAvailability(false);
        if (!markAvailability) return false;

        // Pass the order to another runner
        DeliveryRunner newRunner = DeliveryRunner.getAvailableRunner();

        // If there is another available runner
        if (newRunner != null) {

            // Create notification for the current runner
            boolean currentRunnerNotification = DeliveryRunnerNotification.createNewNotification(
                    "Order Reassigned to Another Runner",
                    "You have declined the order " + this.getOrderID() + ". The order is now passed to another runner.",
                    this.getRunnerInCharge()
            );
            if (!currentRunnerNotification) return false;

            // Assign the order to the runner
            this.setRunnerInCharge(newRunner);

        } else {

            // Customer should choose another dining method if no runner is available. Notifications are created here.
            boolean runnerNotification = DeliveryRunnerNotification.createNewNotification(
                    "Order Declined",
                    "You have declined the order " + this.getOrderID() + ".",
                    this.getRunnerInCharge()
            );
            if (!runnerNotification) return false;

            boolean customerNotification = CustomerNotification.createNewNotification(
                    "Unavailability of Delivery Runners",
                    "We regret to inform that your order " + this.getOrderID() + " cannot be delivered as no runners were available to deliver it. " +
                            "Please change your dining method or cancel your order and try again later.",
                    this.getOrderingCustomer()
            );
            if (!customerNotification) return false;

            // Mark order as pending change
            this.setRunnerInCharge(null);
            this.setOrderStatus(OrderStatus.PENDING_CHANGE);
        }

        // Write into file
        OrderFileIO.writeFile();

        // Return true for successful operation
        return true;
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
        READY_FOR_PICK_UP, RUNNER_DELIVERY, COMPLETED, CANCELLED, PENDING_CHANGE;

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
                case CANCELLED -> "Cancelled";
                case PENDING_CHANGE -> "Pending Change";
            };
        }
    }
}
