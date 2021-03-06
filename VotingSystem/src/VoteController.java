import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
 * This class inherits the Application class.
 *
 * @author Sophia Mallaro
 * @see Database
 */

public class VoteController extends Application {

    @FXML
    private static final Database data = new Database();
    private List<ToggleGroup> buttonGroups;

    //default constructor
    VoteController() {
    }

    /**
     * This method generates the ballot. The method will first set
     * up the scene. Then, the method looks up for the candidates list and
     * their position in the database based on the state and precinct selected.
     *
     * @param stage
     */
    public void start(Stage stage) {
        //Set up scene
        VBox box = new VBox();
        Scene scene = new Scene(box,400,500);
        stage.setScene(scene);
        buttonGroups = new ArrayList<ToggleGroup>();

        //Set title on the stage
        Label title = new Label("OFFICAL GENERAL ELECTION BALLOT");
        title.setFont(Font.font("Helvetica-Bold", 20));
        box.getChildren().add(title);

        //Search through the database for the candidates and list them on the scene
        List<Position> candidates = data.getPositionsWithCandidates(StateAndPrecinctController.getIdCode());
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
                //Sounds source: http://soundbible.com/tags-crickets.html
                try {
                    Media applause = new Media(new File("VotingSystem/src/applause.mp3").toURI().toString());
                    Media crickets = new Media(new File("VotingSystem/src/crickets.mp3").toURI().toString());
                    MediaPlayer badVote = new MediaPlayer(crickets);
                    MediaPlayer goodVote = new MediaPlayer(applause);
                    if (buttonGroups.get(0).getSelectedToggle().toString().split("'")[1].equals("Donald Trump, Republican"))
                        badVote.play();
                    else
                        goodVote.play();
                } catch(MediaException ex) {
                } finally {
                    stage.setScene(ElectionDriver.getStartScene());
                }
            }
        });

    }

}

