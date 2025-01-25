import backend.entity.*;
import backend.file_io.OrderFileIO;
import backend.notification.CustomerNotification;
import backend.notification.Notification;
import backend.notification.VendorNotification;
import backend.utility.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class {@code ManagerTest} focuses on the operations that involves the manager.
 *
 * @author Beng Rhui (TP068495)
 */
public class ManagerTest extends BaseTest {

    /**
     * This setup is exclusive for {@code ManagerTest}, where more orders are inputted to check if the test can retrieve summary data.
     */
    @BeforeEach
    void setUp() {

        // New entities for testing purposes
        Stall stall2 = new Stall("S002", "Clock Diner", new Stall.StallCategories[]{Stall.StallCategories.JAPANESE, Stall.StallCategories.DESSERT});
        Vendor vendor2 = new Vendor("V002", "blade@hunters.abc", "bLaDe@123", "Mr Blade", stall2);
        Customer customer2 = new Customer("C002", "steele@trailblaze.com", "Steele@R0bin", "Steele the Trailblazer", "013-5681646", address1, 48.95, "No note needed for me");
        DeliveryRunner runner2 = new DeliveryRunner("R002", "cyclone@mail.com", "Cyc@345", "Cyclone Crane", "014-9561947");

        // Add these entities into lists
        Stall.addStallToList(stall2);
        Vendor.addToVendorList(vendor2);
        Customer.addToCustomerList(customer2);
        DeliveryRunner.addToRunnerList(runner2);

        // The order list to be written to file
        String orderList = """
                ORD00001  ; C001 ; S002 ; R002 ; 5.0      ; Dine In        ; T045 ; Please call upon arrival.                                   ; 128.06    ; 02/01/2025 23:04:14 ; Waiting for Delivery Confirmation                 ; I002 - 5, I001 - 3                                                                                                                                                                                     \s
                ORD00002  ; C002 ; S001 ; R002 ; 5.0      ; Delivery       ; T042 ; Need cutlery included.                                      ; 81.47     ; 02/01/2025 06:57:54 ; Completed                                         ; I002 - 4, I001 - 2                                                                                                                                                                                     \s
                ORD00003  ; C002 ; S002 ; R002 ; 5.0      ; Dine In        ; T010 ; Please call upon arrival.                                   ; 101.44    ; 02/01/2025 17:07:09 ; Completed                                         ; I002 - 2, I001 - 2                                                                                                                                                                                     \s
                ORD00004  ; C002 ; S001 ; R001 ; 5.0      ; Takeaway       ; T001 ; Please call upon arrival.                                   ; 78.62     ; 02/01/2025 22:54:36 ; Waiting for Delivery Confirmation                 ; I002 - 1, I001 - 2                                                                                                                                                                                     \s
                ORD00005  ; C001 ; S001 ; R001 ; 5.0      ; Delivery       ; T021 ; Table near the window preferred.                            ; 22.86     ; 02/01/2025 06:46:19 ; Waiting for Delivery Confirmation                 ; I002 - 4, I001 - 1                                                                                                                                                                                     \s
                ORD00006  ; C002 ; S001 ; R001 ; 5.0      ; Delivery       ; T001 ; Table near the window preferred.                            ; 90.11     ; 02/01/2025 15:33:16 ; Ready for Pick Up                                 ; I002 - 4, I001 - 3                                                                                                                                                                                     \s
                ORD00007  ; C001 ; S002 ; R002 ; 5.0      ; Takeaway       ; T045 ; Extra napkins, please.                                      ; 81.8      ; 02/01/2025 04:22:55 ; Completed                                         ; I002 - 1, I001 - 2                                                                                                                                                                                     \s
                ORD00008  ; C002 ; S002 ; R001 ; 5.0      ; Delivery       ; T004 ; No special requests.                                        ; 64.11     ; 02/01/2025 08:44:24 ; Vendor Preparing                                  ; I002 - 2, I001 - 2                                                                                                                                                                                     \s
                ORD00009  ; C001 ; S002 ; R001 ; 5.0      ; Delivery       ; T033 ; Extra spicy, if possible.                                   ; 99.86     ; 02/01/2025 16:15:07 ; Waiting for Vendor Confirmation                   ; I002 - 5, I001 - 1                                                                                                                                                                                     \s
                ORD00010  ; C001 ; S001 ; R002 ; 5.0      ; Dine In        ; T021 ; No special requests.                                        ; 52.02     ; 02/01/2025 01:38:00 ; Waiting for Delivery Confirmation                 ; I002 - 4, I001 - 2                                                                                                                                                                                     \s
                ORD00011  ; C001 ; S001 ; R002 ; 5.0      ; Delivery       ; T006 ; Please call upon arrival.                                   ; 133.22    ; 02/01/2025 15:41:02 ; Vendor Preparing                                  ; I002 - 3, I001 - 3                                                                                                                                                                                     \s
                ORD00012  ; C001 ; S002 ; R001 ; 5.0      ; Dine In        ; T027 ; Please call upon arrival.                                   ; 39.59     ; 02/01/2025 12:56:02 ; Vendor Preparing                                  ; I002 - 1, I001 - 1                                                                                                                                                                                     \s
                ORD00013  ; C001 ; S002 ; R001 ; 5.0      ; Delivery       ; T031 ; Allergic to peanuts, please take note.                      ; 149.11    ; 02/01/2025 17:37:52 ; Vendor Preparing                                  ; I002 - 3, I001 - 2                                                                                                                                                                                     \s
                ORD00014  ; C001 ; S001 ; R001 ; 5.0      ; Takeaway       ; T013 ; Quiet table preferred.                                      ; 49.84     ; 02/01/2025 22:35:58 ; Vendor Preparing                                  ; I002 - 3, I001 - 3                                                                                                                                                                                     \s
                ORD00015  ; C002 ; S002 ; R001 ; 5.0      ; Delivery       ; T006 ; Quiet table preferred.                                      ; 46.12     ; 02/01/2025 16:38:19 ; Waiting for Delivery Confirmation                 ; I002 - 4, I001 - 3                                                                                                                                                                                     \s
                ORD00016  ; C002 ; S002 ; R002 ; 5.0      ; Dine In        ; T046 ; Need cutlery included.                                      ; 59.57     ; 01/01/2025 22:33:22 ; Vendor Preparing                                  ; I002 - 2, I001 - 3                                                                                                                                                                                     \s
                ORD00017  ; C002 ; S001 ; R001 ; 5.0      ; Delivery       ; T022 ; Would like a corner table.                                  ; 34.23     ; 05/03/2024 10:10:16 ; Pending Change                                    ; I002 - 4, I001 - 1                                                                                                                                                                                     \s
                ORD00018  ; C001 ; S002 ; R001 ; 5.0      ; Dine In        ; T042 ; No onions in the food, please.                              ; 107.71    ; 13/02/2024 16:27:25 ; Completed                                         ; I002 - 2, I001 - 3                                                                                                                                                                                     \s
                ORD00019  ; C002 ; S002 ; R001 ; 5.0      ; Dine In        ; T048 ; High chair needed.                                          ; 118.68    ; 02/04/2024 10:12:45 ; Completed                                         ; I002 - 2, I001 - 3                                                                                                                                                                                     \s
                ORD00020  ; C001 ; S001 ; R002 ; 5.0      ; Delivery       ; T016 ; No onions in the food, please.                              ; 74.35     ; 14/12/2024 01:13:21 ; Waiting for Vendor and Delivery Confirmation      ; I002 - 3, I001 - 3                                                                                                                                                                                     \s
                ORD00021  ; C001 ; S002 ; R001 ; 5.0      ; Delivery       ; T030 ; Allergic to peanuts, please take note.                      ; 137.28    ; 21/07/2024 23:21:35 ; Waiting for Delivery Confirmation                 ; I002 - 1, I001 - 1                                                                                                                                                                                     \s
                ORD00022  ; C002 ; S001 ; R001 ; 5.0      ; Delivery       ; T040 ; Would like a corner table.                                  ; 86.14     ; 02/04/2024 17:25:40 ; Waiting for Vendor Confirmation                   ; I002 - 1, I001 - 1                                                                                                                                                                                     \s
                ORD00023  ; C001 ; S002 ; R001 ; 5.0      ; Takeaway       ; T010 ; No onions in the food, please.                              ; 70.38     ; 28/05/2024 22:52:42 ; Pending Change                                    ; I002 - 1, I001 - 3                                                                                                                                                                                     \s
                ORD00024  ; C002 ; S001 ; R002 ; 5.0      ; Takeaway       ; T034 ; Allergic to peanuts, please take note.                      ; 116.95    ; 07/05/2024 17:30:30 ; Delivering by Runner                              ; I002 - 3, I001 - 3                                                                                                                                                                                     \s
                ORD00025  ; C002 ; S001 ; R001 ; 5.0      ; Dine In        ; T019 ; Table near the window preferred.                            ; 21.4      ; 06/05/2024 23:51:26 ; Waiting for Vendor Confirmation                   ; I002 - 3, I001 - 3                                                                                                                                                                                     \s
                ORD00026  ; C001 ; S002 ; R001 ; 5.0      ; Dine In        ; T008 ; Quiet table preferred.                                      ; 86.67     ; 27/08/2024 07:13:17 ; Waiting for Delivery Confirmation                 ; I002 - 5, I001 - 2                                                                                                                                                                                     \s
                ORD00027  ; C002 ; S002 ; R001 ; 5.0      ; Takeaway       ; T047 ; No special requests.                                        ; 24.62     ; 28/08/2024 09:39:21 ; Delivering by Runner                              ; I002 - 5, I001 - 2                                                                                                                                                                                     \s
                ORD00028  ; C001 ; S002 ; R001 ; 5.0      ; Takeaway       ; T030 ; Quiet table preferred.                                      ; 128.27    ; 11/09/2024 17:30:59 ; Completed                                         ; I002 - 5, I001 - 3                                                                                                                                                                                     \s
                ORD00029  ; C002 ; S001 ; R002 ; 5.0      ; Delivery       ; T008 ; Extra spicy, if possible.                                   ; 130.08    ; 15/04/2024 20:08:44 ; Cancelled                                         ; I002 - 4, I001 - 1                                                                                                                                                                                     \s
                ORD00030  ; C002 ; S002 ; R001 ; 5.0      ; Takeaway       ; T029 ; Allergic to peanuts, please take note.                      ; 94.93     ; 08/02/2024 00:51:05 ; Completed                                         ; I002 - 5, I001 - 2                                                                                                                                                                                     \s
                ORD00031  ; C002 ; S002 ; R002 ; 5.0      ; Takeaway       ; T031 ; No onions in the food, please.                              ; 107.54    ; 06/09/2024 05:02:23 ; Completed                                         ; I002 - 1, I001 - 2                                                                                                                                                                                     \s
                ORD00032  ; C001 ; S002 ; R002 ; 5.0      ; Dine In        ; T013 ; Would like a corner table.                                  ; 141.31    ; 24/05/2024 05:50:28 ; Waiting for Vendor Confirmation                   ; I002 - 1, I001 - 1                                                                                                                                                                                     \s
                ORD00033  ; C002 ; S002 ; R001 ; 5.0      ; Delivery       ; T013 ; High chair needed.                                          ; 36.73     ; 02/03/2024 10:05:44 ; Pending Change                                    ; I002 - 1, I001 - 2                                                                                                                                                                                     \s
                ORD00034  ; C002 ; S001 ; R002 ; 5.0      ; Delivery       ; T023 ; Please call upon arrival.                                   ; 36.95     ; 24/02/2024 21:22:10 ; Waiting for Vendor Confirmation                   ; I002 - 4, I001 - 1                                                                                                                                                                                     \s
                ORD00035  ; C002 ; S001 ; R001 ; 5.0      ; Delivery       ; T041 ; No special requests.                                        ; 126.5     ; 05/06/2024 08:35:24 ; Waiting for Vendor and Delivery Confirmation      ; I002 - 3, I001 - 2                                                                                                                                                                                     \s
                ORD00036  ; C001 ; S002 ; R001 ; 5.0      ; Dine In        ; T026 ; Need cutlery included.                                      ; 89.69     ; 19/11/2021 12:14:33 ; Cancelled                                         ; I002 - 4, I001 - 1                                                                                                                                                                                     \s
                ORD00037  ; C002 ; S002 ; R001 ; 5.0      ; Dine In        ; T027 ; Allergic to peanuts, please take note.                      ; 132.27    ; 26/07/2021 22:26:11 ; Vendor Preparing                                  ; I002 - 5, I001 - 1                                                                                                                                                                                     \s
                ORD00038  ; C002 ; S002 ; R001 ; 5.0      ; Delivery       ; T048 ; Please call upon arrival.                                   ; 42.31     ; 06/01/2024 16:02:20 ; Ready for Pick Up                                 ; I002 - 1, I001 - 3                                                                                                                                                                                     \s
                ORD00039  ; C001 ; S001 ; R002 ; 5.0      ; Delivery       ; T013 ; Quiet table preferred.                                      ; 128.71    ; 22/03/2021 02:35:00 ; Completed                                         ; I002 - 1, I001 - 1                                                                                                                                                                                     \s
                ORD00040  ; C001 ; S001 ; R001 ; 5.0      ; Dine In        ; T008 ; No onions in the food, please.                              ; 26.23     ; 20/08/2021 14:35:44 ; Pending Change                                    ; I002 - 4, I001 - 1                                                                                                                                                                                     \s
                ORD00041  ; C001 ; S002 ; R002 ; 5.0      ; Delivery       ; T023 ; Allergic to peanuts, please take note.                      ; 90.44     ; 31/03/2024 15:23:06 ; Completed                                         ; I002 - 1, I001 - 1                                                                                                                                                                                     \s
                ORD00042  ; C001 ; S001 ; R002 ; 5.0      ; Dine In        ; T029 ; Need cutlery included.                                      ; 58.36     ; 21/10/2021 13:08:02 ; Waiting for Delivery Confirmation                 ; I002 - 3, I001 - 3                                                                                                                                                                                     \s
                ORD00043  ; C002 ; S001 ; R002 ; 5.0      ; Takeaway       ; T002 ; Allergic to peanuts, please take note.                      ; 50.69     ; 05/07/2024 13:50:18 ; Ready for Pick Up                                 ; I002 - 2, I001 - 2                                                                                                                                                                                     \s
                ORD00044  ; C001 ; S001 ; R001 ; 5.0      ; Delivery       ; T009 ; No onions in the food, please.                              ; 49.24     ; 05/11/2024 11:49:09 ; Cancelled                                         ; I002 - 1, I001 - 3                                                                                                                                                                                     \s
                ORD00045  ; C002 ; S002 ; R002 ; 5.0      ; Dine In        ; T031 ; Quiet table preferred.                                      ; 35.58     ; 05/01/2021 13:12:01 ; Completed                                         ; I002 - 1, I001 - 3                                                                                                                                                                                     \s
                ORD00046  ; C002 ; S001 ; R002 ; 5.0      ; Delivery       ; T045 ; Please call upon arrival.                                   ; 78.09     ; 26/07/2024 20:10:34 ; Completed                                         ; I002 - 4, I001 - 1                                                                                                                                                                                     \s
                ORD00047  ; C002 ; S001 ; R001 ; 5.0      ; Delivery       ; T039 ; No special requests.                                        ; 83.23     ; 20/12/2023 07:14:54 ; Waiting for Vendor and Delivery Confirmation      ; I002 - 5, I001 - 1                                                                                                                                                                                     \s
                ORD00048  ; C001 ; S002 ; R001 ; 5.0      ; Delivery       ; T021 ; Extra spicy, if possible.                                   ; 111.81    ; 08/12/2022 10:39:56 ; Waiting for Vendor Confirmation                   ; I002 - 3, I001 - 2                                                                                                                                                                                     \s
                ORD00049  ; C001 ; S001 ; R002 ; 5.0      ; Delivery       ; T035 ; Need cutlery included.                                      ; 147.47    ; 06/12/2023 08:39:19 ; Waiting for Vendor and Delivery Confirmation      ; I002 - 2, I001 - 2                                                                                                                                                                                     \s
                ORD00050  ; C002 ; S002 ; R002 ; 5.0      ; Takeaway       ; T029 ; Quiet table preferred.                                      ; 110.98    ; 23/08/2024 19:28:00 ; Vendor Preparing                                  ; I002 - 4, I001 - 3                                                                                                                                                                                     \s
                ORD00051  ; C001 ; S001 ; R001 ; 5.0      ; Dine In        ; T049 ; Extra spicy, if possible.                                   ; 106.13    ; 17/07/2021 19:05:38 ; Completed                                         ; I002 - 5, I001 - 1                                                                                                                                                                                     \s
                ORD00052  ; C002 ; S002 ; R002 ; 5.0      ; Dine In        ; T044 ; Quiet table preferred.                                      ; 43.36     ; 15/11/2024 12:30:10 ; Waiting for Delivery Confirmation                 ; I002 - 4, I001 - 3                                                                                                                                                                                     \s
                ORD00053  ; C001 ; S002 ; R001 ; 5.0      ; Takeaway       ; T017 ; Would like a corner table.                                  ; 58.43     ; 31/07/2023 01:37:12 ; Pending Change                                    ; I002 - 3, I001 - 3                                                                                                                                                                                     \s
                ORD00054  ; C001 ; S002 ; R002 ; 5.0      ; Dine In        ; T035 ; Need cutlery included.                                      ; 86.63     ; 12/01/2021 22:26:50 ; Waiting for Vendor Confirmation                   ; I002 - 3, I001 - 1                                                                                                                                                                                     \s
                ORD00055  ; C002 ; S001 ; R002 ; 5.0      ; Delivery       ; T046 ; Please call upon arrival.                                   ; 139.06    ; 05/03/2024 08:26:06 ; Waiting for Vendor and Delivery Confirmation      ; I002 - 5, I001 - 2                                                                                                                                                                                     \s
                ORD00056  ; C002 ; S001 ; R001 ; 5.0      ; Dine In        ; T026 ; Extra napkins, please.                                      ; 82.81     ; 19/05/2024 06:44:29 ; Vendor Preparing                                  ; I002 - 2, I001 - 1                                                                                                                                                                                     \s
                ORD00057  ; C002 ; S002 ; R002 ; 5.0      ; Takeaway       ; T029 ; Would like a corner table.                                  ; 103.64    ; 15/02/2023 04:05:52 ; Waiting for Vendor Confirmation                   ; I002 - 3, I001 - 1                                                                                                                                                                                     \s
                ORD00058  ; C001 ; S001 ; R002 ; 5.0      ; Takeaway       ; T027 ; Would like a corner table.                                  ; 99.81     ; 26/07/2024 02:14:27 ; Cancelled                                         ; I002 - 4, I001 - 2                                                                                                                                                                                     \s
                ORD00059  ; C002 ; S001 ; R002 ; 5.0      ; Takeaway       ; T008 ; Extra napkins, please.                                      ; 73.65     ; 12/04/2024 10:25:00 ; Vendor Preparing                                  ; I002 - 5, I001 - 1                                                                                                                                                                                     \s
                ORD00060  ; C001 ; S002 ; R002 ; 5.0      ; Dine In        ; T037 ; Extra spicy, if possible.                                   ; 148.88    ; 24/04/2022 05:03:56 ; Waiting for Vendor and Delivery Confirmation      ; I002 - 2, I001 - 1                                                                                                                                                                                     \s
                ORD00061  ; C001 ; S001 ; R001 ; 5.0      ; Dine In        ; T038 ; Table near the window preferred.                            ; 36.59     ; 05/10/2023 10:23:50 ; Waiting for Vendor Confirmation                   ; I002 - 1, I001 - 2                                                                                                                                                                                     \s
                ORD00062  ; C001 ; S001 ; R001 ; 5.0      ; Delivery       ; T032 ; No special requests.                                        ; 40.35     ; 28/06/2024 08:14:11 ; Cancelled                                         ; I002 - 5, I001 - 1                                                                                                                                                                                     \s
                ORD00063  ; C001 ; S002 ; R001 ; 5.0      ; Takeaway       ; T041 ; Table near the window preferred.                            ; 74.55     ; 07/06/2022 11:14:58 ; Waiting for Delivery Confirmation                 ; I002 - 5, I001 - 2                                                                                                                                                                                     \s
                ORD00064  ; C002 ; S002 ; R001 ; 5.0      ; Dine In        ; T004 ; Allergic to peanuts, please take note.                      ; 51.82     ; 01/02/2021 21:21:50 ; Waiting for Delivery Confirmation                 ; I002 - 3, I001 - 1                                                                                                                                                                                     \s
                ORD00065  ; C002 ; S001 ; R001 ; 5.0      ; Takeaway       ; T041 ; Table near the window preferred.                            ; 120.36    ; 10/11/2022 18:55:22 ; Delivering by Runner                              ; I002 - 3, I001 - 2                                                                                                                                                                                     \s
                ORD00066  ; C001 ; S002 ; R001 ; 5.0      ; Delivery       ; T008 ; Extra napkins, please.                                      ; 108.06    ; 12/03/2024 12:22:14 ; Vendor Preparing                                  ; I002 - 2, I001 - 3                                                                                                                                                                                     \s
                ORD00067  ; C001 ; S002 ; R002 ; 5.0      ; Takeaway       ; T044 ; Extra spicy, if possible.                                   ; 131.76    ; 06/04/2024 03:25:45 ; Ready for Pick Up                                 ; I002 - 3, I001 - 1                                                                                                                                                                                     \s
                ORD00068  ; C001 ; S001 ; R001 ; 5.0      ; Delivery       ; T032 ; Extra spicy, if possible.                                   ; 88.46     ; 08/01/2024 22:44:54 ; Waiting for Vendor and Delivery Confirmation      ; I002 - 5, I001 - 2                                                                                                                                                                                     \s
                ORD00069  ; C002 ; S001 ; R001 ; 5.0      ; Dine In        ; T001 ; Need cutlery included.                                      ; 114.88    ; 24/10/2022 17:01:34 ; Ready for Pick Up                                 ; I002 - 5, I001 - 1                                                                                                                                                                                     \s
                ORD00070  ; C002 ; S001 ; R002 ; 5.0      ; Dine In        ; T016 ; Table near the window preferred.                            ; 149.56    ; 20/01/2023 16:08:40 ; Waiting for Vendor Confirmation                   ; I002 - 2, I001 - 1                                                                                                                                                                                     \s
                ORD00071  ; C001 ; S001 ; R001 ; 5.0      ; Takeaway       ; T025 ; Would like a corner table.                                  ; 47.02     ; 17/01/2021 11:27:47 ; Vendor Preparing                                  ; I002 - 3, I001 - 2                                                                                                                                                                                     \s
                ORD00072  ; C001 ; S002 ; R002 ; 5.0      ; Delivery       ; T029 ; No onions in the food, please.                              ; 56.38     ; 05/04/2024 19:54:30 ; Ready for Pick Up                                 ; I002 - 1, I001 - 1                                                                                                                                                                                     \s
                ORD00073  ; C001 ; S001 ; R002 ; 5.0      ; Takeaway       ; T008 ; Allergic to peanuts, please take note.                      ; 60.3      ; 09/09/2021 20:08:34 ; Waiting for Vendor Confirmation                   ; I002 - 2, I001 - 1                                                                                                                                                                                     \s
                ORD00074  ; C001 ; S002 ; R002 ; 5.0      ; Takeaway       ; T015 ; High chair needed.                                          ; 47.24     ; 21/11/2024 09:19:22 ; Waiting for Vendor and Delivery Confirmation      ; I002 - 5, I001 - 3                                                                                                                                                                                     \s
                ORD00075  ; C001 ; S002 ; R002 ; 5.0      ; Dine In        ; T035 ; High chair needed.                                          ; 127.34    ; 20/05/2024 18:56:57 ; Completed                                         ; I002 - 3, I001 - 3                                                                                                                                                                                     \s
                ORD00076  ; C001 ; S002 ; R001 ; 5.0      ; Takeaway       ; T043 ; No onions in the food, please.                              ; 133.2     ; 23/11/2023 18:52:53 ; Pending Change                                    ; I002 - 2, I001 - 1                                                                                                                                                                                     \s
                ORD00077  ; C001 ; S002 ; R002 ; 5.0      ; Takeaway       ; T037 ; Allergic to peanuts, please take note.                      ; 52.95     ; 20/04/2022 02:38:06 ; Vendor Preparing                                  ; I002 - 2, I001 - 1                                                                                                                                                                                     \s
                ORD00078  ; C002 ; S001 ; R001 ; 5.0      ; Takeaway       ; T023 ; Extra spicy, if possible.                                   ; 70.73     ; 17/02/2021 05:01:25 ; Waiting for Vendor Confirmation                   ; I002 - 5, I001 - 3                                                                                                                                                                                     \s
                ORD00079  ; C001 ; S002 ; R001 ; 5.0      ; Dine In        ; T011 ; Quiet table preferred.                                      ; 99.34     ; 08/10/2024 04:34:02 ; Waiting for Vendor and Delivery Confirmation      ; I002 - 3, I001 - 3                                                                                                                                                                                     \s
                ORD00080  ; C001 ; S001 ; R002 ; 5.0      ; Dine In        ; T020 ; Please call upon arrival.                                   ; 124.34    ; 29/10/2023 00:41:06 ; Waiting for Vendor and Delivery Confirmation      ; I002 - 2, I001 - 3                                                                                                                                                                                     \s
                ORD00081  ; C001 ; S002 ; R002 ; 5.0      ; Delivery       ; T013 ; High chair needed.                                          ; 52.13     ; 12/10/2024 09:13:36 ; Pending Change                                    ; I002 - 1, I001 - 1                                                                                                                                                                                     \s
                ORD00082  ; C001 ; S002 ; R001 ; 5.0      ; Delivery       ; T011 ; Extra napkins, please.                                      ; 72.53     ; 01/01/2025 11:46:32 ; Waiting for Delivery Confirmation                 ; I002 - 1, I001 - 1                                                                                                                                                                                     \s
                ORD00083  ; C001 ; S001 ; R002 ; 5.0      ; Delivery       ; T001 ; Extra spicy, if possible.                                   ; 147.04    ; 19/11/2023 20:01:22 ; Delivering by Runner                              ; I002 - 5, I001 - 1                                                                                                                                                                                     \s
                ORD00084  ; C001 ; S001 ; R002 ; 5.0      ; Delivery       ; T012 ; Allergic to peanuts, please take note.                      ; 115.05    ; 18/09/2023 21:13:57 ; Waiting for Vendor Confirmation                   ; I002 - 4, I001 - 3                                                                                                                                                                                     \s
                ORD00085  ; C002 ; S001 ; R001 ; 5.0      ; Dine In        ; T037 ; Extra napkins, please.                                      ; 145.11    ; 13/05/2023 05:07:12 ; Ready for Pick Up                                 ; I002 - 4, I001 - 3                                                                                                                                                                                     \s
                ORD00086  ; C001 ; S002 ; R002 ; 5.0      ; Delivery       ; T033 ; Would like a corner table.                                  ; 123.89    ; 06/02/2024 00:38:53 ; Completed                                         ; I002 - 1, I001 - 3                                                                                                                                                                                     \s
                ORD00087  ; C002 ; S002 ; R001 ; 5.0      ; Takeaway       ; T043 ; Allergic to peanuts, please take note.                      ; 94.11     ; 13/11/2022 22:26:02 ; Ready for Pick Up                                 ; I002 - 3, I001 - 1                                                                                                                                                                                     \s
                ORD00088  ; C001 ; S002 ; R001 ; 5.0      ; Delivery       ; T037 ; Allergic to peanuts, please take note.                      ; 43.64     ; 11/09/2022 01:30:42 ; Cancelled                                         ; I002 - 2, I001 - 3                                                                                                                                                                                     \s
                ORD00089  ; C001 ; S002 ; R002 ; 5.0      ; Dine In        ; T031 ; No special requests.                                        ; 105.77    ; 04/06/2024 17:40:14 ; Delivering by Runner                              ; I002 - 5, I001 - 2                                                                                                                                                                                     \s
                ORD00090  ; C001 ; S001 ; R002 ; 5.0      ; Takeaway       ; T014 ; Quiet table preferred.                                      ; 61.32     ; 17/11/2024 08:16:45 ; Completed                                         ; I002 - 3, I001 - 2                                                                                                                                                                                     \s
                ORD00091  ; C002 ; S001 ; R001 ; 5.0      ; Takeaway       ; T006 ; High chair needed.                                          ; 113.0     ; 22/03/2024 13:01:58 ; Ready for Pick Up                                 ; I002 - 4, I001 - 3                                                                                                                                                                                     \s
                ORD00092  ; C001 ; S001 ; R002 ; 5.0      ; Delivery       ; T006 ; No special requests.                                        ; 127.12    ; 14/08/2022 14:06:45 ; Cancelled                                         ; I002 - 5, I001 - 3                                                                                                                                                                                     \s
                ORD00093  ; C001 ; S001 ; R001 ; 5.0      ; Takeaway       ; T040 ; Extra spicy, if possible.                                   ; 78.24     ; 18/02/2023 11:32:15 ; Pending Change                                    ; I002 - 2, I001 - 3                                                                                                                                                                                     \s
                ORD00094  ; C001 ; S001 ; R001 ; 5.0      ; Delivery       ; T016 ; Extra napkins, please.                                      ; 148.59    ; 06/02/2024 14:14:06 ; Waiting for Vendor and Delivery Confirmation      ; I002 - 2, I001 - 1                                                                                                                                                                                     \s
                ORD00095  ; C001 ; S001 ; R002 ; 5.0      ; Takeaway       ; T026 ; High chair needed.                                          ; 81.65     ; 19/07/2021 14:40:10 ; Cancelled                                         ; I002 - 2, I001 - 2                                                                                                                                                                                     \s
                ORD00096  ; C001 ; S002 ; R002 ; 5.0      ; Delivery       ; T050 ; Allergic to peanuts, please take note.                      ; 70.6      ; 01/09/2023 01:49:53 ; Ready for Pick Up                                 ; I002 - 2, I001 - 2                                                                                                                                                                                     \s
                ORD00097  ; C002 ; S001 ; R002 ; 5.0      ; Dine In        ; T025 ; High chair needed.                                          ; 55.13     ; 24/08/2021 17:56:06 ; Vendor Preparing                                  ; I002 - 4, I001 - 3                                                                                                                                                                                     \s
                ORD00098  ; C001 ; S002 ; R001 ; 5.0      ; Dine In        ; T007 ; Please call upon arrival.                                   ; 50.02     ; 15/10/2022 13:59:00 ; Waiting for Delivery Confirmation                 ; I002 - 1, I001 - 1                                                                                                                                                                                     \s
                ORD00099  ; C002 ; S002 ; R002 ; 5.0      ; Takeaway       ; T042 ; High chair needed.                                          ; 29.76     ; 04/07/2024 15:36:22 ; Completed                                         ; I002 - 2, I001 - 1                                                                                                                                                                                     \s
                ORD00100  ; C002 ; S002 ; R002 ; 5.0      ; Takeaway       ; T049 ; No special requests.                                        ; 54.27     ; 20/12/2024 00:10:58 ; Completed                                         ; I002 - 1, I001 - 1                                                                                                                                                                                     \s
                """;

        // Set current time as 1 January 2025 3:30 pm
        Utility.setCurrentTime(LocalDateTime.of(2025, 1, 2, 15, 30, 30));

        // Try to write file
        try {

            // Get the order file and write the orders to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(TESTING_FILE_PATH + OrderFileIO.ORDER_FILE_NAME));
            writer.write(orderList);

            // Close writer after done
            writer.close();

            // The file should be read
            OrderFileIO.readFile();

        } catch (IOException ex) {

            // Fail the test immediately if the orders could not be written to the file
            fail("Unable to write to order text file.");
        }
    }

