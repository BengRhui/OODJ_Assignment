package backend.file_io;

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
    public final static int NUMBER_OF_INFORMATION_IN_FILE = 8;
    public final static int[] SPACING_SIZE = {5, 20, 10, 20, 5, 35, 150, 150};

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
        Order orderAssociated = recordFromFile[2].equalsIgnoreCase("null") ? null : Order.getOrder(recordFromFile[2]);
        LocalDateTime feedbackSubmittedTime = Utility.changeStringToTime(recordFromFile[3]);
        double ratings = Double.parseDouble(recordFromFile[4]);
        String feedbackTitle = recordFromFile[5];
        String feedbackDetails = recordFromFile[6];
        String replyFromManager = recordFromFile[7].equalsIgnoreCase("null") ? null : recordFromFile[7];

        // Create a feedback object and return it
        return new Feedback(
                feedbackID,
                feedbackCategory,
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
            record[2] = feedback.getOrderAssociated() == null ? "null" : feedback.getOrderAssociated().getOrderID();
            record[3] = Utility.generateString(feedback.getFeedbackSubmissionTime());
            record[4] = Double.toString(feedback.getRatings());
            record[5] = feedback.getFeedbackTitle();
            record[6] = feedback.getFeedbackDetails();
            record[7] = feedback.getReplyFromManager();
            informationToFile.add(record);
        }

        // Write the list into text file
        writeListToFile(FEEDBACK_FILE_NAME, informationToFile, SPACING_SIZE);
    }
}
