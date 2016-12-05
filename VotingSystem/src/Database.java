/**
 * This class contains all functions to deal with the database
 * @author Sophia Mallaro
 * @see Database
 */
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:postgresql://s-l112.engr.uiowa.edu:5432/postgres";
    private static final String USERNAME = "student5";
    private static final String PASSWORD = "team08";

    private Connection connection;
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatement2;

    /**
     * Constructor that establishes a connection with the databse
     */
    Database() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Takes in a Candidate object and adds that information to the candidate table
     * in the database
     * @param candidate Candidate object to be added
     */
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
            System.exit(-1); //hello
        }
    }

    /**
     * Adds a new precinct to the precinct table
     * @param stateid State to add to
     * @param precinctid Precinct to add
     */
    public void addPrecinct(String stateid, String precinctid) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO precincts" + "(stateid, precinctid) VALUES" + "(?,?)" );
            preparedStatement.setInt(1, Integer.parseInt(stateid));
            preparedStatement.setString(2, precinctid);
            preparedStatement.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Adds a new office position
     * @param positionTitle Name of position
     * @param availablePrecincts Code for available precincts
     */
    public void addPosition(String positionTitle, String availablePrecincts) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO position" + "(positiontitle, availableprecincts) VALUES" + "(?,?)" );
            preparedStatement.setString(1, positionTitle);
            preparedStatement.setString(2, availablePrecincts);
            preparedStatement.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Gets the id associated with the desired office position
     * @param name Name of position
     * @param id precinct id
     * @return Integer id of position
     */

    public Integer getPositionID(String name, String id) {
        List<State> states = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM position");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("positiontitle").equals(name)) {
                    String code = resultSet.getString("availableprecincts");
                    if(code.equals(id) || code.equals("0000") || (code.charAt(0)==id.charAt(0) && code.charAt(1)==id.charAt(1) && code.charAt(2)=='0' && code.charAt(3)=='0')) {
                        return resultSet.getInt("positionid");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    /**
     * Finds candidates running for specific positions
     * @param positionID ID of position
     */
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

    /**
     * Returns all known states
     * @return List of states
     */
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

    /**
     * Returns the ID of state
     * @param name State name
     * @return Integer ID
     */
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

    /**
     * List of positions available in the precinct
     * @param idCode Precinct code
     * @return List of positions
     */
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

    /**
     * Gets list of ids of positions available
     * @param idCode precinct code
     * @return Integer list of available positions
     */
    public List<Integer> getPositionIDList(String idCode) { //Return list of positions available to a precinct
        List<Integer> positions = new ArrayList<>();
        ResultSet resultSet = null;
        char[] idCodeArray = idCode.toCharArray();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM position");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                char[] codeArray = resultSet.getString("availableprecincts").toCharArray();
                if((codeArray[0] == '0') && (codeArray[1] == '0')) {
                    positions.add(resultSet.getInt("positionid"));
                } else if((codeArray[0] == idCodeArray[0]) && (codeArray[1] == idCodeArray[1])) {
                    if(((codeArray[2] == '0') && (codeArray[3] == '0')) ||  ((idCodeArray[2] == codeArray[2]) && (idCodeArray[3] == codeArray[3]))){
                        positions.add(resultSet.getInt("positionid"));
                    }
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        return positions;
    }

    /**
     * Gets precincts in the given state
     * @param stateid ID code of state
     * @return List of precincts
     */

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

    /**
     * Returns a list of Candidates running for a specific position
     * @param findpositionID position id
     * @return List of candidates
     */
    public List<Candidate> getCandidates(int findpositionID) { //Return list of candidates
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

    /**
     *  Returns list of positions available. Each position has a list of candidates running for the position.
     * @param idCode precinct identification code
     * @return List of positions
     */
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

    /**
     * Votes for a candidate
     * @param identifier candidate name and party
     */
    public void voteFor(String identifier) { //vote for a candidate
        String[] identities = identifier.replace(',', ' ').split("\\s+");
        try {
            preparedStatement = connection.prepareStatement("UPDATE candidates SET voteCount = voteCount+1 where firstname = ? and lastname = ?");
            preparedStatement.setString(1, identities[0]);
            preparedStatement.setString(2, identities[1]);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Returns a bar chart of results data
     * @param positionToLoad ID of position of desired results
     * @return BarChart
     */
    public BarChart<String, Number> loadChart(int positionToLoad) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        List<Candidate> candidates = new ArrayList<>();
            List<Position> offices = getPositionsWithCandidates(StateControl.getIdCode());
            for(Position position : offices) {
                if(position.getPositionid() == positionToLoad) {
                    candidates = position.getCandidates();
                    for(Candidate candidate : candidates) {
                        String name = candidate.getFirstName() + " " + candidate.getLastName();
                        Integer votes = candidate.getVoteCount();
                        //System.out.println("Name: " + name + ", Votes:  " + votes);
                        series.getData().add(new XYChart.Data<>(name, votes));
                    }
                }
            }
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Candidates");
            yAxis.setLabel("Votes");
            BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);
            bc.setTitle("Election Results");
            bc.getData().add(series);
            return bc;
    }

    /**
     * Updates bar chart to add data
     * @param chartToUpdate Barchart to update
     * @param positionToLoad position of id to add
     */
    public void updateChart(BarChart<String, Number> chartToUpdate, int positionToLoad) {
        XYChart.Series<String, Number> newSeries = new XYChart.Series<>();
        List<Candidate> candidates = new ArrayList<>();
        List<Position> offices = getPositionsWithCandidates(StateControl.getIdCode());
        for(Position position : offices) {
            //System.out.println("Position ID is " + position.getPositionid());
            if(position.getPositionid() == positionToLoad) {
                //System.out.println("Found matching position!");
                candidates = position.getCandidates();
                for(Candidate candidate : candidates) {
                    String name = candidate.getFirstName() + " " + candidate.getLastName();
                    Integer votes = candidate.getVoteCount();
                    //System.out.println("Name: " + name + ", Votes:  " + votes);
                    newSeries.getData().add(new XYChart.Data<>(name, votes));
                }
            }
        }
        chartToUpdate.getData().add(newSeries);
    }

    /**
     * Gets list of all candidate IDs
     * @return ArrayList of all candidate IDs
     */
    public ArrayList<Integer> getCandidateIDList() {
        ResultSet resultSet = null;
        ArrayList<Integer> candidateids = new ArrayList<Integer>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM candidates");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                candidateids.add(resultSet.getInt("candidateid"));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        return candidateids;
    }

    /**
     * Votes for a candidate with a specified ID
     * @param ids ID of candidate
     */
    public void voteID(int[] ids) { //vote for a candidate
        try {
            preparedStatement = connection.prepareStatement("UPDATE candidates SET voteCount = 0");
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement("UPDATE candidates SET voteCount = voteCount+1 where candidateid = ?");
            for(int i=0; i<ids.length; i++) {
                preparedStatement.setInt(1, ids[i]);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Makes html file and displays
     * @param ids List of ids of positions to display
     */
    public void makeCSV(List<Integer> ids) {
        ResultSet resultSet = null;
        try {
            File file = new File(System.getProperty("user.dir") + "/VotingSystem/src/results.html");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            try {
                writer.write("<html>\n<head>\n\t<title>\"Election\"</title>\n\t" +
                        "<h1>\"Election Results!!!!\"</h1>\n</head>\n");
                writer.flush();
                for(int id : ids) {
                    writer.write(writeTable(id));
                    writer.flush();
                }
                writer.write("\n</body>\n</html>");
                writer.flush();
                fileWriter.close();
                writer.close();
                Desktop.getDesktop().browse(file.toURI());
            } catch(IOException ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
        } catch(IOException ex) {

        }
    }

    /**
     * Writes html table for a specific position id
     * @param id position id
     * @return
     */
    public String writeTable(int id) {
        ResultSet resultSet = null;
        String toReturn = "";
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM candidates");
            resultSet = preparedStatement.executeQuery();
            toReturn = "<h1>" + getNameFromID(id) + "</h1>\n\n<body>\n<table border = \"1\" style = \"...\">\n\t";
            toReturn += ("<tr>\n\t\t<th>FirstName</th><th>LastName</th>" +
                    "<th>Party</th><th>Votes</th></tr>\n");
            //writer.flush();
            while(resultSet.next()) {
                if(resultSet.getInt("positionid")==id) {
                    toReturn += ("<tr>\n<td>" + resultSet.getString("firstName") + "</td>\n<td>" + resultSet.getString("lastName") + "</td>\n<td>" + resultSet.getString("party") +
                            "</td>\n<td>" + resultSet.getInt("voteCount") + "</td>\n");
                    //writer.flush();
                }
            }
            //resultSet.first();
            toReturn += ("\t</table>\n");
        } catch(SQLException ex) {

        }
        return toReturn;
    }

    /**
     * Gets position name from ID
     * @param id position name from id
     * @return
     */
    public String getNameFromID(int id) {
        String toReturn = "";
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM position");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                if(resultSet.getInt("positionid")==id) {
                    toReturn = resultSet.getString("positiontitle");
                }
            }
        } catch(SQLException ex) {


        }
        return toReturn;
    }

    public void clearVotes() {
        try {
            preparedStatement = connection.prepareStatement("UPDATE candidates SET voteCount = 0");
            preparedStatement.execute();
        } catch(SQLException ex) {
            System.exit(-1);
        }

    }

    /**
     * Closes connection
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}
