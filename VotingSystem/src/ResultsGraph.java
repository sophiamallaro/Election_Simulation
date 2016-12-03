import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kanderson8 on 12/3/16.
 */
public class ResultsGraph extends Application {
    private static final TestDB data = new TestDB();
    private static Button homeButton;
    List<Position> positions;
    private static ComboBox<String> positionSelect = new ComboBox<>();

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
        BarChart<String, ? extends Number> chart = data.loadChart();
        box.setAlignment(chart, Pos.CENTER);
        box.getChildren().add(data.loadChart());
        homeButton = new Button("Home");
        homeButton.setFont(Font.font("Helvetica-Bold", 14));
        box.setAlignment(homeButton, Pos.BOTTOM_LEFT);
        box.getChildren().add(homeButton);
        box.setAlignment(positionSelect, Pos.BOTTOM_CENTER);
        box.getChildren().add(positionSelect);

        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stage.setScene(ElectionDriver.getStartScene());
            }
        });
    }
}
