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
}