    /**
     * This test focuses on the operation where the manager retrieves the total number of orders of a vendor.
     */
    @Test
    void managerGetGraphInformation() {

        // Test if the information can be obtained correctly
        for (Utility.TimeframeFilter filter : Utility.TimeframeFilter.values()) {

            // (Order and feedback) Get the size for each filter
            int filteredOrderSize = Order.filterOrder(filter).size();
            int filteredFeedbackSize = Feedback.filterFeedback(filter).size();

            // (Order) Filter vendor 1 - Get the size for each filter
            ArrayList<Order> vendorOrder = Order.filterOrder(vendor1, filter);
            assertNotNull(vendorOrder);
            int vendorOrderSize = vendorOrder.size();

            // Filter runner 1 - Get size
            ArrayList<Order> runnerOrder = Order.filterOrder(runner1, filter);
            assertNotNull(runnerOrder);
            int runnerOrderSize = runnerOrder.size();

            // Test each filter
            switch (filter) {

                // For 02/01/2025
                case TODAY -> {
                    assertEquals(15, filteredOrderSize);
                    assertEquals(7, vendorOrderSize);
                    assertEquals(9, runnerOrderSize);
                    assertEquals(1, filteredFeedbackSize);
                }

                // For entire January
                case DAILY -> {
                    assertEquals(17, filteredOrderSize);
                    assertEquals(7, vendorOrderSize);
                    assertEquals(10, runnerOrderSize);
                    assertEquals(1, filteredFeedbackSize);
                }

                // For Feb 2024 - Jan 2025
                case MONTHLY, QUARTERLY -> {
                    assertEquals(61, filteredOrderSize);
                    assertEquals(26, vendorOrderSize);
                    assertEquals(30, runnerOrderSize);
                    assertEquals(2, filteredFeedbackSize);
                }

                // For 2021 - 2025
                case YEARLY -> {
                    assertEquals(100, filteredOrderSize);
                    assertEquals(48, vendorOrderSize);
                    assertEquals(52, runnerOrderSize);
                    assertEquals(3, filteredFeedbackSize);
                }
            }
        }
    }

