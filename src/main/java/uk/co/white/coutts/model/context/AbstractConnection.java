package uk.co.white.coutts.model.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractConnection
{
	private static final String USERNAME = "root",
								PASSWORD = "",
							 CONN_STRING = "jdbc:mysql://localhost/home_web";

	protected Connection conn;
	protected PreparedStatement preparedStatement = null;
	protected ResultSet resultSet = null;

	public AbstractConnection() throws SQLException
	{
		conn = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
	}
	
	
}
