package backend.utility;

import backend.entity.DeliveryRunner;
import backend.entity.Item;
import backend.entity.Order;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class {@code Utility} includes a couple of methods that brings convenient to coding.
 *
 * @author Beng Rhui (TP068495)
 */
public class Utility {

    /**
     * Static variables to be used in the methods.
     */
    private static LocalDateTime currentTime = LocalDateTime.now();

    /**
     * A setter to set the current time (used in testing).
     *
     * @param time The time that will be set as current time
     */
    public static void setCurrentTime(LocalDateTime time) {
        currentTime = time;
    }

    /**
     * A method to print time in a readable format.
     *
     * @param time Time in {@code LocalDateTime} format
     * @return Time in String format with style of "dd/MM/yyyy HH:mm:ss"
     */
    public static String generateString(LocalDateTime time) {

        // Indicates the format of the time
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Returns the time in String format
        return time.format(timeFormat);
    }

    /**
     * A method to print array-in-array in a readable format.
     *
     * @param array Array-in-array
     * @return Contents of the array-in-array, with each array separated by lines
     */
    public static String generateString(ArrayList<String[]> array) {

        // Create a string builder to store strings
        StringBuilder string = new StringBuilder();

        // Loop through each string array
        for (String[] stringList : array) {

            // Get the string format of the string array and add to list
            string.append(Arrays.toString(stringList));
            string.append("\n");
        }

        // Return the string builder in string format
        return string.toString();
    }

    /**
     * A method to generate string from {@code HashMap} with {@code Item} and {@code Integer} key and value
     *
     * @param map HashMap with {@code Item} object as key and {@code Integer} object as value
     * @return The string representation of the {@code HashMap}
     */
    public static String generateString(HashMap<Item, Integer> map) {

        // Check if there is null item in the map
        if (map.containsKey(null)) return null;

        // Create a string builder to store string
        StringBuilder string = new StringBuilder();

        // Append each pair of item and quantity to the string builder
        map.forEach(
                (item, quantity) ->
                        string.append(item.getItemID())
                                .append(" - ")
                                .append(quantity)
                                .append(", ")
        );

        // Remove the extra ", " at the end of the string builder
        string.delete(string.length() - 2, string.length());

        // Return the string builder
        return string.toString();
    }

    /**
     * A method to convert password from char[] to string
     *
     * @param password The password in char[] format
     * @return The password in string format
     */
    public static String generateString(char[] password) {
        return new String(password);
    }

    /**
     * A method to generate the string representation of {@code HashMap} representing availability of delivery runners.
     *
     * @param map The HashMap consisting of {@code DeliveryRunner} as the key, {@code Boolean} as the value
     * @return The string representation of the HashMap
     */
    public static String generateRunnerString(HashMap<DeliveryRunner, Boolean> map) {

        // Create a string builder to store string
        StringBuilder string = new StringBuilder();

        // Append each pair of item and quantity to the string builder
        map.forEach(
                (runner, status) ->
                        string.append(runner.getUserID())
                                .append(" - ")
                                .append(status)
                                .append(", ")
        );

        // Remove the extra ", " at the end of the string builder
        string.delete(string.length() - 2, string.length());

        // Return the string builder
        return string.toString();
    }

    /**
     * A method to parse string to {@code LocalDateTime} format.
     *
     * @param time Time in string format
     * @return Time in {@code LocalDateTime} format
     */
    public static LocalDateTime changeStringToTime(String time) {

        // Indicates the format of the time
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Return time in LocalDateTime format
        return LocalDateTime.parse(time, timeFormat);
    }