    /**
     * This test focuses on the operation where the manager deletes an item of the vendor.
     */
    @Test
    void managerDeleteItem() {

        // Make sure that item 1 is in the item list
        boolean itemInList = Item.getItemList().contains(item1);
        assertTrue(itemInList);

        // Retrieve the notification list
        ArrayList<Notification> initialNotification = TestUtility.convertToNotificationArray(
                VendorNotification.getVendorNotificationList()
        );

        // Delete item 1
        boolean deleteItem = item1.managerDeleteItem();

        // Make sure that the deletion is successful
        assertTrue(deleteItem);

        // Make sure that item 1 is not found in the list anymore
        itemInList = Item.getItemList().contains(item1);
        assertFalse(itemInList);

        // Retrieve the newly created notification
        ArrayList<Notification> differentNotification = TestUtility.getDifferentNotification(
                initialNotification,
                TestUtility.convertToNotificationArray(VendorNotification.getVendorNotificationList())
        );

        // Make sure that there is only one notification created
        assertEquals(1, differentNotification.size());

        // Make sure that the description is correct
        assertEquals(
                "The item " + item1.getItemName() + " (" + item1.getItemID() + ") has been removed by the manager due to its inappropriate nature for sale on this platform.",
                differentNotification.getFirst().getNotificationDetails()
        );
    }

