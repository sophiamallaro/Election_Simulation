import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
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
    private static final TestDB data = new TestDB();
    private List<ToggleGroup> buttonGroups;

    public void start(Stage stage) {
        //Set up GUI
        VBox box = new VBox();
        Scene scene = new Scene(box,400,500);
        stage.setScene(scene);
        buttonGroups = new ArrayList<ToggleGroup>();

        Label title = new Label("OFFICAL GENERAL ELECTION BALLOT");
        title.setFont(Font.font("Helvetica-Bold", 20));
        box.getChildren().add(title);

        List<Position> candidates = data.getPositionsWithCandidates("0102");
        for(Position position : candidates) {
            ToggleGroup group = new ToggleGroup();
            Label text = new Label(position.getPositiontitle());
            text.setFont(Font.font("Helvetica-Bold", 16));
            box.getChildren().add(text);
            for(Candidate candidate: position.getCandidates()) {
                RadioButton button = new RadioButton(candidate.getFirstName() + " " + candidate.getLastName() + ", " + candidate.getParty());
                button.setFont(Font.font("Helvetica-Bold", 12));
                button.setToggleGroup(group);
                box.getChildren().add(button);
            }
            Label blank = new Label("    ");
            buttonGroups.add(group);
            box.getChildren().add(blank);
        }

        Button voteButton = new Button("VOTE");
        voteButton.setFont(Font.font("Helvetica-Bold", 14));
        box.getChildren().add(voteButton);

        voteButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                for(ToggleGroup group : buttonGroups) {
                    //System.out.println(group.getSelectedToggle().toString().split("'")[1]);
                    data.voteFor(group.getSelectedToggle().toString().split("'")[1]);
                }
            }
        });

        //Add Action listener to vote button to do the vote count incrementing





        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}

