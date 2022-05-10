package co.dodo.dungeons.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource
{
	private static DataSource dataSource = new DataSource();
	private DataSource() {};
	
	private Connection conn;
	private String driver;
	private String url;
	private String user;
	private String pw;
	
	public static DataSource getInstance()
	{
		return dataSource;
	}
	
	public Connection getConnection()
	{
		conf();
		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	
	private void conf()
	{
		Properties properties = new Properties();
		String resource = getClass().getResource("/dungeondb.properties").getPath();
		try
		{
			properties.load(new FileInputStream(resource));
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			pw = properties.getProperty("pw");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
