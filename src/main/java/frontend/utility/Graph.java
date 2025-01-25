/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.utility;

import backend.entity.Feedback;
import backend.entity.Order;
import backend.utility.Utility;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author limbengrhui
 */
public class Graph extends JPanel {

    public final static int REVENUE_GRAPH = 0;
    public final static int DELIVERY_COUNT_GRAPH = 1;
    public final static int FEEDBACK_GRAPH = 2;

    /**
     * Creates new form Graph
     */
    public Graph(ArrayList<?> dataList, int graphType, Utility.TimeframeFilter filter, int width, int height) {

        // Render GUI components
        initComponents();

        // Set layout and size of the panel
        setLayout(null);
        setSize(width, height);
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));

        // If the list is empty
        if (dataList.isEmpty()) {

            // Create a label to show that no data is available
            JLabel emptyLabel = new JLabel("  No data available for now.");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            emptyLabel.setBounds(0, 0, 400, 30);
            setBackground(Color.WHITE);
            add(emptyLabel);

        } else {

            // Get an element of the array
            Object checkObject = dataList.getFirst();

            // Declare a variable to store the chart
            JFreeChart generatedChart = null;

            // Check the type of the object
            switch (checkObject) {

                // If it is order type
                case Order order -> {

                    // Create an empty order list
                    ArrayList<Order> orderList = new ArrayList<>();

                    // Convert list to order type and add to list
                    for (Object object : dataList) {
                        order = (Order) object;
                        orderList.add(order);
                    }

                    // Generate charts based on the list
                    if (graphType == 0) generatedChart = createRevenueLineChart(orderList, filter);
                    else if (graphType == 1) generatedChart = createDeliveryCountLineChart(orderList, filter);
                }

                // If the object is feedback type
                case Feedback feedback -> {

                    // Create an empty feedback list
                    ArrayList<Feedback> feedbackList = new ArrayList<>();

                    // Convert each object to feedback and store to list
                    for (Object object : dataList) {
                        feedback = (Feedback) object;
                        feedbackList.add(feedback);
                    }

                    // Generate the bar chart based on the feedback
                    if (graphType == 3) generatedChart = null;
                }

                // Else, throw a new exception for any other types of data list is provided
                default -> throw new IllegalStateException("Unexpected class: " + checkObject);
            }

            // Generate a chart panel based on the chart
            ChartPanel chartPanel = new ChartPanel(generatedChart);
            chartPanel.setBounds(0, 0, width, height);

            // Add the chart panel to the main panel
            add(chartPanel);
        }
    }

    /**
     * This method helps to generate the corresponding column keys based on the filter applied to the graphs.
     * @param filter The timeframe applied to the data
     * @return An object list, where index 0 containing the time frame list in LocalDateTime format, and index 1 representing the string column keys
     */
    private Object[][] generateColumnKey(Utility.TimeframeFilter filter) {

        // Retrieve the start and ending time based on the filter
        LocalDateTime startTime = Utility.getFilterStartAndEndTime(filter)[0];
        LocalDateTime endTime = Utility.getFilterStartAndEndTime(filter)[1];

        // Prepare two strings, timeframeList to determine the range the order falls under, column key for chart generation
        LocalDateTime[] timeframeList;
        String[] columnKey;

        // Perform different calculation to retrieve the lists based on filters
        switch (filter) {

            // If the filter is daily (a month's time)
            case DAILY -> {

                // Calculate the number of days
                int numOfDay = (int) Duration.between(startTime, endTime).toDays() + 1;

                // Initialize the lists
                timeframeList = new LocalDateTime[numOfDay + 1];
                columnKey = new String[numOfDay];

                // Add values to the list
                for (int i = 1; i <= numOfDay; i++) {
                    timeframeList[i - 1] = startTime.plusDays(i - 1);
                    columnKey[i - 1] = i + " " + startTime.format(DateTimeFormatter.ofPattern("MMM"));
                }
                timeframeList[numOfDay] = startTime.plusDays(numOfDay);

            }

            // Display data for each month (a year's time)
            case MONTHLY -> {

                // Indicate the number of month and initialize lists
                int numOfMonth = 12;
                timeframeList = new LocalDateTime[numOfMonth + 1];
                columnKey = new String[numOfMonth];

                // Add the values to the list
                for (int i = 1; i <= numOfMonth; i++) {
                    timeframeList[i - 1] = startTime.plusMonths(i - 1);
                    columnKey[i - 1] = startTime.plusMonths(i - 1).format(DateTimeFormatter.ofPattern("MMM yyyy"));
                }
                timeframeList[numOfMonth] = startTime.plusMonths(numOfMonth);
            }

            // If data is sorted quarterly (1 quarter = 3 months)
            case QUARTERLY -> {

                // Get the number of quarters
                int numOfQuarter = 4;

                // Initialize the lists
                timeframeList = new LocalDateTime[numOfQuarter + 1];
                columnKey = new String[numOfQuarter];

                // Add values to the lists
                for (int i = 1; i <= numOfQuarter; i++) {
                    timeframeList[i - 1] = startTime.plusMonths(3L * (i - 1));
                    columnKey[i - 1] =
                            startTime.plusMonths(3L * (i - 1)).format(DateTimeFormatter.ofPattern("MMM yyyy")) +
                                    " - " +
                                    startTime.plusMonths(3L * i).format(DateTimeFormatter.ofPattern("MMM yyyy"));
                }
                timeframeList[numOfQuarter] = startTime.plusYears(1);
            }

            // When data is sorted yearly (5 years)
            case YEARLY -> {

                // Declare the variables to be used
                int numOfYears = 5;
                timeframeList = new LocalDateTime[numOfYears + 1];
                columnKey = new String[numOfYears];

                // Loop and add data to the lists
                for (int i = 1; i <= numOfYears; i++) {
                    timeframeList[i - 1] = startTime.plusYears(i - 1);
                    columnKey[i - 1] = startTime.plusYears(i - 1).format(DateTimeFormatter.ofPattern("yyyy"));
                }
                timeframeList[numOfYears] = startTime.plusYears(numOfYears);
            }

            // If the filters are incorrect, throw an exception
            default -> throw new IllegalStateException("Error in writing data to graph. Please inspect code.");
        }

        return new Object[][]{timeframeList, columnKey};
    }

    /**
     * This method helps to generate the revenue line chart.
     *
     * @param orderList The order list to be generated as a line chart
     * @param filter The filter applied towards the list
     * @return A JFreeChart representing the overall order list
     */
    private JFreeChart createRevenueLineChart(ArrayList<Order> orderList, Utility.TimeframeFilter filter) {

        // Retrieve the relevant column keys to aid in calculation
        Object[][] generatedColumnKeyObject = generateColumnKey(filter);
        LocalDateTime[] timeframeList = (LocalDateTime[]) generatedColumnKeyObject[0];
        String[] columnKey = (String[]) generatedColumnKeyObject[1];

        // Declare an initial dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Since data has not been aggregated, we first declare a list to store the aggregated values for each category
        double[] datasetValue = new double[columnKey.length];

        // Loop through the order list
        for (Order order : orderList) {

            // Retrieve the information from the order
            double orderRevenue = order.getOrderPrice();
            LocalDateTime orderTime = order.getOrderedDate();

            // Loop through each range of time
            for (int i = 0; i < timeframeList.length; i ++) {

                // If the time does not fall within the range, go to the next loop
                if (orderTime.isBefore(timeframeList[i]) || orderTime.isAfter(timeframeList[i + 1])) continue;

                // Else, add the revenue to the dataset and stop this inner loop
                datasetValue[i] += orderRevenue;
                break;
            }
        }

        // Add the data into the dataset
        for (int i = 0; i < columnKey.length; i ++) {
            dataset.addValue(datasetValue[i], "Revenue", columnKey[i]);
        }

        // Get some information for display purpose
        String startDate = Utility.getFilterStartAndEndTime(filter)[0].format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String endDate = Utility.getFilterStartAndEndTime(filter)[1].format(DateTimeFormatter.ofPattern("dd MMM yyyy"));

        // Generate a chart based on the dataset
        JFreeChart generatedChart = ChartFactory.createLineChart(
                filter + " Revenue (" + startDate + " - " +  endDate  + ")",
                "Time",
                "Revenue (RM)",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );

        // Set font for the title
        generatedChart.getTitle().setFont(new Font("Arial", Font.BOLD, 24));

        // Get the background of the chart and make it white
        CategoryPlot chartPlot = generatedChart.getCategoryPlot();
        chartPlot.setInsets(new RectangleInsets(10, 10, 10, 10));
        chartPlot.setBackgroundPaint(Color.WHITE);
        chartPlot.setOutlineVisible(false);

        // Retrieve the render for the chart
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) chartPlot.getRenderer();

        // change the colour and thickness of the lines
        renderer.setSeriesPaint(0, new Color(128, 0, 0));
        BasicStroke stroke = new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        renderer.setSeriesStroke(0, stroke);

        // Add shape to each data point
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShapesFilled(0, true);
        renderer.setSeriesShape(0, new Rectangle2D.Double(-5, -5, 10, 10)); // Square

        // Let the plot use back the same renderer
        chartPlot.setRenderer(renderer);

        // Get the x-axis (domain axis)
        CategoryAxis domainAxis = chartPlot.getDomainAxis();

        // Set the font for x-axis
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font tickLabelFont = new Font("Arial", Font.PLAIN, 12);
        domainAxis.setLabelFont(labelFont);
        domainAxis.setTickLabelFont(tickLabelFont);

        // Change the direction of the x-axis (to show details better)
        if (filter == Utility.TimeframeFilter.DAILY) domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        if (filter == Utility.TimeframeFilter.MONTHLY) domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        // Set the properties for the tick labels
        domainAxis.setCategoryMargin(0.2);
        domainAxis.setMaximumCategoryLabelWidthRatio(2.0f);

        // Customize the y-axis
        ValueAxis valueAxis = chartPlot.getRangeAxis();
        valueAxis.setLabelFont(labelFont);
        valueAxis.setTickLabelFont(tickLabelFont);

        // Return the chart after finish customization
        return generatedChart;
    }

    /**
     * This method helps to generate the delivery count line chart.
     *
     * @param orderList The order list to be generated as a line chart
     * @param filter The filter applied towards the list
     * @return A JFreeChart representing the overall order list
     */
    private JFreeChart createDeliveryCountLineChart(ArrayList<Order> orderList, Utility.TimeframeFilter filter) {

        // Retrieve the relevant column keys to aid in calculation
        Object[][] generatedColumnKeyObject = generateColumnKey(filter);
        LocalDateTime[] timeframeList = (LocalDateTime[]) generatedColumnKeyObject[0];
        String[] columnKey = (String[]) generatedColumnKeyObject[1];

        // Declare an initial dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Since data has not been aggregated, we first declare a list to store the aggregated values for each category
        int[] datasetValue = new int[columnKey.length];

        // Loop through the order list
        for (Order order : orderList) {

            // Retrieve the information from the order
            LocalDateTime orderTime = order.getOrderedDate();

            // Loop through each range of time
            for (int i = 0; i < timeframeList.length; i ++) {

                // If the time does not fall within the range, go to the next loop
                if (orderTime.isBefore(timeframeList[i]) || orderTime.isAfter(timeframeList[i + 1])) continue;

                // Else, add the revenue to the dataset and stop this inner loop
                datasetValue[i] += 1;
                break;
            }
        }

        // Add the data into the dataset
        for (int i = 0; i < columnKey.length; i ++) {
            dataset.addValue(datasetValue[i], "Delivery Count", columnKey[i]);
        }

        // Get some information for display purpose
        String startDate = Utility.getFilterStartAndEndTime(filter)[0].format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String endDate = Utility.getFilterStartAndEndTime(filter)[1].format(DateTimeFormatter.ofPattern("dd MMM yyyy"));

        // Generate a chart based on the dataset
        JFreeChart generatedChart = ChartFactory.createLineChart(
                filter + " Delivery Count (" + startDate + " - " +  endDate  + ")",
                "Time",
                "Count",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );

        // Set font for the title
        generatedChart.getTitle().setFont(new Font("Arial", Font.BOLD, 24));

        // Get the background of the chart and make it white
        CategoryPlot chartPlot = generatedChart.getCategoryPlot();
        chartPlot.setInsets(new RectangleInsets(10, 10, 10, 10));
        chartPlot.setBackgroundPaint(Color.WHITE);
        chartPlot.setOutlineVisible(false);

        // Retrieve the render for the chart
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) chartPlot.getRenderer();

        // change the colour and thickness of the lines
        renderer.setSeriesPaint(0, new Color(128, 0, 0));
        BasicStroke stroke = new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        renderer.setSeriesStroke(0, stroke);

        // Add shape to each data point
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShapesFilled(0, true);
        renderer.setSeriesShape(0, new Rectangle2D.Double(-5, -5, 10, 10)); // Square

        // Let the plot use back the same renderer
        chartPlot.setRenderer(renderer);

        // Get the x-axis (domain axis)
        CategoryAxis domainAxis = chartPlot.getDomainAxis();

        // Set the font for x-axis
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font tickLabelFont = new Font("Arial", Font.PLAIN, 12);
        domainAxis.setLabelFont(labelFont);
        domainAxis.setTickLabelFont(tickLabelFont);

        // Change the direction of the x-axis (to show details better)
        if (filter == Utility.TimeframeFilter.DAILY) domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        if (filter == Utility.TimeframeFilter.MONTHLY) domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        // Set the properties for the tick labels
        domainAxis.setCategoryMargin(0.2);
        domainAxis.setMaximumCategoryLabelWidthRatio(2.0f);

        // Customize the y-axis
        ValueAxis valueAxis = chartPlot.getRangeAxis();
        valueAxis.setLabelFont(labelFont);
        valueAxis.setTickLabelFont(tickLabelFont);

        // Return the chart after finish customization
        return generatedChart;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
