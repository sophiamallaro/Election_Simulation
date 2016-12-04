import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

//import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by smallaro on 12/3/16.
 */
public class ManagePrecinctsController implements Initializable {
    @FXML
    Button add;

    @FXML
    TextField name;

    @FXML
    ComboBox<String> domain;

    private static final TestDB data = new TestDB();

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

    @FXML
    public void addPressed(ActionEvent event) {
        if(name.getText().equals("") || name.getText().equals("Enter Officer Title")) {
            name.setText("Enter Officer Title");
        } else if(!domain.getSelectionModel().isEmpty()) {
            char[] givenID = AuditorController.getIdCode().toCharArray();
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
        }
    }
}