    /**
     * A method to change a string containing the pair of item and quantity into {@code HashMap}.<br>
     * The string is parsed in the format of "I001 - 1, I002 - 2, ..."
     *
     * @param itemSet The string that contains the key-value pair of item and quantity
     * @return The key-value pair in {@code HashMap}
     */
    public static HashMap<Item, Integer> changeStringToHashMap(String itemSet) {

        // Create a new HashMap to store information
        HashMap<Item, Integer> map = new HashMap<>();

        // Split each item pair
        String[] itemPairs = itemSet.split(", ");

        // Loop through each item pair to extract information
        for (String itemPair : itemPairs) {

            // Separate item ID and quantity
            String[] itemAndQuantity = itemPair.split("-");

            // Retrieve item and quantity after removing the trailing whitespaces
            Item item = Item.getItem(itemAndQuantity[0].strip());
            int quantity = Integer.parseInt(itemAndQuantity[1].strip());

            // Add the item and quantity into HashMap
            map.put(item, quantity);
        }

        // Return HashMap after everything is done
        return map;
    }

    /**
     * A method to retrieve picture by providing directory and the name of the picture without considering extension
     *
     * @param directory The directory to be searched
     * @param fileName  The file name of the picture
     * @return The corresponding picture in File object
     */
    public static File retrieveFileWithoutExtension(File[] directory, String fileName) {

        // Return null if directory is empty
        if (directory == null) {
            return null;
        }

        // Loop through the directory
        for (File file : directory) {

            // Continue the loop if the item is not a file
            if (!file.isFile()) {
                continue;
            }

            // Retrieve the file names in the directory
            String fullFileName = file.getName();
            int dotIndex = fullFileName.lastIndexOf(".");
            String trimmedName = fullFileName.substring(0, dotIndex);

            // Return the file if it is found
            if (trimmedName.equalsIgnoreCase(fileName)) return file;
        }

        // Return null if file is not found
        return null;
    }

    /**
     * A method to determine the starting and ending time when filtering records.
     *
     * @param filter The timeframe used to filter records
     * @return A list containing the starting and ending time
     */
    public static LocalDateTime[] getFilterStartAndEndTime(Utility.TimeframeFilter filter) {

        // Define empty variables to hold the starting and ending time based on filter
        LocalDateTime startTime;
        LocalDateTime endTime;

        // Based on current time, calculate the corresponding values
        int year = currentTime.getYear();
        int month = currentTime.getMonthValue();
        int day = currentTime.getDayOfMonth();

        // YearMonth class is used to get the last day of the month (there are different days for different month)
        YearMonth currentMonth = YearMonth.of(year, month);
        int lastDayOfMonth = currentMonth.lengthOfMonth();

        // Get start time and end time based on different filters
        switch (filter) {

            // Today: 00:00:00 of today till 23:59:59 of today
            case TODAY -> {
                startTime = LocalDateTime.of(year, month, day, 0, 0, 0);
                endTime = LocalDateTime.of(year, month, day, 23, 59, 59);
            }

            // Daily: 1st of this month (00:00:00) till last day of this month (23:59:59)
            case DAILY -> {
                startTime = LocalDateTime.of(year, month, 1, 0, 0, 0);
                endTime = LocalDateTime.of(year, month, lastDayOfMonth, 23, 59, 59);
            }

            // Monthly and quarterly: Day 1 of 12 months before this month (00:00:00) till this month (23:59:59)
            case MONTHLY, QUARTERLY -> {

                // Calculate the starting month and year (January 2024 will have a start time of February 2023)
                int startMonth = month + 1;
                int startYear = year - 1;

                // Evaluate the case when the current month is December (Dec 2024 will have a start time of Jan 2024)
                if (startMonth == 13) {
                    startMonth = 1;
                    startYear = year;
                }

                // Get the starting and ending time
                startTime = LocalDateTime.of(startYear, startMonth, 1, 0, 0, 0);
                endTime = LocalDateTime.of(year, month, lastDayOfMonth, 23, 59, 59);
            }

            // Yearly: 1 Jan of 5 years before current year (00:00:00) till 31 Dec of this year (23:59:59)
            case YEARLY -> {
                startTime = LocalDateTime.of(year - 4, 1, 1, 0, 0, 0);
                endTime = LocalDateTime.of(year, 12, 31, 23, 59, 59);
            }

            // If the filters do not match, return null
            default -> throw new IllegalArgumentException("Invalid TimeframeFilter used.");
        }

        // Return the starting and ending time
        return new LocalDateTime[]{startTime, endTime};
    }

