/**
 * This Precinct class stores the details of the precinct and
 * contains methods to manipulate the member variables. The state ID
 * stores the state ID of the state that corresponds to the
 * precinct and the id member variable stores the precinct ID for
 * the database record.
 *
 * @author Sophia Mallaro
 * Created by smallaro on 12/2/16.
 */
public class Precinct {

    //Instantiation of member variables
    private int stateid;
    private String id;

    /**
     * Constructor with two parameters.
     * The constructor will assign the stateid and
     * id member variables to the arguments passed
     *
     * @param stateid the state ID of the state the precinct is in
     * @param id the precinct ID
     */
    public Precinct(int stateid, String id) {
        this.stateid = stateid;
        this.id = id;
    }

    /**
     * Accessor method that returns the state ID of the state
     * the precinct is in
     *
     * @return the stateID of the precinct's state
     */
    public int getStateid() {
        return stateid;
    }

    /**
     * Update method modify the state ID to the integer passed in
     * by the argument
     * @param stateid
     */
    public void setStateid(int stateid) {
        this.stateid = stateid;
    }

    /**
     * Accessor method to retrieve the precinct ID
     * @return A String of the precinct ID
     */
    public String getId() {
        return id;
    }

    /**
     * Update method to modify the id member variable to the
     * String passed in the argument
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
}
