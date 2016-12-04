/**
 * The State class is a class that stores the details
 * of a state. The member variables of this class are
 * id of type integer which is the state ID in the database
 * and stateName of type String which is the state's name.
 *
 * @author Sophia Mallaro
 * Created by smallaro on 12/2/16.
 *
 */
public class State {
    //Instantiation of variables
    private int id;
    private String stateName;

    /**
     * Constructor with two parameters: an integer and a String
     * @param id state ID in the database
     * @param stateName The State's Name
     */
    public State(int id, String stateName) {
        this.id = id;
        this.stateName = stateName;
    }

    /**
     * Accessor method that returns the state ID in the database
     * @return the state ID
     */
    public int getId() {
        return id;
    }

    /**
     * Update method to set the state ID to the integer passed in
     * the argument
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Accessor method that returns the state's name
     * @return the state's name
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * Update method that set the state name to the String passed in
     * the argument
     * @param stateName
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * Overriden method of converting the state name to
     * a String.
     * @return A String of the state's name.
     */
    @Override
    public String toString() {
        return stateName;
    }
}
