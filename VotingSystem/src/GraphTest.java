/**
 * Created by Kyle Anderson on 12/2/2016.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class GraphTest extends Application {
    final static String obama = "Barack Obama";
    final static String hillary = "Hillary Clinton";
    final static String trump = "Donald Trump";


    @Override public void start(Stage stage) {
        stage.setTitle("Graph Test");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<>(xAxis,yAxis);
        bc.setTitle("Vote Total");
        xAxis.setLabel("America");
        yAxis.setLabel("Votes");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data(obama, 500190));
        series1.getData().add(new XYChart.Data(hillary, 295096));
        series1.getData().add(new XYChart.Data(trump, 155079));

        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}