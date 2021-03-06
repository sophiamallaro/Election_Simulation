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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is the class to generate the result
 * graph of the election. The graph can be updated
 * with changing the positions in the combo box.
 * The class also allows the application to return
 * to the main menu upon clicking the home button.
 *
 * @author Kyle Anderson
 */
public class ResultsGraph extends Application {
    //Instantion of member variables
    private static final Database data = new Database();
    private static final Button homeButton = new Button("Home");
    List<Position> positions;
    private ArrayList<Integer> positionsDisplayed = new ArrayList<>();
    private final ComboBox<String> positionSelect = new ComboBox<>();
    BarChart<String, Number> chart;

    /**
     * Starts up the result graph class to generate
     * the election results
     *
     * @param stage
     * @throws Exception
     */
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
        chart = data.loadChart(1);
        positionsDisplayed.add(1);
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
                //data.closeConnection();
                stage.setScene(ElectionDriver.getStartScene());
            }
        });

        positionSelect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selection = positionSelect.getSelectionModel().getSelectedItem();
                int id = data.getPositionID(selection, StateControl.getIdCode());
                if(!positionsDisplayed.contains(id)) {
                    positionsDisplayed.add(id);
                    data.updateChart(chart, id);
                }
            }
        });
    }
}
