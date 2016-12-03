import java.util.List;

/**
 * Created by smallaro on 12/3/16.
 */
public class BallotGenerator {
    private String precinctCode;
    private final TestDB data = new TestDB();
    private List<Position> offices;

    BallotGenerator(String precinctCode) {
        this.precinctCode = precinctCode;
        offices = data.getPositionsWithCandidates(precinctCode);
    }




}
