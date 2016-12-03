import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

public class VoteController extends Application {
    @FXML
    private ScrollPane choices;
    private List<ToggleGroup> buttons;
    private static final TestDB data = new TestDB();

    public void start(Stage stage) {
        VBox box = new VBox();
        Scene scene = new Scene(box,400,600);
        stage.setScene(scene);
        buttons = new ArrayList<ToggleGroup>();

        Label title = new Label("OFFICAL GENERAL ELECTION BALLOT");
        title.setFont(Font.font("Helvetica-Bold", 20));
        box.getChildren().add(title);

        List<Position> candidates = data.getPositionsWithCandidates("1502");
        for(Position position : candidates) {
            ToggleGroup group = new ToggleGroup();
            Label text = new Label(position.getPositiontitle());
            text.setFont(Font.font("Helvetica-Bold", 16));
            box.getChildren().add(text);
            //test.appendText(position.getPositiontitle() + "\n");
            for(Candidate candidate: position.getCandidates()) {
                RadioButton button = new RadioButton(candidate.getFirstName() + " " + candidate.getLastName() + ", " + candidate.getParty());
                button.setToggleGroup(group);
                box.getChildren().add(button);
            }
            Label blank = new Label("    ");
            box.getChildren().add(blank);
            buttons.add(group);
        }

        /*for(ToggleGroup toggle : buttons) {
            for(RadioButton button : toggle) {

            }
            box.getChildren().add(toggle);
        }(*/

        //choices.getChild;
        //choices.setContent();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
