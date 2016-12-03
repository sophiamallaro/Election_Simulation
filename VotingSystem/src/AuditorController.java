import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private char[] precinctCode;
    private TestDB data = new TestDB();
    private ObservableList precinctList;
    List<State> states;

    @FXML
    ComboBox<String> stateSelect;

    @FXML
    ComboBox<String> precinct;

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

    }


    @FXML
    public void stateSelected() throws Exception{
        for(State state : states) {
            if(state.getStateName().equals(stateSelect.getValue())) {
                List<Precinct> precincts = data.getPrecincts(state.getId());
                List<String> precinctNames = new ArrayList<>();
                for(Precinct precinct : precincts) {
                    precinctNames.add(precinct.getId());
                }

                ObservableList ob = FXCollections.observableList(precinctNames);
                precinct.setItems(ob);
            }
        }


        //System.out.println("Triggered");
        //List<Precinct> precincts = data.getPrecincts(data.getStateID(stateSelect.getSelectionModel().getSelectedItem().toString()));
        //List<Precinct> precincts = data.getPrecincts(data.getStateID( stateSelect.getValue()));
        //ObservableList ob = FXCollections.observableList(precincts); //http://stackoverflow.com/questions/18361195/javafx-how-to-load-populate-values-at-start-up
        //precinct.setItems(ob);
        /*List<String> testList = new ArrayList<>();
        testList.add("Hello");
        testList.add("It's me");
        //ObservableList ob = FXCollections.observableList(testList);
        //precinct.setItems(ob);
        precinctList = FXCollections.observableList(testList);
        precinct.setItems(precinctList); */


    }

    /*@FXML
    public void buttonPressed() {
        List<Precinct> precincts = data.getPrecincts(data.getStateID( stateSelect.getValue()));
        precinct = new ChoiceBox<String>();
        ObservableList ob = FXCollections.observableList(precincts); //http://stackoverflow.com/questions/18361195/javafx-how-to-load-populate-values-at-start-up
        precinctSelect.setItems(ob);
    }*/

}
