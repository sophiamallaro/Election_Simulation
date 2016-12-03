/**
 * Created by smallaro on 12/2/16.
 */
public class Position {
    private int positionid;
    private String positiontitle;
    private String availableprecincts;

    public Position(int positionid, String positiontitle, String availableprecincts) {
        this.positionid = positionid;
        this.positiontitle = positiontitle;
        this.availableprecincts = availableprecincts;
    }

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
}
