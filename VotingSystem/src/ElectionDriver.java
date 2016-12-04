/**
 * @author Kyle Anderson
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ElectionDriver extends Application {
    private static Scene startScene;

    public static Scene getStartScene() {
        return startScene;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("VotingSystemMain.fxml"));
        startScene = new Scene(root);
        primaryStage.setScene(startScene);
        primaryStage.show(); //being stage
    }
}
