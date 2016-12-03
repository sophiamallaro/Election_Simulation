/**
 * Created by Tengku on 12/2/2016.
 */


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.ResourceBundle;

public class AddCandidateController implements Initializable {
    List<Position> positions;
    private TestDB data = new TestDB();
    Candidate newCandidate = new Candidate();
    List<String> titleNames;
    List<Position>  offices;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partySelect.getItems().clear();
        partySelect.getItems().addAll("Democrat", "Republican", "Green Party", "Independent", "Libertarian");
        List<Position>  offices = data.getPositions("1502");
        List<String> titleNames = new ArrayList<>();
        for(Position position : offices) {
            titleNames.add(position.getPositiontitle());
        }

        positionSelect.getItems().addAll(titleNames);
    }

    @FXML
    public void partySelected() throws Exception {
        newCandidate.setParty(partySelect.getValue().toString());
    }

    @FXML
    public void positionSelected() throws Exception {
        String selected = partySelect.getValue().toString();
        Integer positionID = data.getPositionID(selected);
        newCandidate.setPositionid(positionID);
    }

    @FXML
    public void submitButtonPressed() throws Exception {

        try {
            newCandidate.setFirstName(candidateFirstName.getText());
            newCandidate.setLastName(candidateLastName.getText());
            data.addCandidate(newCandidate);
        }
        catch (NullPointerException ex)  {
            candidateFirstName.setText("Enter First Name");
            candidateFirstName.selectAll();
        }
    }
}
