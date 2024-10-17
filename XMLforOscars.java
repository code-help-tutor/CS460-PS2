WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com
/*
 * XMLforOscars
 * 
 * A class for objects that convert data about Oscars from 
 * our relational database to XML.
 * 
 * Starter code: CS 460 staff
 * Completed by: 
 *     name1 (email1)
 *     name2 (email2)
 */

import java.sql.*;      // needed for the JDBC-related classes
import java.io.*;       // needed for file-related classes

public class XMLforOscars {
    private Connection db;   // a connection to the database
    
    /*
     * XMLforOscars constructor - takes the name of a SQLite file for
     * a database like the one from PS 1, and creates an object that 
     * can be used to convert data about the Oscars in that database to XML.
     */
    public XMLforOscars(String dbFilename) throws SQLException {
        this.db = DriverManager.getConnection("jdbc:sqlite:" + dbFilename);
    }

    /*
     * simpleElem - a private helper method takes the name and value of 
     * a simple XML element and returns a string representation of that element
     */
    private String simpleElem(String name, String value) {
        String elem = "<" + name + ">";
        elem += value;
        elem += "</" + name + ">";
        return elem;
    }

    /*
     * resultsFor - private helper method that takes a string representing 
     * a SQL query for the movie database and returns a ResultSet object 
     * that represents the results of the query (if any).
     */
    private ResultSet resultsFor(String query) throws SQLException {
        Statement stmt = this.db.createStatement();
        ResultSet results = stmt.executeQuery(query);
        return results;
    }

    /*
     * personIdFor - takes the name of a person and returns the id number of
     * that person in the database as a string. If the person is not in the
     * database, it returns an empty string.
     */
    public String personIdFor(String name) throws SQLException {
        String query = "SELECT id FROM Person WHERE name = '" + name + "';";
        ResultSet results = resultsFor(query);

        if (results.next()) {
            String id = results.getString(1);
            return id;
        } else {
            return "";
        }
    }

    /*
     * movieIdFor - takes the name of a movie and returns the id number 
     * of that movie in the database as a string. If the movie is 
     * not in the database, it returns an empty string.
     */
    public String movieIdFor(String name) throws SQLException {
        String query = "SELECT id FROM Movie WHERE name = '" + name + "';\n";
        ResultSet results = resultsFor(query);

        if (results.next()) {
            String id = results.getString(1);
            return id;
        } else {
            return "";
        }
    }

    /*
     * movieElemFor - takes a string representing the id number of a movie
     * and returns an XML element with an id attribute whose value is movieID
     * and whose text value is the name of the movie. If there is no movie 
     * with the specified id number, the method returns an empty string.
     */
    public String movieElemFor(String movieID) throws SQLException {
        
         // replace this return statement with your implementation
         return "";
    }
    
    /*
     * personElemFor - takes a string representing the id number of a person
     * and returns a complex XML element with an attribute called id whose 
     * value is personID, and with nested child elements for the person's
     * name and (if it's not null) their dob. If there is no person with 
     * the specified id number, the method returns an empty string.
     */
    public String personElemFor(String personID) throws SQLException {
        
        // replace this return statement with your implementation
        return "";
    }
    
    /*
     * awardElemFor - takes strings representing an Oscar's type, person_id,
     * and movie_id and returns a single complex XML element of type 
     * "award" that contains nested child elements for the type, person
     * and movie.
     */
    public String awardElemFor(String type, String personId, String movieId) 
      throws SQLException {
     
        // replace this return statement with your implementation
        return "";
    }

    /*
     * oscarsForYear - takes an String representing a year and returns
     * a single complex XML element named "oscars_for_year" that contains
     * nested child elements for all of the Oscars associated with that 
     * year in the database. If there are no awards for that year in
     * the database, the method returns an empty string.
     */
    public String oscarsForYear(String year) throws SQLException {
        
        // replace this return statement with your implementation
        return "";
    }

    /*
     * createFile - creates a text file with the specified filename containing 
     * an XML representation of the entire Oscar table.
     */
    public void createFile(String filename) 
      throws FileNotFoundException, SQLException 
    {
        PrintStream outfile = new PrintStream(filename);    
        outfile.println("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>");
        outfile.println("<oscars>");
        
        // Use a query to get all distinct years from the Oscar table.
        ResultSet results = resultsFor("SELECT DISTINCT year FROM Oscar ORDER BY year DESC;");
        
        // Process one year at a time, creating its 
        // XML element and writing it to the output file.
        while (results.next()) {
            String year = results.getString(1);
            outfile.println(oscarsForYear(year));
        }
        
        outfile.println("</oscars>");
        
        // Close the connection to the output file.
        outfile.close();
        System.out.println(filename + " has been written.");
    }
    
    /*
     * closeDB - closes the connection to the database that was opened when 
     * the XMLforPeople object was constructed
     */
    public void closeDB() throws SQLException {
        this.db.close();
    }
    
    public static void main(String[] args) 
        throws ClassNotFoundException, SQLException, FileNotFoundException
    {
        XMLforOscars xml = new XMLforOscars("movie.sqlite");
        xml.createFile("oscars.xml");
        xml.closeDB();
    }
}
