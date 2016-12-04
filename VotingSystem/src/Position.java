import java.util.List;

/**
 * This class is a class to store all details
 * regarding a position. This class contains four
 * member variables.
 *
 * @author Sophia Mallaro
 */
public class Position {
    //Instantiation of the member variables
    private int positionid;
    private String positiontitle;
    private String availableprecincts;
    private List<Candidate> candidates;

    /**
     * Constructor with a parameter of two Strings and one
     * integer.
     *
     * @param positionid position ID
     * @param positiontitle position's title
     * @param availableprecincts precincts where the position is available
     */
    public Position(int positionid, String positiontitle, String availableprecincts) {
        this.positionid = positionid;
        this.positiontitle = positiontitle;
        this.availableprecincts = availableprecincts;
        this.candidates = null;
    }

    /**
     * Constructor with two String parameters, one integer, and
     * a list.
     *
     * @param positionid the position ID
     * @param positiontitle the position's title
     * @param availableprecincts the precincts where the position is available
     * @param candidates a list of candidates for the position
     */
    public Position(int positionid, String positiontitle, String availableprecincts, List<Candidate> candidates) {
        this.positionid = positionid;
        this.positiontitle = positiontitle;
        this.availableprecincts = availableprecincts;
        this.candidates = candidates;
    }

    /**
     * Accessor method to retrieve the position ID
     * in the database
     *
     * @return position ID
     */
    public int getPositionid() {
        return positionid;
    }

    /**
     * Update method to modify the position ID
     * associated in the database.
     *
     * @param positionid
     */
    public void setPositionid(int positionid) {
        this.positionid = positionid;
    }

    /**
     * Accessor method to get the position title
     * in the database
     * @return position title
     */
    public String getPositiontitle() {
        return positiontitle;
    }

    /**
     * Update method to modify the position title o
     * @param positiontitle
     */
    public void setPositiontitle(String positiontitle) {
        this.positiontitle = positiontitle;
    }

    /**
     * Accessor method to retrieve the available precincts for
     * the position
     *
     * @return the precincts for the position
     */
    public String getAvailableprecincts() {
        return availableprecincts;
    }

    /**
     * Update method to modify the available precincts
     * for the position
     *
     * @param availableprecincts
     */
    public void setAvailableprecincts(String availableprecincts) {
        this.availableprecincts = availableprecincts;
    }

    /**
     * An accessor method to retrieve a list of candidates
     * running for the position
     *
     * @return a list of candidates
     */
    public List<Candidate> getCandidates() {
        return candidates;
    }

    /**
     * An update method to modify the list of candidates
     * running for the position
     *
     * @param candidates
     */
    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
