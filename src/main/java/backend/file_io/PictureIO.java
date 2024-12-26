package backend.file_io;

import backend.entity.Item;
import backend.entity.Stall;
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
    public static String PARENT_PATH_TO_STORE_DIRECTORY = "src/main/resources/asset/store/";
    public static String PARENT_PATH_TO_ITEM_DIRECTORY = "src/main/resources/asset/item/";

    /**
     * Setter to set directory to the store folder (used in testing).
     * @param parentPathToStoreDirectory The directory to the store folder
     */
    public static void setParentPathToStoreDirectory(String parentPathToStoreDirectory) {
        PARENT_PATH_TO_STORE_DIRECTORY = parentPathToStoreDirectory;
    }

    /**
     * Setter to set directory to the item folder (used in testing).
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

        // Get the file extension and generate file name
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
        return retrievePictureWithoutExtension(directory, fileName);
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

        // Retrieve the relevant picture
        return retrievePictureWithoutExtension(directory, pictureName);
    }

    /**
     * A method to retrieve picture by providing directory and the name of the picture without considering extension
     *
     * @param directory The directory to be searched
     * @param fileName  The file name of the picture
     * @return The corresponding picture in File object
     */
    public static File retrievePictureWithoutExtension(File[] directory, String fileName) {

        // Return null if directory is empty
        if (directory == null) {
            return null;
        }

        // Loop through the directory
        for (File file : directory) {
            if (file.isFile()) {

                // Retrieve the file names in the directory
                String fullFileName = file.getName();
                int dotIndex = fullFileName.lastIndexOf(".");
                String trimmedName = fullFileName.substring(0, dotIndex);

                // Return the picture if it is found
                if (trimmedName.equalsIgnoreCase(fileName)) return file;
            }
        }

        // Return a default picture if the picture is not found (CHANGE HERE)
        return null;
    }
}
