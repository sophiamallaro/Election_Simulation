import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

//import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class is the controller for the Precincts.fxml GUI.
 * The class generally adds a new office to the database either
 * at the local, state, or federal domain.
 *
 * @author Sophia Mallaro
 * Created by smallaro on 12/3/16.
 */
public class ManagePrecinctsController implements Initializable {

    //GUI controls defined in FXML and used by the controller's code
    @FXML
    Button add;

    @FXML
    TextField name;

    @FXML
    ComboBox<String> domain;

    private static final Database data = new Database();

    //called by FXMLLoader to initialize the controller
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> domains = new ArrayList<String>();
        domains.add("Local");
        domains.add("State");
        domains.add("Federal");
        ObservableList obList = FXCollections.observableList(domains);
        domain.setItems(obList);
        name.setText("");
    }

    /**
     * This method adds the officer title to the database after
     * the add button clicked. The method does not allow the
     * user to proceed without first entering an officer title and
     * selecting a domain.
     *
     * @param event
     */
    @FXML
    public void addPressed(ActionEvent event) {
        if(name.getText().equals("") || name.getText().equals("Enter Officer Title")) {
            name.setText("Enter Officer Title");
        } else if(!domain.getSelectionModel().isEmpty()) {
            char[] givenID = StateAndPrecinctController.getIdCode().toCharArray();
            String stateid = "";
            String precinctid = "";
            if(domain.getSelectionModel().getSelectedIndex()==0){
                stateid += givenID[0];
                stateid += givenID[1];
                precinctid += givenID[0];
                precinctid += givenID[1];
            } else if(domain.getSelectionModel().getSelectedIndex()==1) {
                stateid += givenID[0];
                stateid += givenID[1];
                precinctid = "00";
            } else {
                stateid = "00";
                precinctid = "00";
            }
            stateid +=precinctid;
            data.addPosition(name.getText(), stateid);
            Node node = (Node) add;
            Stage myStage = (Stage) node.getScene().getWindow();
            myStage.setScene(ElectionDriver.getStartScene());
            myStage.show();
        }
    }
}