    /**
     * This test focuses on the operation where the manager filters the feedback list.
     */
    @Test
    void managerFilterFeedbackList() {

        // Retrieve the relevant feedback based on category
        ArrayList<Feedback> systemFeedback = Feedback.arrangeFeedbackList(Feedback.Category.SYSTEM, Feedback.Filter.LATEST_TO_OLDEST);
        ArrayList<Feedback> vendorFeedback = Feedback.arrangeFeedbackList(Feedback.Category.VENDOR, Feedback.Filter.HIGH_TO_LOW_RATING);
        ArrayList<Feedback> runnerFeedback = Feedback.arrangeFeedbackList(Feedback.Category.DELIVERY_RUNNER, Feedback.Filter.OLDEST_TO_LATEST);

        // Make sure they are not empty
        assertNotNull(systemFeedback);
        assertNotNull(vendorFeedback);
        assertNotNull(runnerFeedback);

        // Check if the method is able to retrieve the associated categories (each should have one)
        assertEquals(1, systemFeedback.size());
        assertEquals(1, vendorFeedback.size());
        assertEquals(1, runnerFeedback.size());

        // Change the feedbacks to vendor category
        feedback1.setFeedbackCategory(Feedback.Category.VENDOR);        // Feedback 1: Rating 4.5 at 6/4/2021
        feedback2.setFeedbackCategory(Feedback.Category.VENDOR);        // Feedback 2: Rating 3.5 at 2/1/2025
        feedback3.setFeedbackCategory(Feedback.Category.VENDOR);        // Feedback 3: Rating 1.5 at 9/8/2024

        // Try to arrange based on different conditions
        ArrayList<Feedback> arrangementOne = Feedback.arrangeFeedbackList(Feedback.Category.VENDOR, Feedback.Filter.LOW_TO_HIGH_RATING);
        ArrayList<Feedback> arrangementTwo = Feedback.arrangeFeedbackList(Feedback.Category.VENDOR, Feedback.Filter.HIGH_TO_LOW_RATING);
        ArrayList<Feedback> arrangementThree = Feedback.arrangeFeedbackList(Feedback.Category.VENDOR, Feedback.Filter.OLDEST_TO_LATEST);
        ArrayList<Feedback> arrangementFour = Feedback.arrangeFeedbackList(Feedback.Category.VENDOR, Feedback.Filter.LATEST_TO_OLDEST);

        // Make sure that the lists are not null for all
        assertNotNull(arrangementOne);
        assertNotNull(arrangementTwo);
        assertNotNull(arrangementThree);
        assertNotNull(arrangementFour);

        // Test for lowest to highest rating
        assertEquals(feedback3, arrangementOne.getFirst());
        assertEquals(feedback1, arrangementOne.getLast());

        // Test for highest to lowest rating
        assertEquals(feedback1, arrangementTwo.getFirst());
        assertEquals(feedback3, arrangementTwo.getLast());

        // Test for oldest to latest
        assertEquals(feedback1, arrangementThree.getFirst());
        assertEquals(feedback2, arrangementThree.getLast());

        // Test for latest to oldest
        assertEquals(feedback2, arrangementFour.getFirst());
        assertEquals(feedback1, arrangementFour.getLast());
    }

