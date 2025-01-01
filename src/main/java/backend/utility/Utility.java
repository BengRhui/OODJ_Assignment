package backend.utility;

import backend.entity.DeliveryRunner;
import backend.entity.Item;

import java.io.File;
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
     * A enum class that is used to filter the time range (especially when generating charts).
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
