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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.ResourceBundle;

public class AddCandidateController implements Initializable{
    private String candidate_firstName;
    private String candidate_lastName;
    List<String> party;


    @FXML
    TextField candidateFirstName;

    @FXML
    TextField candidateLastName;

    @FXML
    ComboBox partySelect;

    @FXML
    ComboBox positionSelect;

    @Override
    public void initialize(URL url, ResourceBundle rb)    {

    }

    @FXML
    public void firstNameEntered() throws Exception   {

    }

}
