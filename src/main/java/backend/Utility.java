package backend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {

    public static String printTime(LocalDateTime time) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return time.format(timeFormat);
    }
}
