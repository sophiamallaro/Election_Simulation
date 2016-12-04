/**
 * This class is the controller for the Auditor.fxml GUI. The class
 * handles the function of adding candidates. After the user has
 * selected the state and the precinct, the Auditor.fxml GUI will appear
 * prompting for first name, last name, party, and position. When the
 * submit button is pressed, the candidate details will be added to
 * the database.
 *
 * This class implements the Initializable interface.
 *
 *@author Alya Mohd
 * Created by Tengku on 12/2/2016.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.ResourceBundle;

public class AddCandidateController implements Initializable {

    List<Position> positions;                       //Stores a list of Position objects
    private TestDB data = new TestDB();             //initializes a TestDB class
    Candidate newCandidate = new Candidate();       //intializes a Candidate class

    //GUI controls defined in FXML and used by the controller's code
    @FXML
    TextField candidateFirstName;

    @FXML
    TextField candidateLastName;

    @FXML
    ComboBox partySelect;

    @FXML
    ComboBox positionSelect;

    @FXML
    Button submitButton;

    //called by FXMLLoader to initialize the controller
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partySelect.getItems().clear();
        partySelect.getItems().addAll("Democrat", "Republican", "Green Party", "Independent", "Libertarian");   //provide the options for the party combo box

        List<Position>  offices = data.getPositions(StateAndPrecinctController.getIdCode()); //<--------check the idCode to get from the AuditorController

        List<String> titleNames = new ArrayList<>();
        for(Position position : offices) {
            titleNames.add(position.getPositiontitle());
        }

        positionSelect.getItems().addAll(titleNames);
        candidateFirstName.setText("");
        candidateLastName.setText("");
    }

    /**
     * Set the new candidate party association in the newCandidate object to
     * the party selected by the user from the combobox.
     * @throws Exception
     */
    @FXML
    public void partySelected() throws Exception {
        newCandidate.setParty(partySelect.getValue().toString());
    }

    /**
     * Set the new candidate position title in the newCandidate object to the
     * position selected by the user from the combobox.
     * @throws Exception
     */
    @FXML
    public void positionSelected() throws Exception {
        String selected = positionSelect.getValue().toString();
        Integer positionID = data.getPositionID(selected, StateAndPrecinctController.getIdCode());
        newCandidate.setPositionid(positionID);
    }

    /**
     *Submit the new candidate details to the database.
     *
     * After the submit button is pressed, the program will receive
     * the first name and last name of the new candidate from the text field.
     * The first name and last name will be stored the newCandidate object
     * under variable firstName and lastName. Then the new candidate
     * details will be added to the database.
     *
     * The GUI will then return to the main menu
     *
     * @throws Exception
     */
    @FXML
    public void submitButtonPressed() throws Exception {
        try {
            newCandidate.setFirstName(candidateFirstName.getText());
            newCandidate.setLastName(candidateLastName.getText());
            if(candidateFirstName.getText().equals("") || candidateFirstName.getText().equals("Enter First Name")) {
                candidateFirstName.setText("Enter First Name");
            } else if(candidateLastName.getText().equals("") || candidateLastName.getText().equals("Enter Last Name")) {
                candidateLastName.setText("Enter Last Name");
            } else if(!partySelect.getSelectionModel().isEmpty() && !positionSelect.getSelectionModel().isEmpty()) {
                data.addCandidate(newCandidate);
                Node node = (Node) submitButton;
                Stage myStage = (Stage) node.getScene().getWindow();
                myStage.setScene(ElectionDriver.getStartScene());
                myStage.show();
            }
        }
        catch (NullPointerException ex)  {
            candidateFirstName.setText("Enter First Name");
            candidateFirstName.selectAll();
            candidateLastName.setText("Enter Last Name");
            candidateFirstName.selectAll();
        }
    }
}
