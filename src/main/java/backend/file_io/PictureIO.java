package backend.file_io;

import backend.entity.Item;
import backend.entity.Vendor;
import backend.utility.Utility;

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
    public static String PARENT_PATH_TO_STORE_DIRECTORY = "src/main/resources/asset/store/";
    public static String PARENT_PATH_TO_ITEM_DIRECTORY = "src/main/resources/asset/item/";
    public static String PARENT_PATH_TO_SYSTEM_DIRECTORY = "src/main/resources/asset/system/";

    /**
     * A method to get the empty picture file.
     * @return The empty picture file
     */
    public static File getEmptyPicture() {
        return new File(PARENT_PATH_TO_SYSTEM_DIRECTORY + "empty_picture.jpg");
    }

    /**
     * Setter to set directory to the store folder (used in testing).
     *
     * @param parentPathToStoreDirectory The directory to the store folder
     */
    public static void setParentPathToStoreDirectory(String parentPathToStoreDirectory) {
        PARENT_PATH_TO_STORE_DIRECTORY = parentPathToStoreDirectory;
    }

    /**
     * Setter to set directory to the item folder (used in testing).
     *
     * @param parentPathToItemDirectory The directory to the item folder
     */
    public static void setParentPathToItemDirectory(String parentPathToItemDirectory) {
        PARENT_PATH_TO_ITEM_DIRECTORY = parentPathToItemDirectory;
    }

    /**
     * A method to store vendor background into the directory after uploading it.
     *
     * @param uploadedFile The file uploaded to the system
     * @param vendor       The vendor who uploaded the file
     * @return {@code true} if file is copied to directory successfully<br>
     * {@code false} if file is not copied to directory
     */
    public static boolean uploadVendorBackground(File uploadedFile, Vendor vendor) {

        // Return true if vendor does not provide a background (they can have a choice to do so) - but the current background shall be removed
        if (uploadedFile == null) {

            // Get the directory for the existing background picture
            File[] directory = new File(PARENT_PATH_TO_STORE_DIRECTORY).listFiles();
            String fileName = vendor.getStall().getStallID() + "_background";

            // Get the background file
            File initialBackground = Utility.retrieveFileWithoutExtension(directory, fileName);

            // Delete the background file and return true
            if (initialBackground != null) return initialBackground.delete();
            return true;
        }

        // Get the file extension and generate file name
        String[] initialFileName = uploadedFile.getName().split("\\.");
        String fileExtension = initialFileName[initialFileName.length - 1];
        String newFileName = vendor.getStall().getStallID() + "_background." + fileExtension;

        // Retrieve the file in the directory to be copied to
        String pathToSavePicture = PARENT_PATH_TO_STORE_DIRECTORY + newFileName;
        File targetFileLocation = new File(pathToSavePicture);

        // Pass arguments to upload picture method and return boolean
        return uploadPicture(uploadedFile, targetFileLocation);
    }

    /**
     * A method to upload picture of vendor item into directory.
     *
     * @param uploadedFile The picture file associated with the vendor's item
     * @param item         The item linked to the picture
     * @return A status to indicate whether the picture has been updated successfully
     */
    public static boolean uploadVendorItemPicture(File uploadedFile, Item item) {

        // Return true (since vendors can have the option to upload picture) - but the initial picture has to be removed
        if (uploadedFile == null) {

            // Get the directory
            File[] directory = new File(PARENT_PATH_TO_ITEM_DIRECTORY).listFiles();
            String fileName = item.getStall().getStallID() + "_" + item.getItemID();

            // Retrieve the item picture
            File initialItem = Utility.retrieveFileWithoutExtension(directory, fileName);

            // Remove the picture and return true
            if (initialItem != null) return initialItem.delete();
            return true;
        }

        // Generate file name
        String[] initialFileName = uploadedFile.getName().split("\\.");
        String fileExtension = initialFileName[initialFileName.length - 1];
        String newFileName = item.getStall().getStallID() + "_" + item.getItemID() + "." + fileExtension;

        // Retrieve the file in the directory to be copied to
        String pathToSavePicture = PARENT_PATH_TO_ITEM_DIRECTORY + newFileName;
        File targetFileLocation = new File(pathToSavePicture);

        // Pass arguments to upload picture method and return boolean
        return uploadPicture(uploadedFile, targetFileLocation);
    }

    /**
     * A method to upload pictures to directory
     *
     * @param uploadedFile       The file to be uploaded
     * @param targetFileLocation The place where the file should be stored
     * @return Status whether if the picture is uploaded successfully
     */
    public static boolean uploadPicture(File uploadedFile, File targetFileLocation) {

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

        // Retrieve the relevant picture
        File retrievedPicture = Utility.retrieveFileWithoutExtension(directory, fileName);

        // Return the empty picture if the picture is not found
        if (retrievedPicture == null) return getEmptyPicture();

        // If not null, the picture is returned
        return retrievedPicture;
    }

    /**
     * A method to retrieve pictures related to item.
     *
     * @param item The item which picture should be retrieved
     * @return The picture of the item in File object
     */
    public static File retrieveItemPicture(Item item) {

        // Get picture name without extension
        String pictureName = item.getStall().getStallID() + "_" + item.getItemID();

        // Set directory and retrieve the files in the directory
        File[] directory = new File(PARENT_PATH_TO_ITEM_DIRECTORY).listFiles();
        File itemPicture = Utility.retrieveFileWithoutExtension(directory, pictureName);

        // Get the empty picture if the item picture does not exist
        if (itemPicture == null) return getEmptyPicture();

        // Retrieve the relevant picture
        return itemPicture;
    }
}
