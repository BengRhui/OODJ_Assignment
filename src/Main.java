import backend.BackendTest;
import frontend.FrontendTest;

public class Main {
    public static void main(String[] args) {

        // This code is used to see if the frontend backend package can run
        System.out.println("Testing:");
        new FrontendTest();
        new BackendTest();

    }
}
