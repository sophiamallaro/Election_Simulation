import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by kanderson8 on 12/1/16.
 */
public class ChatApp extends Application {

    private TextArea messages = new TextArea();


    private Parent createContent() {
        messages.setPrefHeight(550);
        TextField input = new TextField();

        VBox root = new VBox(20, messages, input);
        root.setPrefSize(600, 600);
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //primaryStage.setScene(new Scene(createContent));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
