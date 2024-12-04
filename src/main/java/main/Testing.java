package main;

import backend.Backend;
import frontend.Frontend;

public class Testing {

    public static void main(String[] args) {

        // This code is used to see if the frontend backend package can run
        System.out.println("Testing:");
        new Frontend();
        new Backend();
    }
}
