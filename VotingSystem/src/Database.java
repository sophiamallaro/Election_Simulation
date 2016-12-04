/**
 * Created by Sophia on 12/2/2016.
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

    public static void main(String[] args) {
        Database base = new Database();
        base.makeCSV(1);
    }

    Database() {
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
            System.exit(-1); //hello
        }
    }

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

    public void voteID(int[] ids) { //vote for a candidate
        try {
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

    public void makeCSV(int id) {
        ResultSet resultSet = null;
        try {
            File file = new File(System.getProperty("user.dir") + "/VotingSystem/src/results.html");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            try {
                preparedStatement = connection.prepareStatement("SELECT * FROM candidates");
                resultSet = preparedStatement.executeQuery();
                writer.write("<html>\n<head>\n\t<title>\"Election\"</title>\n\t" +
                        "<h1>\"Election!!!!\"</h1>\n</head>\n" +
                        "<body>\n<table border = \"1\" style = \"...\">\n\t");
                writer.flush();
                writer.write("<tr>\n\t\t<th>FirstName</th><th>LastName</th>" +
                        "<th>Party</th><th>Votes</th></tr>\n");
                writer.flush();
                while(resultSet.next()) {
                    if(resultSet.getInt("positionid")==id) {
                        writer.write("<tr>\n<td>" + resultSet.getString("firstName") + "</td>\n<td>" + resultSet.getString("lastName") + "</td>\n<td>" + resultSet.getString("party") +
                                "</td>\n<td>" + resultSet.getInt("voteCount") + "</td>\n");
                        writer.flush();
                    }
                }
                writer.write("\t\t</table>\n\t</body>\n</html>");
                writer.flush();
                fileWriter.close();
                writer.close();
                Desktop.getDesktop().browse(file.toURI());
            } catch(SQLException ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
        } catch(IOException ex) {

        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }



}
