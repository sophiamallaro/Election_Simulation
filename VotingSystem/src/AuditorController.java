import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by smallaro on 12/2/16.
 */
public class AuditorController extends StateControl implements Initializable{
    private TestDB data = new TestDB();
    private ObservableList precinctList;
    List<State> states;

    @FXML
    ComboBox<String> stateSelect;

    @FXML
    ComboBox<String> precinct;

    @FXML
    Button generateButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("The button pressed was " + getButtonPressed());
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
                setStateID(Integer.toString(id));
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
        setPrecinctID(precinct.getValue());
    }

    @FXML
    public void buttonPressed() throws Exception { //Generates full id code for precinct
        setIdCode(getStateID() + getPrecinctID());
        if (getIdCode().length() == 3) {
            setIdCode("0" + getIdCode());
        }
        try {
            Node node = (Node) generateButton;
            Stage myStage = (Stage) node.getScene().getWindow();
            //Requires that button names DO NOT CHANGE
            if(getButtonPressed().equals("Results")) {
                myStage.setScene(new Scene(data.loadChart()));
            } else {
                Parent stateAndPrecinct = FXMLLoader.load(getClass().getResource(getButtonPressed() + ".fxml"));
                myStage.setScene(new Scene(stateAndPrecinct));
                myStage.show();
            }
        } catch (NullPointerException npe) {
            System.out.print("The " + getButtonPressed() + ".fxml file does not exist");
            generateButton.getScene().getWindow().hide();
        }
    }

    //public static String getID() { //Returns a static variable representnig the ID Code
    //    return AuditorController.idCode;
    //}

}