    /**
     * This test focuses on the operation where manager provides a reply for a feedback.
     */
    @Test
    void testManagerProvideFeedback() {

        // Get the initial notification list for customer
        ArrayList<Notification> initialNotification = TestUtility.convertToNotificationArray(
                CustomerNotification.getCustomerNotificationList()
        );

        // Take feedback 2 (without feedback) and provide an empty reply
        boolean replySuccess = feedback2.managerProvideReply("  ");

        // Check if the operation is successful
        assertFalse(replySuccess);

        // Provide a proper reply this time
        String reply = "Sorry for the inconvenience.";
        replySuccess = feedback2.managerProvideReply(reply);

        // CHeck if the operation is successful
        assertTrue(replySuccess);

        // Check if the correct content is written to feedback 2
        assertEquals(reply, feedback2.getReplyFromManager());

        // Retrieve the newly created notification
        ArrayList<Notification> differentNotification = TestUtility.getDifferentNotification(
                initialNotification,
                TestUtility.convertToNotificationArray(
                        CustomerNotification.getCustomerNotificationList()
                )
        );

        // Make sure that only one notification is created
        assertEquals(1, differentNotification.size());

        // Check if the notification is sent to the correct user
        assertEquals(
                feedback2.getCustomerAssociated().getUserID(),
                differentNotification.getFirst().getEntityID()
        );

        // Make sure that the description of the notification is correct
        assertEquals(
                "Thank you for reaching out! Our team has reviewed your feedback " + feedback2.getFeedbackID() + " and provided some response. Please check it out for more details.",
                differentNotification.getFirst().getNotificationDetails()
        );

        // Erroneous input: feedback 1 (already has a reply)
        boolean errorFeedback = feedback1.managerProvideReply("Hello!");
        assertFalse(errorFeedback);
    }
}
