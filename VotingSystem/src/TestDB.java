/**
 * Created by Sophia on 12/2/2016.
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDB {
    private static final String URL = "jdbc:postgresql://s-l112.engr.uiowa.edu:5432/postgres";
    private static final String USERNAME = "student5";
    private static final String PASSWORD = "team08";

    private Connection connection;
    private PreparedStatement preparedStatement;

    public static void main(String args[]) {
        TestDB tdb = new TestDB();
        tdb.findCandidates(1);
    }

    TestDB() {
        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch(SQLException ex) {
            System.out.println("Uh oh. Something went wrong.");
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    public void addCandidate(Candidate candidate) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO candidates" + "(candidateid, firstname, lastname, party, voteCount, positionid) VALUES" + "(?,?,?,?,?,?)" );
            preparedStatement.setInt(1,candidate.getCandidateid());
            preparedStatement.setString(2, candidate.getFirstName());
            preparedStatement.setString(3, candidate.getLastName());
            preparedStatement.setString(4, candidate.getParty());
            preparedStatement.setInt(5, candidate.getVoteCount());
            preparedStatement.setInt(6, candidate.getPositionid());
            preparedStatement.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(0);
        } //hi
    }

    public void findCandidates(int positionID) {
        List<Candidate> candidates = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM candidates");
            resultSet = preparedStatement.executeQuery();
            Candidate test;
            while(resultSet.next()) {
                if(resultSet.getInt("positionid") == positionID) {
                    System.out.println(resultSet.getString("firstName") + " " + resultSet.getString("lastName") );
                }
            }
        } catch(SQLException ex) {
            System.out.println("Something went wrong");
        }
    }

}
