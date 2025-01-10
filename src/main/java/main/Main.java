package main;

import backend.file_io.FileIO;
import frontend.home_page.LoginPage;

public class Main {

    public static void main(String[] args) {

        // Read all the files before running the system
        FileIO.readAllFIle();

        // This code is used to see if the frontend backend package can run
        new LoginPage().setVisible(true);
    }
}
