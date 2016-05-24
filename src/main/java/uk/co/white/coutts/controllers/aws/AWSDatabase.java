package uk.co.white.coutts.controllers.aws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class AWSDatabase
{
	public Connection getConnection()
	{
		try
		{
			String name = "cw_home_master";
			String key = "masterPass16";
					
			String url = "jdbc:mysql://cw-home-web-db.ct0qikphk9or.eu-west-1.rds.amazonaws.com:3306/";
			String dbName = "homewebdb";
			String driver = "com.mysql.cj.jdbc.Driver";
		
			Class.forName( driver );

			return DriverManager.getConnection( url + dbName, name, key );
		}
		catch (ClassNotFoundException ce)
		{
			ce.printStackTrace();
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		return null;
	}
	
	public void getDataSource()
	{
		DataSource ds = new MysqlDataSource();
	}
}
