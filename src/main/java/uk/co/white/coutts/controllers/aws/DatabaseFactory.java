package uk.co.white.coutts.controllers.aws;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DatabaseFactory
{
	private static MysqlDataSource mysqlDS;

	public static DataSource getMysqlDataSource()
	{
		if( mysqlDS != null )
			return mysqlDS;
		Properties props = new Properties();
		try ( InputStream fis = DatabaseFactory.class.getResourceAsStream( "db.properties" ) )
		{
			props.load( fis );
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL( props.getProperty( "MYSQL_DB_URL" ) );
			mysqlDS.setUser( props.getProperty( "MYSQL_DB_USERNAME" ) );
			mysqlDS.setPassword( props.getProperty( "MYSQL_DB_PASSWORD" ) );
			return mysqlDS;
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		return null;
	}	
}
