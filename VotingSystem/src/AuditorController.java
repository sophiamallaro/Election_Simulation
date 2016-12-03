import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by smallaro on 12/2/16.
 */
public class AuditorController implements Initializable {
    private char[] precinctCode;
    private TestDB data = new TestDB();

    @FXML
    ComboBox<String> stateSelect;

    @FXML
    ComboBox<String> precinctSelect;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> states = data.getStates();
        ObservableList obList = FXCollections.observableList(states); //http://stackoverflow.com/questions/18361195/javafx-how-to-load-populate-values-at-start-up
        stateSelect.setItems(obList);
    }

}
