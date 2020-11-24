import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.sql.*;

public class MovieFinder {

	public static void main(String[] args) throws SQLException {
	
	System.out.println("Welcome to the Movie Finder app! Please enjoy your time here.");
	Connection connect = DBConnection.connectToDB();		
	
	//Cleans up the database
	String nukeMovieName = "DROP TABLE movie_name_score";
	String nukeMovieCast = "DROP TABLE movie_cast";
	
	System.out.println("Cleaning up the database of previous work...");
	PreparedStatement tableNuke = connect.prepareStatement(nukeMovieCast);
	tableNuke.executeUpdate();
	System.out.println("Database cleared of all tables.");
	tableNuke = connect.prepareStatement(nukeMovieName);
	tableNuke.executeUpdate();
	

	
	//Successfully connected to the database, now we create the movie_name_score table

	String movieNameScoreTableCreationSQL = "CREATE TABLE movie_name_score(movieID Integer, mname CHAR(255), mscore Integer, PRIMARY KEY (MovieID));";
	PreparedStatement prepStatement = connect.prepareStatement(movieNameScoreTableCreationSQL);
	prepStatement.executeUpdate();
	System.out.println("Created the movie_name_score table.");
	
	//Movie-cast Table creation
	String movieCastTableCreationSQL = "CREATE TABLE movie_cast (movieID INTEGER, castID INTEGER, cname CHAR(255), PRIMARY KEY (movieID, castID),"
			+ "FOREIGN KEY (MovieID) REFERENCES movie_name_score(MovieID));";
	PreparedStatement prepStatement2 = connect.prepareStatement(movieCastTableCreationSQL);
	prepStatement2.executeUpdate(movieCastTableCreationSQL);
	System.out.println("Created the movie_name table.");
	
	File MovieNS = new File("movie-name-score.txt");
	DataScanner fileScanner = new DataScanner(MovieNS);
	fileScanner.ScanNameAndUpload();
	File MovieCast = new File("movie-cast.txt");
	fileScanner.setFileUpload(MovieCast);
	fileScanner.scanMovieCastAndUpload();
	
	
	}
}
