import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kyle Anderson
 */
public class ResultsGraph extends Application {
    private static final Database data = new Database();
    private static final Button homeButton = new Button("Home");
    List<Position> positions;
    private final ComboBox<String> positionSelect = new ComboBox<>();
    private int currentPosition = 1;
    BarChart<String, Number> chart;

    @Override
    public void start(Stage stage) throws Exception {
        StackPane box = new StackPane();
        Scene scene = new Scene(box,400,500);
        stage.setScene(scene);
        positions = data.getPositionsWithCandidates(StateControl.getIdCode());
        List<String> positionNames = new ArrayList<>();
        for(Position position : positions) {
            positionNames.add(position.getPositiontitle());
        }
        System.out.println(Arrays.toString(positionNames.toArray()));
        positionSelect.getItems().addAll(positionNames);
        chart = data.loadChart(currentPosition);
        box.setAlignment(chart, Pos.CENTER);
        box.getChildren().add(chart);
        //homeButton = new Button("Home");
        homeButton.setFont(Font.font("Helvetica-Bold", 14));
        box.setAlignment(homeButton, Pos.BOTTOM_LEFT);
        box.getChildren().add(homeButton);
        box.setAlignment(positionSelect, Pos.BOTTOM_CENTER);
        box.getChildren().add(positionSelect);

        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                data.closeConnection();
                stage.setScene(ElectionDriver.getStartScene());
            }
        });

        positionSelect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selection = positionSelect.getSelectionModel().getSelectedItem();
                data.updateChart(chart,data.getPositionID(selection, StateControl.getIdCode()));
            }
        });
    }
}
