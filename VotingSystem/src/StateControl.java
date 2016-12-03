import javafx.stage.Stage;

/**
 * Created by kanderson8 on 12/3/16.
 */
public class StateControl {
    private static Stage myStage;
    private static String idCode;
    private static String buttonPressed;
    private static String stateID;
    private static String precinctID;

    public static Stage getMyStage() {
        return myStage;
    }

    public static void setMyStage(Stage myStage) {
        StateControl.myStage = myStage;
    }

    public static String getIdCode() {
        return idCode;
    }

    public static void setIdCode(String idCode) {
        StateControl.idCode = idCode;
    }

    public static String getButtonPressed() {
        return buttonPressed;
    }

    public static void setButtonPressed(String buttonPressed) {
        StateControl.buttonPressed = buttonPressed;
    }

    public static String getStateID() {
        return stateID;
    }

    public static void setStateID(String stateID) {
        StateControl.stateID = stateID;
    }

    public static String getPrecinctID() {
        return precinctID;
    }

    public static void setPrecinctID(String precinctID) {
        StateControl.precinctID = precinctID;
    }
}
