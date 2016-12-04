/**
 * This Precinct class stores the details of the precinct and
 * contains methods to manipulate the member variables. The state ID
 * stores the state ID of the state that corresponds to the
 * precinct and the id member variable stores the precinct ID for
 * the database record.
 *
 * Created by smallaro on 12/2/16.
 */
public class Precinct {
    private int stateid;
    private String id;

    public Precinct(int stateid, String id) {
        this.stateid = stateid;
        this.id = id;
    }

    public int getStateid() {
        return stateid;
    }

    public void setStateid(int stateid) {
        this.stateid = stateid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
