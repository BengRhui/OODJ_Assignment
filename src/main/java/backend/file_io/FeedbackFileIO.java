package backend.file_io;

import backend.entity.Customer;
import backend.entity.Feedback;
import backend.entity.Order;
import backend.utility.Utility;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class {@code FeedbackFileIO} contains the methods to read and write feedback file.
 *
 * @author Beng Rhui (TP068495)
 */
public class FeedbackFileIO extends FileIO {

    /**
     * Fixed variables to help in coding.
     */
    public final static String FEEDBACK_FILE_NAME = "feedback.txt";
    public final static int NUMBER_OF_INFORMATION_IN_FILE = 9;
    public final static int[] SPACING_SIZE = {5, 20, 5, 10, 20, 5, 35, 150, 150};

    /**
     * A method to read from feedback file and initialize {@code Feedback} objects.
     */
    public static void readFile() {

        // Reset list before reading files
        Feedback.getFeedbackList().clear();

        // Get information from text file
        ArrayList<String[]> informationFromFile = getListFromFile(FEEDBACK_FILE_NAME);

        // Loop through each information
        for (String[] information : informationFromFile) {

            // Create feedback object based on the information, then add to list
            Feedback feedback = createFeedbackObject(information);
            Feedback.addToFeedbackList(feedback);
        }
    }

    /**
     * A method to create {@code Feedback} objects based on information from text file.
     *
     * @param recordFromFile The string array obtained from text file
     * @return The {@code Feedback} object created based on the information
     */
    public static Feedback createFeedbackObject(String[] recordFromFile) {

        // Retrieve information from the string array
        String feedbackID = recordFromFile[0];
        Feedback.Category feedbackCategory = Feedback.Category.getFromString(recordFromFile[1]);
        Customer customerAssociated = Customer.getCustomer(recordFromFile[2]);
        Order orderAssociated = recordFromFile[3].equalsIgnoreCase("null") ? null : Order.getOrder(recordFromFile[3]);
        LocalDateTime feedbackSubmittedTime = Utility.changeStringToTime(recordFromFile[4]);
        int ratings = Integer.parseInt(recordFromFile[5]);
        String feedbackTitle = recordFromFile[6];
        String feedbackDetails = recordFromFile[7];
        String replyFromManager = recordFromFile[8].equalsIgnoreCase("null") ? null : recordFromFile[8];

        // Create a feedback object and return it
        return new Feedback(
                feedbackID,
                feedbackCategory,
                customerAssociated,
                orderAssociated,
                feedbackSubmittedTime,
                ratings,
                feedbackTitle,
                feedbackDetails,
                replyFromManager
        );
    }

    /**
     * A method to write all {@code Feedback} objects into file.
     */
    public static void writeFile() {

        // Get a list of feedback objects
        ArrayList<Feedback> feedbackList = Feedback.getFeedbackList();

        // Create a list to store the information to be written to file
        ArrayList<String[]> informationToFile = new ArrayList<>();

        // Loop through each feedback object
        for (Feedback feedback : feedbackList) {

            // Retrieve information and add to list
            String[] record = new String[NUMBER_OF_INFORMATION_IN_FILE];
            record[0] = feedback.getFeedbackID();
            record[1] = feedback.getFeedbackCategory().toString();
            record[2] = feedback.getCustomerAssociated() == null ? "null" : feedback.getCustomerAssociated().getUserID();
            record[3] = feedback.getOrderAssociated() == null ? "null" : feedback.getOrderAssociated().getOrderID();
            record[4] = Utility.generateString(feedback.getFeedbackSubmissionTime());
            record[5] = Integer.toString(feedback.getRatings());
            record[6] = feedback.getFeedbackTitle();
            record[7] = feedback.getFeedbackDetails();
            record[8] = feedback.getReplyFromManager();
            informationToFile.add(record);
        }

        // Write the list into text file
        writeListToFile(FEEDBACK_FILE_NAME, informationToFile, SPACING_SIZE);
    }
}
