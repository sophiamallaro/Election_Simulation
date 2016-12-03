/**
 * Created by kanderson8 on 12/2/16.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ElectionDriver extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("VotingSystemMain.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
