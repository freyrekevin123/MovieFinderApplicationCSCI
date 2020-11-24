import java.io.File;

public class MovieFinder {
	
	public static void main(String[] args) 
	{
		System.out.println("Welcome to the Movie Finder app! Please enjoy your time here.");
		
		//Cleans up the database
		DBCommands.nukeBothTables();
		
		//Now we create the tables.
		DBCommands.createBothTables();
		
		
		File MovieNS = new File("movie-name-score.txt");
		DataScanner fileScanner = new DataScanner(MovieNS);
		fileScanner.ScanNameAndUpload();
		File MovieCast = new File("movie-cast.txt");
		fileScanner.setFileUpload(MovieCast);
		fileScanner.scanMovieCastAndUpload();
	}
}
