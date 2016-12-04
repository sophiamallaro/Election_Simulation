import javafx.stage.Stage;

/**
 * This class controls contains method to assist
 * the VotingSystemMainController.java class with
 * handling and controlling the next scene to be
 * displayed.
 *
 * @author Kyle Anderson
 * Created by kanderson8 on 12/3/16.
 */
public class StateControl {

    //Instantiation of member variables
    private static Stage myStage;
    private static String idCode;
    private static String buttonPressed;
    private static String stateID;
    private static String precinctID;

    /**
     * Accessor method for retrieving the stage
     * @return Stage
     */
    public static Stage getMyStage() {
        return myStage;
    }

    /**
     * Update method to set the stage to stage passed in the
     * argument
     * @param myStage
     */
    public static void setMyStage(Stage myStage) {
        StateControl.myStage = myStage;
    }

    /**
     * Accessor method to retrieve the ID code
     * @return A String of the ID code
     */
    public static String getIdCode() {
        return idCode;
    }

    /**
     * Update method to change the ID code to the String passed
     * in the argument
     * @param idCode
     */
    public static void setIdCode(String idCode) {
        StateControl.idCode = idCode;
    }

    /**
     * Accessor method to retrieve the button pressed by the user
     * @return A String of the name of the button pressed
     */
    public static String getButtonPressed() {
        return buttonPressed;
    }

    /**
     * Update method to set the button pressed to the name of the button passed
     * in by the argument
     * @param buttonPressed The name of the button pressed
     */
    public static void setButtonPressed(String buttonPressed) {
        StateControl.buttonPressed = buttonPressed;
    }

    /**
     * Accessor method to retrieve the State ID in the database.
     * @return A String of the state ID
     */
    public static String getStateID() {
        return stateID;
    }

    /**
     * Update method to set or change the stateID to the String passed in
     * the argument
     * @param stateID The state ID to be set to
     */
    public static void setStateID(String stateID) {
        StateControl.stateID = stateID;
    }

    /**
     * Accessor method to retrieve the Precinct ID
     * @return  The precinct ID
     */
    public static String getPrecinctID() {
        return precinctID;
    }

    /**
     * Update method to set or change the precinct ID to the String passed
     * in the argument
     * @param precinctID The precinct ID to be set to
     */
    public static void setPrecinctID(String precinctID) {
        StateControl.precinctID = precinctID;
    }
}
