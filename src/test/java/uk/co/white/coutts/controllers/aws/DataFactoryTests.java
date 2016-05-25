package uk.co.white.coutts.controllers.aws;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

public class DataFactoryTests
{
	private DataSource ds;
	private Connection conn;
	
	@Before
	public void getFactoryInstance()
	{
		ds = DatabaseFactory.getMysqlDataSource();
	}
	
	@After
	public void closeConnection()
	{
		try
		{
			conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void canConnectToDB() throws SQLException
	{
		try
		{
			conn = ds.getConnection();
			assertNotNull( conn );
		}
		catch( SQLException e )
		{
			fail( "database connection failed" );
		}
	}
}
