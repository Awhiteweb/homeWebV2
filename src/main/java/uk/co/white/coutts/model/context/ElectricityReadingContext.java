package uk.co.white.coutts.model.context;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import uk.co.white.coutts.model.data.AbstractReading;
import uk.co.white.coutts.model.data.ElectricityReading;

public class ElectricityReadingContext extends AbstractConnection implements ReadingContext
{

	private String query = "SELECT * FROM readings "
			+ "JOIN readings_type ON readings.`type` = readings_type.id "
			+ "WHERE readings_type.id = 1";
	
	public ElectricityReadingContext() throws SQLException
	{
		super();
	}

	@Override
	public List<AbstractReading> getAll() throws SQLException
	{
		preparedStatement = conn.prepareStatement( query );
		resultSet = preparedStatement.executeQuery();
		return stripResults();
	}

	@Override
	public AbstractReading getById( int id ) throws SQLException
	{
		query += "AND id = ?";
		preparedStatement = conn.prepareStatement( query );
		resultSet = preparedStatement.executeQuery();
		return stripResults().get( 0 );
	}

	@Override
	public AbstractReading getByName( String name ) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AbstractReading> getDateRange( Calendar start, Calendar end ) throws SQLException
	{
		query += "AND `day` BETWEEN ? AND ?";
		preparedStatement = conn.prepareStatement( query );
		preparedStatement.setTimestamp( 1, new Timestamp( start.getTimeInMillis() ) );
		preparedStatement.setTimestamp( 2, new Timestamp( end.getTimeInMillis() ) );
		resultSet = preparedStatement.executeQuery();
		return stripResults();
	}
	
	private List<AbstractReading> stripResults() throws SQLException
	{
		List<AbstractReading> gas = new LinkedList<>();
		while ( resultSet.next() )
		{
			AbstractReading g = new ElectricityReading();
			g.setId( resultSet.getInt( "id" ) );
			g.setReading( resultSet.getFloat( "reading" ) );
			Calendar c = Calendar.getInstance();
			c.setTime( resultSet.getDate( "day" ) );
			g.setDate( c );
			gas.add( g );
		}
		return gas;
	}

	@Override
	public void insert( AbstractReading item ) throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

}
