/**
 * Created by Sophia on 12/2/2016.
 */
public class Candidate {
    private int candidateid;
    private String firstName;
    private String lastName;
    private String party;
    private int voteCount;
    private int positionid;

    public Candidate(int candidateid, String firstName, String lastName, String party, int positionid) {
        this.candidateid = candidateid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.party = party;
        this.voteCount = 0;
        this.positionid = positionid;
    }

    public int getCandidateid() {
        return candidateid;
    }

    public void setCandidateid(int candidateid) {
        this.candidateid = candidateid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getPositionid() {
        return positionid;
    }

    public void setPositionid(int positionid) {
        this.positionid = positionid;
    }
}
