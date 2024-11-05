import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class CollatzConjectureGraph extends Application {

    @Override
    public void start(Stage stage) {
        // Set up the axes
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Step");
        yAxis.setLabel("Value");

        // Create the LineChart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Collatz Conjecture");

        // List of starting numbers
        List<Integer> startingNumbers = Arrays.asList(32,78,321,322,333,450,543); // You can add more numbers here

        // Generate the Collatz sequence for each starting number
        for (int startingNumber : startingNumbers) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName("Collatz Sequence for " + startingNumber);

            // Generate the Collatz sequence and add to the series
            int currentNumber = startingNumber;
            int step = 0;

            while (currentNumber != 1) {
                series.getData().add(new XYChart.Data<>(step, currentNumber));
                step++;
                if (currentNumber % 2 == 0) {
                    currentNumber /= 2;
                } else {
                    currentNumber = 3 * currentNumber + 1;
                }
            }
            series.getData().add(new XYChart.Data<>(step, 1)); // Adding the final value

            // Add the series to the chart
            lineChart.getData().add(series);
        }

        // Set up the scene and stage
        StackPane root = new StackPane();
        root.getChildren().add(lineChart);
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Collatz Conjecture Graph");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
