/**
 * A candidate class that stores the details of the candidate
 *
 * @author Sophia Mallaro
 * Created by Sophia on 12/2/2016.
 */

public class Candidate {

    //Instantiation of variables for candidate's details
    private int candidateid;
    private String firstName;
    private String lastName;
    private String party;
    private int voteCount;
    private int positionid;

    //default constructor with the vote count value set to 0
    public Candidate()  {
        this.voteCount = 0;
    }

    /**
     *
     *
     * @param firstName
     * @param lastName
     * @param party
     * @param positionid
     */
    public Candidate(String firstName, String lastName, String party, int positionid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.party = party;
        this.voteCount = 0;
        this.positionid = positionid;
    }

    /**
     *
     * @param candidateid
     * @param firstName
     * @param lastName
     * @param party
     * @param voteCount
     * @param positionid
     */
    public Candidate(int candidateid, String firstName, String lastName, String party, int voteCount, int positionid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.party = party;
        this.voteCount = 0;
        this.positionid = positionid;
        this.voteCount = voteCount;
        this.candidateid = candidateid;
    }

    /**
     *
     * @return
     */
    public int getCandidateid() {
        return candidateid;
    }

    //public void setCandidateid(int candidateid) {
        //this.candidateid = candidateid;
    //}

    /**
     * An accessor method to get the first name of the candidate
     * @return the first name of the candidate
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * An update method to set the first name of the candidate
     * @param firstName the first name of the candidate
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getParty() {
        return party;
    }

    /**
     *
     * @param party
     */
    public void setParty(String party) {
        this.party = party;
    }

    /**
     *
     * @return
     */
    public int getVoteCount() {
        return voteCount;
    }

    /**
     *
     * @param voteCount
     */
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    /**
     *
     * @return
     */
    public int getPositionid() {
        return positionid;
    }

    /**
     *
     * @param positionid
     */
    public void setPositionid(int positionid) {
        this.positionid = positionid;
    }
}