    /**
     * A method to generate an Excel file containing order data.
     *
     * @param data      The order data passed into the Excel file
     * @param timeRange The time range obtained from Utility method
     * @return {@code true} if the data is successfully written to Excel, else {@code false}
     */
    public static boolean writeDataToExcel(ArrayList<Order> data, LocalDateTime[] timeRange) {

        // Table headers
        String[] headerList = {
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

        // The width for each column (Note: A character has a length of 256 units in this library)
        int[] columnWidth = {
                256 * 15,
                256 * 20,
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

        try {

            // Create a workbook and worksheet
            Workbook excelFile = new XSSFWorkbook();
            Sheet sheet = excelFile.createSheet("Order Records");

            // Retrieve time range in string format (used to create title later)
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            String startingTime = timeRange[0].format(timeFormat);
            String endingTime = timeRange[1].format(timeFormat);
            String timeStringToDisplay = startingTime.equals(endingTime) ? startingTime : startingTime + " - " + endingTime;

            // Generate file name with extension (the full file name will be used later)
            String titleString = "Order Records (" + timeStringToDisplay + ")";
            String fullFileName = titleString + ".xlsx";

            // Create title row and set height (starts from the 2nd row)
            Row titleRow = sheet.createRow(1);
            titleRow.setHeight((short) 700);            // We have to times 20 with pts to get the height in Java

            // Create a title cell from the title row
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue(titleString);

            // Personalization for title cell - set font style for title
            setCellFormat(excelFile, titleCell, 18, true, false, false);

            // Merge the title row (from column A to L - column 0 to 11)
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, headerList.length));

            // Create a header row and set height
            Row headerRow = sheet.createRow(3);
            headerRow.setHeight((short) 600);

            // Create a loop to write values to the row
            for (int i = 0; i < headerList.length; i++) {

                // Create a cell and write values into it
                Cell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(headerList[i]);

                // Personalize cell
                setCellFormat(excelFile, headerCell, 12, false, true, true);

                // Set the width of the cells
                sheet.setColumnWidth(i, columnWidth[i]);
            }

            // Initialize the row index as 4 (the first row is at row 4)
            int rowIndex = 4;

            // Start looping through the data
            for (Order order : data) {

                // Find the number of items in an order (will be used to merge cells later)
                int numOfItems = order.getOrderItem().size();

                // A variable to record the row index for the current order
                int rowIndexForCurrentOrder = 0;

                // Loop through each pair of key-value
                for (HashMap.Entry<Item, Integer> entry : order.getOrderItem().entrySet()) {

                    // Create row based on row index and set height
                    Row dataRow = sheet.createRow(rowIndex + rowIndexForCurrentOrder);
                    dataRow.setHeight((short) 520);

                    // Loop to write data into each column
                    for (int i = 0; i < headerList.length; i++) {

                        // Create cell for each column
                        Cell dataCell = dataRow.createCell(i);

                        // Write values into each column (for the first row)
                        if (rowIndexForCurrentOrder == 0) {
                            switch (i) {
                                case 0 -> dataCell.setCellValue(order.getOrderID());
                                case 1 -> dataCell.setCellValue(generateString(order.getOrderedDate()));
                                case 2 ->
                                        dataCell.setCellValue(order.getOrderingCustomer() == null ? "-" : order.getOrderingCustomer().getName());
                                case 3 ->
                                        dataCell.setCellValue(order.getOrderingCustomer() == null ? "-" : order.getOrderingCustomer().getContactNumber());
                                case 4 ->
                                        dataCell.setCellValue(order.getOrderedStall() == null ? "-" : order.getOrderedStall().getStallName());
                                case 5 ->
                                        dataCell.setCellValue(order.getRunnerInCharge() == null ? "-" : order.getRunnerInCharge().getName());
                                case 6 ->
                                        dataCell.setCellValue(order.getTableNumber() == null ? "-" : order.getTableNumber());
                                case 10 -> dataCell.setCellValue(order.getOrderPrice());
                                case 11 -> dataCell.setCellValue(order.getOrderStatus().toString());
                                case 12 ->
                                        dataCell.setCellValue(order.getNoteToVendor() == null ? "-" : order.getNoteToVendor());
                            }
                        }

                        // Data for item (for all rows)
                        switch (i) {
                            case 7 -> dataCell.setCellValue(entry.getKey().getItemName());
                            case 8 -> dataCell.setCellValue(entry.getKey().getPrice());
                            case 9 -> dataCell.setCellValue(entry.getValue());
                        }

                        // Customize cell
                        setCellFormat(excelFile, dataCell, 12, false, false, true);
                    }

                    // Done writing current row, continue to the next row of the same order
                    rowIndexForCurrentOrder++;

                }

                // Merge cells (for all columns except the ones related to item)
                for (int i = 0; i < headerList.length; i++) {

                    // Skip the item-related columns
                    if (6 < i && i < 10) continue;

                    // Merge the rows
                    sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex + numOfItems - 1, i, i));
                }

                // Increment row index to proceed to the next order
                rowIndex += numOfItems;
            }

