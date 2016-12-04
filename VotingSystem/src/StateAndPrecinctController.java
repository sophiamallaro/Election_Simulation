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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class is the controller for the StateAndPrecinct.fxml.
 * The class keeps track of the state and the precinct that has been
 * selected by the user. The StateAndPrecinct GUI is used each time
 * any of the main menu button is pressed. This class stores the state
 * that the user has selected and shows the precincts of the selected state.
 *
 * @author Sophia Mallaro
 * @see Database
 *
 * Created by smallaro on 12/2/16.
 */
public class StateAndPrecinctController extends StateControl implements Initializable{
    private Database data = new Database();
    private ObservableList<String> list;
    List<State> states;

    //GUI controls defined in FXML and used by the controller's code
    @FXML
    ComboBox<String> stateSelect;

    @FXML
    ComboBox<String> precinct;

    @FXML
    Button generateButton;

    //called by FXMLLoader to initialize the controller
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
        if(StateControl.getStateID() != null) {
            stateSelect.getSelectionModel().select(Integer.parseInt((StateControl.getStateID()))-1);
            precinctInititialize();
            precinct.getSelectionModel().select(Integer.parseInt((StateControl.getPrecinctID()))-1);
        }
    }

    /**
     * Store the stateID and generates a list of precincts to be displayed at
     * the combobox based on the state selected.
     *
     * @throws Exception
     */
    @FXML
    public void stateSelected() throws Exception{
        precinctInititialize();
    }

    public void precinctInititialize() {
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

    /**
     * Store the precinctID of the precinct selected
     * @throws Exception
     */
    @FXML
    public void precinctSelected() throws Exception{
        setPrecinctID(precinct.getValue());
    }

    /**
     * Generates full id code for precinct and change the scene to the
     * appropriate interface.
     *
     * Pressing the continue button will change the current scene depending
     * on the previous selection in the Main Menu. If the user has selected
     * the "Auditor" button in the main menu, this method will change the
     * scene to the Add Candidate interface (Auditor.fxml) after the user
     * has selected the state and the precinct. If the "Manage Precinct"
     * button was pressed, the interface will proceed to the Manage Precinct
     * Interface (Precincts.fxml). If the "Vote" button was selected,
     * pressing the continue button will generate a ballot for the voter
     * (VoteController.java). If the user pressed "Results" button, the method
     * will change the scene to show the results of the election (ResultsGraph.java)
     *
     * @throws Exception
     */
    @FXML
    public void buttonPressed() throws Exception {
        setIdCode(getStateID() + getPrecinctID());
        if (getIdCode().length() == 3) {
            setIdCode("0" + getIdCode());
        }
        if(!stateSelect.getSelectionModel().isEmpty() && !precinct.getSelectionModel().isEmpty()) {
            try {
                Node node = (Node) generateButton;
                Stage myStage = (Stage) node.getScene().getWindow();
                //Requires that button names DO NOT CHANGE
                if (getButtonPressed().equals("Results")) {
                    //myStage.setScene(new Scene(data.loadChart()));
                    ResultsGraph graph = new ResultsGraph();
                    graph.start(myStage);
                } else if (getButtonPressed().equals("Vote")) {
                    VoteController ballet = new VoteController();
                    ballet.start(myStage);
                } else {
                    String fxmlToLoad = getButtonPressed() + ".fxml";
                    Parent stateAndPrecinct = FXMLLoader.load(getClass().getResource(fxmlToLoad));
                    myStage.setScene(new Scene(stateAndPrecinct));
                    myStage.show();
                }
            } catch (NullPointerException npe) {//Throws NullPointerException if unable to find file
                generateButton.getScene().getWindow().hide();
            }//hi
        }
    }

}
