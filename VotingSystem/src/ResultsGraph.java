import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by kanderson8 on 12/3/16.
 */
public class ResultsGraph extends Application {
    private static final TestDB data = new TestDB();
    private static Button homeButton;

    @Override
    public void start(Stage stage) throws Exception {
        VBox box = new VBox();
        Scene scene = new Scene(box,400,500);
        stage.setScene(scene);
        box.getChildren().add(data.loadChart());
        homeButton = new Button("Home");
        homeButton.setFont(Font.font("Helvetica-Bold", 14));
        box.getChildren().add(homeButton);

        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stage.setScene(ElectionDriver.getStartScene());
            }
        });
    }
}
