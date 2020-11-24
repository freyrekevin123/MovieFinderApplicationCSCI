import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//************************************************************************************
// ReadProperties.java		Created By: Larry Gaitan-Rodriguez	Date: 11/24/2020
//
// Used to import data from db.properties and return the data using getter methods.
//************************************************************************************

public class ReadDBProperties {

	
	//Creates the properties Object to read from the dbConfig
	private static Properties dbProp = new Properties();
	
	//Pulls the config data from db.properties
	private static void pullDBConfigData() {
		try
		{
			File file = new File("resources/db.properties");
			FileInputStream fileInput = new FileInputStream(file);
			dbProp.load(fileInput);
			fileInput.close();
		}
		catch(FileNotFoundException fileE)
		{
			fileE.printStackTrace();
		}
		catch(IOException IOE)
		{
			IOE.printStackTrace();
		}
		
	}
	
	
	//Following methods return property values
	public static String getURL()
	{
		pullDBConfigData();
		return dbProp.getProperty("url");
	}
	public static String getUsername()
	{
		pullDBConfigData();
		return dbProp.getProperty("username");
	}
	public static String getPassword()
	{
		pullDBConfigData();
		return dbProp.getProperty("password");
	}
}
