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

    public static void main(String[] args) {
        TestDB tdb = new TestDB();
        /*List<Position> candidates = tdb.getPositionsWithCandidates("0101");
        for(Position position : candidates) {
            System.out.println(position.getPositiontitle());
            for(Candidate candidate: position.getCandidates()) {
                System.out.println(candidate.getFirstName() + " " + candidate.getLastName() + ", " + candidate.getParty());
            }
            System.out.println();
        }*/
        Integer bob = tdb.getPositionID("President");

    }

    TestDB() {
        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    public void addCandidate(Candidate candidate) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO candidates" + "(firstname, lastname, party, voteCount, positionid) VALUES" + "(?,?,?,?,?)" );
            preparedStatement.setString(1, candidate.getFirstName());
            preparedStatement.setString(2, candidate.getLastName());
            preparedStatement.setString(3, candidate.getParty());
            preparedStatement.setInt(4, candidate.getVoteCount());
            preparedStatement.setInt(5, candidate.getPositionid());
            preparedStatement.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    public Integer getPositionID(String name) {
        List<State> states = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM position");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("positiontitle").equals(name)) {
                    return resultSet.getInt("positionid");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        return null;
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
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    public List<State> getStates() {
        List<State> states = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM states");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                states.add(new State(resultSet.getInt("addressid"), resultSet.getString("stateName")));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        return states;
    }

    public Integer getStateID(String name) {
        List<State> states = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM states");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("statename").equals(name)) {
                    return resultSet.getInt("addressid");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    public List<Position> getPositions(String idCode) { //Return list of positions available to a precinct
        List<Position> positions = new ArrayList<>();
        ResultSet resultSet = null;
        char[] idCodeArray = idCode.toCharArray();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM position");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                char[] codeArray = resultSet.getString("availableprecincts").toCharArray();
                if((codeArray[0] == '0') && (codeArray[1] == '0')) {
                    positions.add(new Position(resultSet.getInt("positionid"), resultSet.getString("positiontitle"), resultSet.getString("availableprecincts")));
                } else if((codeArray[0] == idCodeArray[0]) && (codeArray[1] == idCodeArray[1])) {
                    if(((codeArray[2] == '0') && (codeArray[3] == '0')) ||  ((idCodeArray[2] == codeArray[2]) && (idCodeArray[3] == codeArray[3]))){
                        positions.add(new Position(resultSet.getInt("positionid"), resultSet.getString("positiontitle"), resultSet.getString("availableprecincts")));
                    }
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        return positions;
    }

    public List<Precinct> getPrecincts(int stateid) {
        List<Precinct> precincts = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM precincts");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                if(resultSet.getInt("stateid") == stateid) {
                    precincts.add(new Precinct(resultSet.getInt("stateid"), resultSet.getString("precinctid")));
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        return precincts;
    }

    public List<Candidate> getCandidates(int findpositionID) { //Return list of positions available to a precinct
        ResultSet resultSet = null;
        List<Candidate> candidates = new ArrayList<Candidate>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM candidates");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                if(resultSet.getInt("positionID")==findpositionID) {
                    candidates.add(new Candidate(resultSet.getInt("candidateid"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("party"), resultSet.getInt("voteCount"), resultSet.getInt("positionid")));
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        return candidates;
    }

    public List<Position> getPositionsWithCandidates(String idCode) {
        List<Position> positions = new ArrayList<>();
        ResultSet resultSet = null;
        char[] idCodeArray = idCode.toCharArray();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM position");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                char[] codeArray = resultSet.getString("availableprecincts").toCharArray();
                if((codeArray[0] == '0') && (codeArray[1] == '0')) {
                    positions.add(new Position(resultSet.getInt("positionid"), resultSet.getString("positiontitle"), resultSet.getString("availableprecincts"), getCandidates(resultSet.getInt("positionid"))));
                } else if((codeArray[0] == idCodeArray[0]) && (codeArray[1] == idCodeArray[1])) {
                    if(((codeArray[2] == '0') && (codeArray[3] == '0')) ||  ((idCodeArray[2] == codeArray[2]) && (idCodeArray[3] == codeArray[3]))){
                        positions.add(new Position(resultSet.getInt("positionid"), resultSet.getString("positiontitle"), resultSet.getString("availableprecincts"), getCandidates(resultSet.getInt("positionid"))));
                    }
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        return positions;
    }

}
