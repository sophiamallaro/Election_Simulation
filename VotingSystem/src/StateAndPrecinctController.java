import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kanderson8 on 12/2/16.
 */
public class StateAndPrecinctController {
    private static Stage myStage;

    @FXML
    private void displayStateAndPrecinct(ActionEvent event) throws Exception{
        Node node = (Node) event.getSource();
        myStage = (Stage) node.getScene().getWindow();
        Parent stateAndPrecinct = FXMLLoader.load(getClass().getResource("Auditor.fxml"));
        myStage.setScene(new Scene(stateAndPrecinct));
        myStage.show();
    }
}
