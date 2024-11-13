package src;

import src.backend.BackendTest;
import src.frontend.FrontendTest;

public class Main {
    public static void main(String[] args) {

        // This code is used to see if the frontend backend package can run
        System.out.println("Testing here:");
        new FrontendTest();
        new BackendTest();

    }
}
