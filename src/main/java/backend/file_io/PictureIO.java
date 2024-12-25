package backend.file_io;

import backend.entity.Vendor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Class {@code PictureIO} contains the methods to deal with image-related IO.
 *
 * @author Beng Rhui (TP068495)
 */
public class PictureIO {

    /**
     * A final variable that stores the path to the directory to store pictures
     */
    public final static String PARENT_PATH_TO_STORE_DIRECTORY = "src/main/resources/asset/store/";

    /**
     * A method to store vendor background into the directory after uploading it.
     *
     * @param uploadedFile The file uploaded to the system
     * @param vendor       The vendor who uploaded the file
     * @return {@code true} if file is copied to directory successfully<br>
     * {@code false} if file is not copied to directory
     */
    public static boolean uploadVendorBackground(File uploadedFile, Vendor vendor) {

        // Get the file extension and generate file name
        String[] initialFileName = uploadedFile.getName().split("\\.");
        String fileExtension = initialFileName[initialFileName.length - 1];
        String newFileName = vendor.getStall().getStallID() + "_background." + fileExtension;

        // Retrieve the file in the directory to be copied to
        String pathToSavePicture = PARENT_PATH_TO_STORE_DIRECTORY + newFileName;
        File targetFileLocation = new File(pathToSavePicture);

        try {

            // Copy file from the uploaded path to the desired path
            Files.copy(uploadedFile.toPath(), targetFileLocation.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {

            // Print out error message and return false for unsuccessful copy
            System.out.println("Error while copying file " + uploadedFile.getName() + " to path.");
            return false;
        }

        // Return true if the copy operation is executed successfully
        return true;
    }

    /**
     * A method to retrieve the background picture of a stall from the directory
     *
     * @param vendor The vendor associated with the background picture
     * @return The background picture of the stall
     */
    public static File retrieveBackgroundPicture(Vendor vendor) {

        // Retrieve the name of the file to be extracted
        String fileName = vendor.getStall().getStallID() + "_background";

        // Get the list of files in the directory
        File[] directory = new File(PARENT_PATH_TO_STORE_DIRECTORY).listFiles();

        // Return null if the directory is not valid
        if (directory == null) {
            return null;
        }

        // Loop through the list of files in directory
        for (File file : directory) {
            if (file.isFile()) {

                // Retrieve the file name from the directory and remove the extension
                String fileNameInDirectory = file.getName();
                int dotIndex = fileNameInDirectory.lastIndexOf(".");
                String nameInDirectory = fileNameInDirectory.substring(0, dotIndex);

                // Return the file if the file name matches
                if (fileName.equalsIgnoreCase(nameInDirectory)) return file;
            }
        }

        // Return null if there's no file retrieved
        return null;
    }
}
