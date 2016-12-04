/**
 * A candidate class that stores the details of the candidate
 * This class fulfills the requirement of keeping track each of the
 * candidate's information.
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
     * Constructor with the parameters of three Strings and one integer.
     *
     * @param firstName The candidate's first name
     * @param lastName The candidate's last name
     * @param party The candidate's party
     * @param positionid The candidate's position ID in the database
     */
    public Candidate(String firstName, String lastName, String party, int positionid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.party = party;
        this.voteCount = 0;
        this.positionid = positionid;
    }

    /**
     * Constructor with the parameter of three integers and three Strings
     *
     * @param candidateid The candidate's ID in the database
     * @param firstName The candidate's first name
     * @param lastName The candidate's last name
     * @param party The candidate's party
     * @param voteCount The candidate's vote count
     * @param positionid The candidate's position in the database
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
     * An accessor method to get the candidate's ID
     * @return the candidate's ID in the database
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
     * An accessor method to get the last name of the candidate
     * @return the last name of the candidate
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * An update method to set the last name of the candidate
     * @param lastName candidate's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * An accessor method to get the candidate's party
     * @return the candidate's party affiliation
     */
    public String getParty() {
        return party;
    }

    /**
     * An update method to set the candidate's party
     * @param party candidate's party
     */
    public void setParty(String party) {
        this.party = party;
    }

    /**
     * An accessor method to get the number of votes for the candidate
     * @return the number of votes
     */
    public int getVoteCount() {
        return voteCount;
    }

    /**
     * An update method to set the number of votes
     * @param voteCount the number of votes
     */
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    /**
     * An accessor method to get the position ID of the candidate in the database
     * @return the candidate's position ID
     */
    public int getPositionid() {
        return positionid;
    }

    /**
     * An update method to set the position ID of the candidate in the database
     * @param positionid
     */
    public void setPositionid(int positionid) {
        this.positionid = positionid;
    }
}
