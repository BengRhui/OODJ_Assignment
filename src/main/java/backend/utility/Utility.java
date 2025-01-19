package backend.utility;

import backend.entity.*;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
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
import java.util.*;

/**
 * Class {@code Utility} includes a couple of methods that brings convenient to coding.
 *
 * @author Beng Rhui (TP068495)
 */
public class Utility {

    /**
     * Static variables to be used in the methods.
     */
    private final static String SYSTEM_PICTURE_PATH = "src/main/resources/asset/system/full_system_logo.png";
    private final static String CONTACT_ME_ICON_PATH = "src/main/resources/asset/system/contact_icon.png";
    private final static String JAPANESE_FONT_PATH = "src/main/resources/asset/font/NotoSansJP.ttf";
    private final static String FOOD_COURT_NAME = "Beng の Best";
    private final static String FOOD_COURT_ADDRESS = "No. 1A, Jalan Teknologi 5, Taman Teknologi Malaysia,\n" +
            "57000 Bukit Jalil, WP Kuala Lumpur.";
    private final static String FOOD_COURT_PHONE_NUMBER = "(+60) 5-345 6789";
    private final static String FOOD_COURT_EMAIL = "bengnobest@gmail.com";
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
     * A method to generate string from {@code Map} with {@code Item} and {@code Integer} key and value
     *
     * @param map Map with {@code Item} object as key and {@code Integer} object as value
     * @return The string representation of the {@code Map}
     */
    public static String generateString(Map<Item, Integer> map) {

        // Check if there is null item in the map
        if (map.containsKey(null)) return null;

        // Create a string builder to store string
        StringBuilder string = new StringBuilder();

        // Append each pair of item and quantity to the string builder
        map.entrySet().stream()                                                         // Convert map to stream
                .sorted(Comparator.comparing(item -> item.getKey().getItemID()))        // Sort the map based on item ID
                .forEach(entry -> string.append(entry.getKey().getItemID())             // Convert each pair to string
                        .append(" - ")
                        .append(entry.getValue())
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
     * A method to print out the availability list (used for testing purposes, maybe will be removed if not used later)
     */
    public static void printAvailabilityList() throws NullPointerException {

        // Loop through the map
        for (Map.Entry<String, Boolean> entry : DeliveryRunner.getAvailabilityList().entrySet()) {

            // Retrieve the runner object
            DeliveryRunner runnerRetrieved = DeliveryRunner.getRunner(entry.getKey());

            // If the runner is null, throw a null pointer exception
            if (runnerRetrieved == null) throw new NullPointerException(entry.getKey());

            // If runner is found, print the runner
            System.out.println(runnerRetrieved.getName() + ": " + entry.getValue());
        }
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
     * A method to change a string containing the pair of item and quantity into {@code Map}.<br>
     * The string is parsed in the format of "I001 - 1, I002 - 2, ..."
     *
     * @param itemSet The string that contains the key-value pair of item and quantity
     * @return The key-value pair in {@code Map}
     */
    public static Map<Item, Integer> changeStringToHashMap(String itemSet) {

        // If input is null, return null
        if (itemSet == null || itemSet.isEmpty()) return null;

        // Create a new Map to store information
        Map<Item, Integer> map = new HashMap<>();

        // Split each item pair
        String[] itemPairs = itemSet.split(", ");

        // Loop through each item pair to extract information
        for (String itemPair : itemPairs) {

            // Separate item ID and quantity
            String[] itemAndQuantity = itemPair.split("-");

            // Retrieve item and quantity after removing the trailing whitespaces
            Item item = Item.getItem(itemAndQuantity[0].strip());
            int quantity = Integer.parseInt(itemAndQuantity[1].strip());

            // Add the item and quantity into Map
            map.put(item, quantity);
        }

        // Return Map after everything is done
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
     * A method to export data into Excel files.
     *
     * @param headerList    The header list for the table in Excel
     * @param columnWidth   The width for each table column
     * @param worksheetName The name of the worksheet
     * @param data          The data to be written to Excel
     * @param timeRange     The time range that the data falls within
     * @param <T>           A generic class to allow method to receive different types of data
     * @return {@code true} if the Excel file is created successfully, else {@code false}
     */
    public static <T> boolean downloadAsExcel(String[] headerList, int[] columnWidth, String worksheetName, ArrayList<T> data, LocalDateTime[] timeRange) {

        // Check the type of data involved
        Object classInvolved = data.getFirst().getClass();

        // Currently we only accept order and feedback data, the rest are rejected
        if (classInvolved != Order.class && classInvolved != Feedback.class) return false;

        // Start creating the Excel file
        try {

            // Create a workbook and worksheet
            Workbook excelFile = new XSSFWorkbook();
            Sheet sheet = excelFile.createSheet(worksheetName);

            // Generate a title to be placed inside the worksheet (will be used as file name too)
            String titleAndFileName = generateTitleForExcel(worksheetName, timeRange);

            // Create title and header rows
            createTitleRow(excelFile, sheet, titleAndFileName, headerList);
            createHeaderRow(excelFile, sheet, headerList, columnWidth);

            // Place the data into the worksheet
            convertDataIntoCell(excelFile, sheet, data, headerList);

            // Save the Excel file to Downloads folder
            exportExcelToDownloads(excelFile, titleAndFileName);

            // Close file after finish modifying
            excelFile.close();

        } catch (IOException ex) {

            // Print error and return false if errors occur
            System.out.println("Unable to export feedback data to Excel file.");
            return false;
        }

        // Return true for successful operation
        return true;
    }

    /**
     * A private method to generate a title string for an Excel file (will also be used as file name), with the format {@code WorksheetName (Date Involved)}.
     *
     * @param worksheetName The name of the worksheet
     * @param timeRange     The time range where the data falls
     * @return A string that represents the generated title
     */
    private static String generateTitleForExcel(String worksheetName, LocalDateTime[] timeRange) {

        // Retrieve starting and ending time
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String startingTime = timeRange[0].format(timeFormat);
        String endingTime = timeRange[1].format(timeFormat);

        // Check if the starting and ending time matches, if yes use only one of them
        String timeStringToDisplay = startingTime.equals(endingTime) ? startingTime : startingTime + " - " + endingTime;

        // Generate the name
        return worksheetName + " (" + timeStringToDisplay + ")";
    }

    /**
     * A private method to create a title row for an Excel file (at row 2, spanning across the header columns)
     *
     * @param workbook   The Excel workbook object created
     * @param sheet      The Excel sheet object created
     * @param title      The title that will be placed
     * @param headerList The header list (to determine the column that the title should merge until)
     */
    private static void createTitleRow(Workbook workbook, Sheet sheet, String title, String[] headerList) {

        // Create title row and set height (starts from the 2nd row)
        Row titleRow = sheet.createRow(1);
        titleRow.setHeight((short) 700);            // We have to times 20 with pts in Excel (e.g. 35 pts = 700 units)

        // Create a title cell from the title row
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(title);

        // Personalization for title cell - set font style for title
        setCellFormat(workbook, titleCell, 18, true, false, false);

        // Merge the title cell with the other cells (up till the last column for header)
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, headerList.length));
    }

    /**
     * A private method to create a header row for tables in Excel.
     *
     * @param workbook    The Excel workbook object created
     * @param sheet       The Excel sheet object created
     * @param headerList  The list of headers to be inputted
     * @param columnWidth The width for each column
     */
    private static void createHeaderRow(Workbook workbook, Sheet sheet, String[] headerList, int[] columnWidth) {

        // Create a header row and set height
        Row headerRow = sheet.createRow(3);
        headerRow.setHeight((short) 600);                   // 30 pts = 600 units

        // Create a loop to write values to the row
        for (int i = 0; i < headerList.length; i++) {

            // Create a cell and write values into it
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(headerList[i]);

            // Personalize cell
            setCellFormat(workbook, headerCell, 12, false, true, true);

            // Set the width of the cells
            sheet.setColumnWidth(i, columnWidth[i]);
        }
    }

    /**
     * A private method to write data into each cell. This method will be complemented with other methods that details the logic for writing each different type of data.
     *
     * @param workbook   The Excel workbook object created
     * @param sheet      The Excel sheet object created
     * @param data       The data to be written into Excel
     * @param headerList The list of headers (will be passed to the complement methods for determining the logic to write data)
     * @param <T>        A generic class that allows different types of data to be passed into the method
     */
    private static <T> void convertDataIntoCell(Workbook workbook, Sheet sheet, ArrayList<T> data, String[] headerList) {

        // Get an object from the data
        T element = data.getFirst();

        // Check if the object is from Order class
        if (element instanceof Order) {

            // Direct casting data to Order type might yield problem, but since we are sure that there will not be a mix of data types so annotation is used
            @SuppressWarnings("unchecked")
            ArrayList<Order> orderList = (ArrayList<Order>) data;

            // Pass the converted list to complement method for writing Order data
            writeOrderAsData(workbook, sheet, headerList, orderList);
        }

        // Check if the object is from Feedback class
        if (element instanceof Feedback) {

            // Case data into Feedback type
            @SuppressWarnings("unchecked")
            ArrayList<Feedback> feedbackList = (ArrayList<Feedback>) data;

            // Pass converted list into complement method for writing Feedback data
            writeFeedbackAsData(workbook, sheet, headerList, feedbackList);
        }
    }

    /**
     * A private method that details the logic to write Order data.
     *
     * @param workbook   The Excel workbook object created
     * @param sheet      The Excel sheet object created
     * @param headerList The header list of the data (used to determine the number of attributes to be written)
     * @param orderList  The list of orders (the data / records to be written)
     */
    private static void writeOrderAsData(Workbook workbook, Sheet sheet, String[] headerList, ArrayList<Order> orderList) {

        // Initialize the row index as 4 (the first row is at row 5 - header at row 4)
        int rowIndex = 4;

        // Start looping through the data
        for (Order order : orderList) {

            // Find the number of items in an order (to determine the number of rows that the order will occupy)
            int numOfItems = order.getOrderItem().size();

            // A variable to record the row index for the current order
            int rowIndexForCurrentOrder = 0;

            // Loop through each pair of key-value of the items involved in an order
            for (Map.Entry<Item, Integer> entry : order.getOrderItem().entrySet()) {

                // Create row based on row index and set height
                Row dataRow = sheet.createRow(rowIndex + rowIndexForCurrentOrder);
                dataRow.setHeight((short) 520);             // 26 pts = 520 units

                // Loop to write data into each column
                for (int colIndex = 0; colIndex < headerList.length; colIndex++) {

                    // Create cell for each column
                    Cell dataCell = dataRow.createCell(colIndex);

                    // Check if the current row is the first row
                    boolean isFirstRow = (rowIndexForCurrentOrder == 0);

                    // Pass the boolean into a method to set values for the cell
                    setCellValues(dataCell, colIndex, order, entry, isFirstRow);

                    // Customize cell
                    setCellFormat(workbook, dataCell, 12, false, false, true);
                }

                // Done writing current row, continue to the next row of the same order
                rowIndexForCurrentOrder++;
            }

            // After the current order is written, merge cells (for all columns except the ones related to item)
            for (int i = 0; i < headerList.length; i++) {

                // Skip the item-related columns (column 8, 9 and 10, minus 1 for index)
                if (6 < i && i < 10) continue;

                // Merge the rows
                sheet.addMergedRegion(
                        new CellRangeAddress(rowIndex, rowIndex + numOfItems - 1, i, i)
                );
            }

            // Increment row index to proceed to the next order
            rowIndex += numOfItems;
        }
    }

    /**
     * A private method to write Feedback data into cells.
     *
     * @param workbook     The Excel workbook object created
     * @param sheet        The Excel sheet object created
     * @param headerList   The header list (used to determine the column indices)
     * @param feedbackList The list of feedback (the data to be written to)
     */
    private static void writeFeedbackAsData(Workbook workbook, Sheet sheet, String[] headerList, ArrayList<Feedback> feedbackList) {

        // Initialize the row index as 4 (start from 5th row)
        int rowIndex = 4;

        // Start looping through the data
        for (Feedback feedback : feedbackList) {

            // Create a row and set height
            Row dataRow = sheet.createRow(rowIndex);
            dataRow.setHeight((short) 520);

            // Start looping through each column
            for (int col = 0; col < headerList.length; col++) {

                // Create a cell
                Cell dataCell = dataRow.createCell(col);

                // Set values for the cell
                setCellValues(dataCell, col, feedback);

                // Customize cell
                setCellFormat(workbook, dataCell, 12, false, false, true);
            }

            // Increment row index after writing each feedback
            rowIndex++;
        }
    }

    /**
     * A private method to set the values of a cell for Order data
     *
     * @param cell       The cell to be written
     * @param column     The column index
     * @param order      The Order item
     * @param entry      The {@code item} attribute in {@code Order} object that contains the item ID and quantity
     * @param isFirstRow Check if the current row is the first row
     */
    public static void setCellValues(Cell cell, int column, Order order, Map.Entry<Item, Integer> entry, boolean isFirstRow) {

        // Write data based on the column index
        switch (column) {

            // For order ID (first row only)
            case 0 -> {
                if (isFirstRow) cell.setCellValue(order.getOrderID());
            }

            // For order date (first row only)
            case 1 -> {
                if (isFirstRow) cell.setCellValue(generateString(order.getOrderedDate()));
            }

            // For customer name (first row only)
            case 2 -> {
                if (isFirstRow)
                    cell.setCellValue(order.getOrderingCustomer() == null ? "-" : order.getOrderingCustomer().getName());
            }

            // For customer contact number (first row only)
            case 3 -> {
                if (isFirstRow)
                    cell.setCellValue(order.getOrderingCustomer() == null ? "-" : order.getOrderingCustomer().getContactNumber());
            }

            // For stall name (first row only)
            case 4 -> {
                if (isFirstRow)
                    cell.setCellValue(order.getOrderedStall() == null ? "-" : order.getOrderedStall().getStallName());
            }

            // For runner name (first row only)
            case 5 -> {
                if (isFirstRow)
                    cell.setCellValue(order.getRunnerInCharge() == null ? "-" : order.getRunnerInCharge().getName());
            }

            // For table number (first row only)
            case 6 -> {
                if (isFirstRow) cell.setCellValue(order.getTableNumber() == null ? "-" : order.getTableNumber());
            }

            // For item name
            case 7 -> cell.setCellValue(entry.getKey().getItemName());

            // For item price
            case 8 -> cell.setCellValue(entry.getKey().getPrice());

            // For item quantity
            case 9 -> cell.setCellValue(entry.getValue());

            // For total order price
            case 10 -> {
                if (isFirstRow) cell.setCellValue(order.getOrderPrice());
            }

            // For order status
            case 11 -> {
                if (isFirstRow) cell.setCellValue(order.getOrderStatus().toString());
            }

            // For customer note to vendor
            case 12 -> {
                if (isFirstRow) cell.setCellValue(order.getNoteToVendor() == null ? "-" : order.getNoteToVendor());
            }
        }
    }

    /**
     * A private method to set values for cells when writing data from {@code Feedback} class.
     *
     * @param cell     The cell to be written
     * @param column   The column index
     * @param feedback The Feedback item
     */
    public static void setCellValues(Cell cell, int column, Feedback feedback) {

        // Write data based on column index
        switch (column) {

            // For feedback ID
            case 0 -> cell.setCellValue(feedback.getFeedbackID());

            // For feedback category
            case 1 -> cell.setCellValue(feedback.getFeedbackCategory().toString());

            // For order ID
            case 2 ->
                    cell.setCellValue(feedback.getOrderAssociated() == null ? "-" : feedback.getOrderAssociated().getOrderID());

            // For customer name
            case 3 ->
                    cell.setCellValue(feedback.getCustomerAssociated() == null ? "-" : feedback.getCustomerAssociated().getName());

            // For customer contact number
            case 4 ->
                    cell.setCellValue(feedback.getCustomerAssociated() == null ? "-" : feedback.getCustomerAssociated().getContactNumber());

            // For stall name
            case 5 ->
                    cell.setCellValue(feedback.getFeedbackCategory() == Feedback.Category.VENDOR && feedback.getOrderAssociated().getOrderedStall() != null ? feedback.getOrderAssociated().getOrderedStall().getStallName() : "-");

            // For runner name
            case 6 ->
                    cell.setCellValue(feedback.getFeedbackCategory() == Feedback.Category.DELIVERY_RUNNER && feedback.getOrderAssociated().getRunnerInCharge() != null ? feedback.getOrderAssociated().getRunnerInCharge().getName() : "-");

            // For feedback submission time
            case 7 -> cell.setCellValue(Utility.generateString(feedback.getFeedbackSubmissionTime()));

            // For ratings
            case 8 -> cell.setCellValue(feedback.getRatings());

            // For feedback title
            case 9 -> cell.setCellValue(feedback.getFeedbackTitle());

            // For feedback details
            case 10 -> cell.setCellValue(feedback.getFeedbackDetails());

            // For manager response
            case 11 -> cell.setCellValue(feedback.getReplyFromManager() == null ? "-" : feedback.getReplyFromManager());
        }
    }

    /**
     * A private method to export Excel file to user's Downloads folder.
     *
     * @param workbook The Excel workbook created
     * @param fileName The name for the Excel file to be saved as
     * @throws IOException Thrown if the file could not been exported successfully
     */
    private static void exportExcelToDownloads(Workbook workbook, String fileName) throws IOException {

        // Get the name of the file with XLSX extension
        String fullFileName = fileName + ".xlsx";

        // Retrieve path to user's downloads folder
        String pathToDownload = getPathToDownloadsFolder();

        // Create a file object at the path
        File outputFile = new File(pathToDownload, fullFileName);

        // Export the Excel file to the designated path
        FileOutputStream fileOut = new FileOutputStream(outputFile);
        workbook.write(fileOut);
    }

    /**
     * A method to generate the file path to downloads folder of a user.
     *
     * @return The path to downloads folder
     */
    public static String getPathToDownloadsFolder() {

        // Retrieve path to user's downloads folder
        return Paths.get(System.getProperty("user.home"), "Downloads").toString();
    }

    /**
     * A private method that helps to set the format of cells for the method {@code writeOrderDataToExcel()}.
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
     * A method to convert map consisting of item ID to the item object.
     *
     * @param map The map with item ID as key, quantity as value
     * @return Map with item object as key, quantity as value
     */
    public static Map<Item, Integer> convertItemMap(Map<String, Integer> map) {

        // Return null if the map is empty
        if (map == null) return null;

        // Create a map to store values to output
        Map<Item, Integer> outputMap = new HashMap<>();

        // Loop through each entry
        for (Map.Entry<String, Integer> entry : map.entrySet()) {

            // Get the item ID and retrieve the associated item
            String itemID = entry.getKey();
            Item item = Item.getItem(itemID);

            // If the retrieved item is null, return null
            if (item == null) return null;

            // Get the quantity of the item
            int quantity = entry.getValue();

            // Put them into the map
            outputMap.put(item, quantity);
        }

        // Return the map
        return outputMap;
    }

    /**
     * A method to return the total price for the cart.
     *
     * @param cart The cart consisting of item and quantity
     * @return The total price
     */
    public static double getTotalAmountForCart(Map<?, Integer> cart) {

        // Declare a variable to store price
        double price = 0;

        // Loop through each item in the cart
        for (Map.Entry<?, Integer> entry : cart.entrySet()) {

            // Get the object
            Object key = entry.getKey();

            // Declare a variable to store item object
            Item item = null;

            // Retrieve item object based on key
            if (key instanceof String) item = Item.getItem((String) key);
            else if (key instanceof Item) item = (Item) key;

            // If the item cannot be retrieved, continue the loop
            if (item == null) continue;

            // Add the calculated price to variable
            price += item.getPrice(entry.getValue());
        }

        // Return the variable as overall price for the cart
        return price;
    }

    /**
     * A method to generate the path for the transaction PDF to user's Downloads folder.
     *
     * @param transaction The transaction item to be converted to PDF
     * @return The path to Downloads folder for the transaction object
     */
    private static String generatePathForPDF(Transaction transaction) {

        // Get path to downloads folder
        String pathToDownloads = getPathToDownloadsFolder() + "/";

        // Generate file name for transaction
        return pathToDownloads + "Transaction_" + transaction.getTransactionID() + ".pdf";
    }

    /**
     * A method to generate a PDF file for a transaction object in general.
     *
     * @param transaction The transaction item involved
     */
    public static boolean generatePDF(Transaction transaction, String[] tableHeaders, int[] columnPosition) {

        // Retrieve the path to downloads folder
        String destinationPath = generatePathForPDF(transaction);

        try {

            // Initialize PDF writer with the path
            PdfWriter writer = new PdfWriter(destinationPath);

            // Create a PDF document
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Add header for PDF (food court logo, name and address)
            createHeaderForPDF(document);

            // Add title for PDF (the main title - Invoice Slip)
            createTitleForPDF(document);

            // Add table headers for PDF
            createTableHeaderForPDF(document, tableHeaders, columnPosition);

            // Add data into the PDF
            createTableDataForPDF(document, transaction, columnPosition);

            // Add total row into PDF
            createTotalRowForPDF(document, transaction.getTransactionAmount());

            // Add footer into PDF
            createFooterForPDF(pdfDoc, document, transaction);

            // Close the document after finish editing
            document.close();

            // Return true for successful operation
            return true;

        } catch (Exception e) {

            // Print error message and return false
            System.out.println("Fail to create PDF file for " + transaction.getTransactionID() + ".");
            return false;
        }
    }

    /**
     * A private method that creates a header for a PDF document
     *
     * @param document The document that will be added with header
     */
    private static void createHeaderForPDF(Document document) throws Exception {

        // Get the font used
        PdfFont font = getJapaneseFont();

        // Add image into PDF
        ImageData imageData = ImageDataFactory.create(SYSTEM_PICTURE_PATH);
        Image image = new Image(imageData);
        image.scaleToFit(100, 100);
        document.add(image);

        // Add name to PDF
        document.add(new Paragraph(FOOD_COURT_NAME)
                .setFont(font)
                .setFontSize(20)
                .setTextRenderingMode(1)
                .setStrokeWidth(1.7f)
                .setFixedPosition(170, 777, 300)
        );

        // Add address to PDF
        document.add(new Paragraph(FOOD_COURT_ADDRESS)
                .setFont(font)
                .setFontSize(12)
                .setFixedPosition(170, 732, 300)
        );
    }

    /**
     * A private method to create a title for the PDF (invoice slip in this case).
     *
     * @param document The PDF document that will be added with title
     */
    private static void createTitleForPDF(Document document) {

        // Create a line object to separate title with header
        LineSeparator line = new LineSeparator(new SolidLine(1));
        line.setWidth(UnitValue.createPercentValue(100));
        line.setMarginTop(10);

        // Add the line
        document.add(line);

        // Add the title
        document.add(new Paragraph("Invoice Slip")
                .setFont(getJapaneseFont())
                .setFontSize(30)
                .setTextRenderingMode(1)
                .setStrokeWidth(2f)
                .setTextAlignment(TextAlignment.CENTER)
        );

        // Add the line again to separate with table header
        document.add(line);
    }

    /**
     * A private method to add table headers into the PDF.
     *
     * @param document       The PDF document that will be added with header
     * @param headerList     The list of headers
     * @param columnPosition The column position of headers (at what place will the header start)
     */
    private static void createTableHeaderForPDF(Document document, String[] headerList, int[] columnPosition) {

        // Loop through each header
        for (int i = 0; i < headerList.length; i++) {

            // Add headers to PDF
            document.add(new Paragraph(headerList[i])
                    .setFont(getJapaneseFont())
                    .setFontSize(16)
                    .setTextRenderingMode(1)
                    .setStrokeWidth(1.5f)
                    .setFixedPosition(columnPosition[i], 590, 100)
            );
        }
    }

    /**
     * A private method to add data into PDF.
     *
     * @param document       The document that will be written with data
     * @param transaction    The transaction object to be written
     * @param columnPosition The column position of data (at what place will the data start)
     */
    private static void createTableDataForPDF(Document document, Transaction transaction, int[] columnPosition) {

        // Add transaction type (cash in / our)
        document.add(new Paragraph(transaction.getTransactionType().toString())
                .setFont(getJapaneseFont())
                .setFontSize(12)
                .setTextRenderingMode(1)
                .setStrokeWidth(1f)
                .setFixedPosition(columnPosition[0], 565, 100)
        );

        // Quantity is null in this case, so "-" is set
        document.add(new Paragraph("-")
                .setFont(getJapaneseFont())
                .setFontSize(12)
                .setTextRenderingMode(1)
                .setStrokeWidth(1f)
                .setTextAlignment(TextAlignment.CENTER)
                .setFixedPosition(columnPosition[1], 565, 60)
        );

        // Add the amount of transaction
        document.add(new Paragraph("RM" + String.format("%.2f", transaction.getTransactionAmount()))
                .setFont(getJapaneseFont())
                .setFontSize(12)
                .setTextRenderingMode(1)
                .setStrokeWidth(1f)
                .setFixedPosition(columnPosition[2], 565, 100)
        );

        // Add the payment method into PDF
        document.add(new Paragraph("Payment Method: " + transaction.getPaymentMethod().toString())
                .setFont(getJapaneseFont())
                .setFontSize(12)
                .setFixedPosition(columnPosition[0], 545, 200)
        );
    }

    /**
     * A private method to generate a line separator (when we need a line at a specific place).
     *
     * @param pdfDoc    The document object that will be written to
     * @param yPosition The y-position of the line
     */
    private static void generateLineSeparator(PdfDocument pdfDoc, int yPosition) {

        // Create a canvas object to draw the line
        PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());

        // Set the margin for the PDF (note that the size of an A4 PDF is 595 pts x 842 pts)
        int margin = 35;

        // Calculate the starting point and ending point in terms of x-coordinate
        float xStart = (float) margin;
        float xEnd = (float) (595 - margin);

        // Draw the line on the canvas
        canvas.moveTo(xStart, yPosition);
        canvas.lineTo(xEnd, yPosition);
        canvas.stroke();
    }

    /**
     * A private method to create a total row inside a PDF.
     *
     * @param document    The document where the total row will be written to
     * @param totalAmount The total amount involved
     */
    private static void createTotalRowForPDF(Document document, double totalAmount) {

        // Add the "Total" text
        document.add(new Paragraph("Total:")
                .setFont(getJapaneseFont())
                .setFontSize(16)
                .setTextRenderingMode(1)
                .setStrokeWidth(1.5f)
                .setFixedPosition(380, 150, 100)
        );

        // Add the total price
        document.add(new Paragraph("RM" + String.format("%.2f", totalAmount))
                .setFont(getJapaneseFont())
                .setFontSize(16)
                .setTextRenderingMode(1)
                .setStrokeWidth(1.5f)
                .setFixedPosition(450, 150, 100)
        );
    }

    /**
     * A private method to create a footer for a PDF (includes contact details and ID)
     *
     * @param pdfDoc      The PDF document object
     * @param document    THe document that the footer will be written to
     * @param transaction The transaction object that will be involved (to retrieve ID)
     * @throws Exception If the icon for "Contact Us" could not be found, an exception will be thrown
     */
    private static void createFooterForPDF(PdfDocument pdfDoc, Document document, Transaction transaction) throws Exception {

        // Draw a line to separate data with footer
        generateLineSeparator(pdfDoc, 135);

        // Get the font
        PdfFont font = getJapaneseFont();

        // Add the word "contact us"
        document.add(new Paragraph("Contact Us")
                .setFont(font)
                .setFontSize(14)
                .setTextRenderingMode(1)
                .setStrokeWidth(1.3f)
                .setFixedPosition(40, 90, 100)
        );

        // Add the image into the PDF
        ImageData contactIconData = ImageDataFactory.create(CONTACT_ME_ICON_PATH);
        Image contactIcon = new Image(contactIconData);
        contactIcon.scaleToFit(50, 50);
        document.add(contactIcon.setFixedPosition(40, 35));

        // Add telephone number into PDF
        document.add(new Paragraph("Tel No: " + FOOD_COURT_PHONE_NUMBER)
                .setFont(font)
                .setFontSize(12)
                .setFixedPosition(95, 60, 200)
        );

        // Add email into PDF
        document.add(new Paragraph("Email: " + FOOD_COURT_EMAIL)
                .setFont(font)
                .setFontSize(12)
                .setFixedPosition(95, 39, 200)
        );

        // Add ID into PDF
        document.add(new Paragraph(transaction.getTransactionID())
                .setFont(font)
                .setFontSize(14)
                .setTextRenderingMode(1)
                .setStrokeWidth(1.3f)
                .setTextAlignment(TextAlignment.RIGHT)
                .setFixedPosition(455, 39, 100)
        );
    }

    /**
     * A method to retrieve the Japanese font used (coz of the character "の")
     *
     * @return The font object
     */
    public static PdfFont getJapaneseFont() {

        // Declare a variable to store the font object
        PdfFont japaneseFont = null;

        try {

            // Retrieve the font from file
            japaneseFont = PdfFontFactory.createFont(JAPANESE_FONT_PATH, PdfEncodings.IDENTITY_H, PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);

        } catch (IOException ex) {

            // Print error message out if the font file is not found
            System.out.println("Cannot find file: " + JAPANESE_FONT_PATH);
        }

        // Return the font
        return japaneseFont;
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
