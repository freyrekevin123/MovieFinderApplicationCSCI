import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//************************************************************************************
//DBCommands.java		Created By: Larry Gaitan-Rodriguez	Date: 11/24/2020
//
//All the used database commands in a central location. Will be called by methods.
//************************************************************************************

public class DBCommands {
	public DBCommands() {}
	
	
	// Used to drop the movie_cast table in the DB
	public static void nukeMovieCast() 
	{
		Connection connect = DBConnection.connectToDB();
		try
		{
			String nukeMovieCast = "DROP TABLE movie_cast";
			PreparedStatement tableNuke = connect.prepareStatement(nukeMovieCast);
			tableNuke.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("Critical error in nuking movie_cast table. Table may not exist.");
			e.printStackTrace();
		}
	}
	
	//Used to drop movie_name_score table.
	public static void nukeMovieName() 
	{
		Connection connect = DBConnection.connectToDB();
		try
		{
			String nukeMovieName = "DROP TABLE movie_name_score";
			PreparedStatement tableNuke = connect.prepareStatement(nukeMovieName);
			tableNuke.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("Critical error in nuking movie_name_score table. Table may not exist");
			e.printStackTrace();
		}
	}
	
	//combined both nuke methods.
	public static void nukeBothTables()
	{
		nukeMovieCast();
		nukeMovieName();
	}
	
	
	//The following methods are for creating the required tables for the program.
	public static void createMovieCastTable() 
	{
		Connection connect = DBConnection.connectToDB();
		try
		{
			String movieCastTableCreationSQL = "CREATE TABLE movie_cast (movieID INTEGER, castID INTEGER, cname CHAR(255), PRIMARY KEY (movieID, castID),"
					+ "FOREIGN KEY (movieID) REFERENCES movie_name_score(movieID));";
			PreparedStatement prepStatement2 = connect.prepareStatement(movieCastTableCreationSQL);
			prepStatement2.executeUpdate(movieCastTableCreationSQL);
		}
		catch(SQLException e)
		{
			System.out.println("Critical error in creating move_cast table. Table may already exist.");
			e.printStackTrace();
		}
	}
	
	public static void createMovieNameTable() 
	{
		Connection connect = DBConnection.connectToDB();
		try
		{
			String movieNameScoreTableCreationSQL = "CREATE TABLE movie_name_score(movieID Integer, mname CHAR(255), mscore Integer, PRIMARY KEY (movieID));";
			PreparedStatement prepStatement = connect.prepareStatement(movieNameScoreTableCreationSQL);
			prepStatement.executeUpdate();
			System.out.println("Created the movie_name_score table.");

		}
		catch(SQLException e)
		{
			System.out.println("Critical error in creating move_name_score table. Table may already exist.");
			e.printStackTrace();
		}
	}
	
	public static void createBothTables()
	{
		createMovieNameTable();
		createMovieCastTable();
	}
	
}
