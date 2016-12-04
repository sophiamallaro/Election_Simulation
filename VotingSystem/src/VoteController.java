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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * This Vote Controller is the controller class for the Vote interface.
 * After the user has chosen the state and the precinct on the previous
 * interface, the ballot will appear. The positions and candidates for
 * those positions will be listed based on the selected state and precinct.
 *
 * After the candidates are selected and the vote button is pressed,
 * the program will register the vote to the database and the program will
 * return to the main menu.
 *
 * @author Sophia Mallaro
 * @see TestDB
 */

public class VoteController extends Application {
    @FXML
    private static final TestDB data = new TestDB();
    private List<ToggleGroup> buttonGroups;

    //default constructor
    VoteController() {
    }

    //set up the stage
    public void start(Stage stage) {
        //Set up GUI
        VBox box = new VBox();
        Scene scene = new Scene(box,400,500);
        stage.setScene(scene);
        buttonGroups = new ArrayList<ToggleGroup>();

        //Set title on the stage
        Label title = new Label("OFFICAL GENERAL ELECTION BALLOT");
        title.setFont(Font.font("Helvetica-Bold", 20));
        box.getChildren().add(title);

        //Search through the database for the candidates and list them on the scene
        List<Position> candidates = data.getPositionsWithCandidates(AuditorController.getIdCode());
        for(Position position : candidates) {
            System.out.println(position.getPositiontitle());
            ToggleGroup group = new ToggleGroup();
            Label text = new Label(position.getPositiontitle());
            text.setFont(Font.font("Helvetica-Bold", 16));
            box.getChildren().add(text);
            for(Candidate candidate: position.getCandidates()) {
                RadioButton button = new RadioButton(candidate.getFirstName() + " " + candidate.getLastName() + ", " + candidate.getParty());
                button.setFont(Font.font("Helvetica-Bold", 12));
                button.setToggleGroup(group);
                box.getChildren().add(button);
                System.out.println(candidate.getFirstName());
            }
            Label blank = new Label("    ");
            buttonGroups.add(group);
            box.getChildren().add(blank);
        }

        //Set up vote button on the scene
        Button voteButton = new Button("VOTE");
        voteButton.setFont(Font.font("Helvetica-Bold", 14));
        box.getChildren().add(voteButton);

        //Register the vote to the database
        voteButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                for(ToggleGroup group : buttonGroups) {
                    if(group.getSelectedToggle() != null) {
                        data.voteFor(group.getSelectedToggle().toString().split("'")[1]);
                    }
                }
                Media applause = new Media(new File("VotingSystem/src/applause.mp3").toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(applause);
                mediaPlayer.play();
                stage.setScene(ElectionDriver.getStartScene());
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }


}

