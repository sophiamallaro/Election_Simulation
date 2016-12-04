import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * This class is a controller class for the VotingSystemMain.fxml file.
 * The controller generally loads the next scene which is the
 * StateandPrecinct.fxml and stores the name of the button clicked.
 *
 * @author Kyle Anderson
 * Created by kanderson8 on 12/2/16.
 */

// Help with changing scenes found:
// http://stackoverflow.com/questions/12928751/changing-scene-when-a-button-is-pressed-fxml
// Image used for background found:
// https://quote.com/blog/wp-content/uploads/2016/06/american-flag.jpg
public class VotingSystemMainController extends StateControl {

    /**
     * This method displays the StateandPrecinct.fxml file
     * after an event occurs. The method also stores the
     * name of the button pressed for next course of action
     *
     * @param event
     * @throws Exception
     */
    @FXML
    private void displayStateAndPrecinct(ActionEvent event) throws Exception{
        //passes information about the button pressed to the next controller
        String myString = ((Button)event.getSource()).getText();
        //System.out.print("My string is " + myString);
        setButtonPressed(myString);
        Node node = (Node) event.getSource();
        setMyStage((Stage) node.getScene().getWindow());
        Parent stateAndPrecinct = FXMLLoader.load(getClass().getResource("StateAndPrecinct.fxml"));
        getMyStage().setScene(new Scene(stateAndPrecinct));
        getMyStage().show();
    }
}