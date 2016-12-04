import java.util.List;

/**
 *
 * Created by smallaro on 12/2/16.
 */
public class Position {
    //
    private int positionid;
    private String positiontitle;
    private String availableprecincts;
    private List<Candidate> candidates;

    /**
     *
     * @param positionid
     * @param positiontitle
     * @param availableprecincts
     */
    public Position(int positionid, String positiontitle, String availableprecincts) {
        this.positionid = positionid;
        this.positiontitle = positiontitle;
        this.availableprecincts = availableprecincts;
        this.candidates = null;
    }

    /**
     *
     * @param positionid
     * @param positiontitle
     * @param availableprecincts
     * @param candidates
     */
    public Position(int positionid, String positiontitle, String availableprecincts, List<Candidate> candidates) {
        this.positionid = positionid;
        this.positiontitle = positiontitle;
        this.availableprecincts = availableprecincts;
        this.candidates = candidates;
    }

    /**
     *
     * @return
     */
    public int getPositionid() {
        return positionid;
    }

    public void setPositionid(int positionid) {
        this.positionid = positionid;
    }

    public String getPositiontitle() {
        return positiontitle;
    }

    public void setPositiontitle(String positiontitle) {
        this.positiontitle = positiontitle;
    }

    public String getAvailableprecincts() {
        return availableprecincts;
    }

    public void setAvailableprecincts(String availableprecincts) {
        this.availableprecincts = availableprecincts;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
