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
	
	//Now to scan in movie-name-score
	try {
		Scanner movieNameReader = new Scanner(new FileInputStream("movie-name-score.txt"));
		
		
		//Goes thru the whole file to process the data and upload it
		int mnCounter = 0;
		while(movieNameReader.hasNextLine())
		{
			String currentLine = movieNameReader.nextLine();
			
			
			//Thank you very much stack overflow: https://stackoverflow.com/questions/18893390/splitting-on-comma-outside-quotes
			String[] delimitedLine = currentLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			//Following the format of the movie-cast, first two data points will
			//be integers and the last one will be a name.
			int movieID = Integer.parseInt(delimitedLine[0]);
			String movieName = delimitedLine[1];
			int score = Integer.parseInt(delimitedLine[2]);
			
			PreparedStatement postNM = connect.prepareStatement("INSERT INTO movie_name_score (movieID, mname, mscore ) "
					+ "VALUES(?,?,?)");
			postNM.setInt(1, movieID);
			postNM.setString(2, movieName);
			postNM.setInt(3, score);
			
			postNM.executeUpdate();
			mnCounter++;
		}
		movieNameReader.close();
		System.out.println("inserted "+mnCounter+" lines into movie_name_cast");
	} catch (FileNotFoundException e) {
		System.out.println("File was not found. Please make sure its in the correct folder or correctly named.");
		e.printStackTrace();
	}

	//Now to scan in movie-cast 
	try {
		Scanner castReader = new Scanner(new FileInputStream("movie-cast.txt"));
		
		
		int castCounter = 0;
		while(castReader.hasNextLine())
		{
			String currentLine = castReader.nextLine();
			String[] delimitedLine = currentLine.split(",");
			//Following the format of the movie-cast, first two data points will
			//be integers and the last one will be a name.
			int movieID = Integer.parseInt(delimitedLine[0]);
			int castID = Integer.parseInt(delimitedLine[1]);
			String actorName = delimitedLine[2];
			PreparedStatement postNM = connect.prepareStatement("INSERT INTO movie_cast (movieID, castID, cname) "
					+ "VALUES(?,?,?)");
			postNM.setInt(1, movieID);
			postNM.setInt(2, castID);
			postNM.setString(3, actorName);
			postNM.executeUpdate();
			castCounter++;
		}
		castReader.close();
		System.out.println("inserted "+castCounter+" lines into movie_cast");
		
	} catch (FileNotFoundException e) {
		System.out.println("File was not found. Please make sure its in the correct folder or correctly named.");
		e.printStackTrace();
	}

	}
}
