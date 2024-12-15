package backend.utility;

import backend.entity.Item;

import java.time.LocalDateTime;
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
     * @param map HashMap with {@code Item} object as key and {@code Integer} object as value
     * @return The string representation of the {@code HashMap}
     */
    public static String generateString(HashMap<Item, Integer> map) {

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
     * A method to parse string to {@code LocalDateTime} format.
     * @param time Time in string format
     * @return Time in {@code LocalDateTime} format
     */
    public static LocalDateTime changeStringToTime(String time) {

        // Indicates the format of the time
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Return time in LocalDateTime format
        return LocalDateTime.parse(time, timeFormat);
    }
}
