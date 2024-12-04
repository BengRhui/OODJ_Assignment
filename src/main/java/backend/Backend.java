package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Backend {
    public Backend() {
        System.out.println("Backend can run!");
        readFile();
    }

    public void readFile() {
        try {
            System.out.println("---------------------");
            System.out.println("File content:");
            File test_file = new File("src/main/resources/text_file/test_file.txt");
            Scanner read_object = new Scanner(test_file);
            while (read_object.hasNext()) {
                System.out.println(read_object.nextLine());
            }
            read_object.close();
            System.out.println("---------------------");
            System.out.println("File can run!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }
}
