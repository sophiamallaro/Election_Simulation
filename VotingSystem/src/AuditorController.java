import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by smallaro on 12/2/16.
 */
public class AuditorController implements Initializable{
    private TestDB data = new TestDB();
    private ObservableList precinctList;
    List<State> states;
    private static String idCode;
    private String stateID;
    private String precinctID;
    private String buttonPressed;

    @FXML
    ComboBox<String> stateSelect;

    @FXML
    ComboBox<String> precinct;

    @FXML
    Button generateButton;

    AuditorController(String event) {
        this.buttonPressed = event;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        states = data.getStates();
        List<String> stateNames = new ArrayList<>();
        for(State state : states) {
            stateNames.add(state.toString());
        }
        ObservableList obList = FXCollections.observableList(stateNames); //http://stackoverflow.com/questions/18361195/javafx-how-to-load-populate-values-at-start-up
        List<String> test = new ArrayList<>();
        ObservableList precinctList = FXCollections.observableList(test);
        stateSelect.setItems(obList);
        precinct.setItems(precinctList);
        //Parent.g
    }


    @FXML
    public void stateSelected() throws Exception{
        for(State state : states) {
            if(state.getStateName().equals(stateSelect.getValue())) {
                int id = state.getId();
                stateID = Integer.toString(id);
                List<Precinct> precincts = data.getPrecincts(id);
                List<String> precinctNames = new ArrayList<>();
                for(Precinct precinct : precincts) {
                    precinctNames.add(precinct.getId());
                }
                ObservableList ob = FXCollections.observableList(precinctNames);
                precinct.setItems(ob);
            }
        }
    }

    @FXML
    public void precinctSelected() throws Exception{
        precinctID = precinct.getValue();
    }

    @FXML
    public void buttonPressed() throws Exception { //Generates full id code for precinct
        idCode = stateID + precinctID;
        if (idCode.length() == 3) {
            idCode = "0" + idCode;
        }
    }

    public static String getID() { //Returns a static variable representnig the ID Code
        return AuditorController.idCode;
    }

}