            // Retrieve path to user's downloads folder
            String pathToDownload = Paths.get(System.getProperty("user.home"), "Downloads").toString();

            // Create a file object at the path
            File outputFile = new File(pathToDownload, fullFileName);

            // Export the Excel file to the designated path
            FileOutputStream fileOut = new FileOutputStream(outputFile);
            excelFile.write(fileOut);

            // Close file after finish modifying
            excelFile.close();

        } catch (IOException ex) {

            // Print error and return false if errors occur
            System.out.println("Unable to export data to Excel file.");
            return false;
        }

        // Return true for successful operation
        return true;
    }

    /**
     * A private method that helps to set the format of cells for the method {@code writeDataToExcel()}.
     *
     * @param workbook     The workbook object created (required to customize font and styles)
     * @param cell         The cell to be formatted
     * @param fontSize     The font size to be set
     * @param setUnderline Set to {@code true} if the text has to be underlined
     * @param setGreyFill  Set to {@code true} if the cell has to be filled with grey
     * @param setAllBorder Set to {@code true} if border is required for the cell
     */
    private static void setCellFormat(Workbook workbook, Cell cell, int fontSize, boolean setUnderline, boolean setGreyFill, boolean setAllBorder) {

        // Personalize the cells - Font
        Font cellFont = workbook.createFont();
        cellFont.setBold(true);
        cellFont.setFontHeightInPoints((short) fontSize);
        if (setUnderline) cellFont.setUnderline(Font.U_SINGLE);

        // Apply font, alignment and wrap for the cell
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(cellFont);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);

        // Set fill colour
        if (setGreyFill) {
            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }

        // Set border
        if (setAllBorder) {
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
        }

        // Set the style to the cell
        cell.setCellStyle(cellStyle);
    }

    /**
     * An enum class that is used to filter the time range (especially when generating charts).
     */
    public enum TimeframeFilter {

        /**
         * Fields for time frame filters
         */
        TODAY, DAILY, MONTHLY, QUARTERLY, YEARLY;

        /**
         * A method to return the string representation of the status.
         *
         * @return The string representation of the current status
         */
        @Override
        public String toString() {
            return switch (this) {
                case TODAY -> "Today";
                case DAILY -> "Daily";
                case MONTHLY -> "Monthly";
                case QUARTERLY -> "Quarterly";
                case YEARLY -> "Yearly";
            };
        }
    }
}
