package uk.co.white.coutts.model.context;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import uk.co.white.coutts.model.data.Paint;

public class PaintContext extends AbstractConnection implements Context<Paint>
{
	public PaintContext() throws SQLException
	{
		super();
	}

	@Override
	public List<Paint> getAll() throws SQLException
	{
		preparedStatement = conn.prepareStatement( "SELECT * FROM paints" );
		resultSet = preparedStatement.executeQuery();
		return stripResults();
	}

	@Override
	public Paint getById( int id ) throws SQLException
	{
		preparedStatement = conn.prepareStatement( "SELECT * FROM paints WHERE id = ?" );
		preparedStatement.setInt( 1, id );
		resultSet = preparedStatement.executeQuery();
		return stripResults().get( 0 );
	}

	@Override
	public Paint getByName( String name ) throws SQLException
	{
		preparedStatement = conn.prepareStatement( "SELECT * FROM paints WHERE room = ?" );
		preparedStatement.setString( 1, name );
		resultSet = preparedStatement.executeQuery();
		return stripResults().get( 0 );
	}

	private List<Paint> stripResults() throws SQLException
	{
		List<Paint> paints = new LinkedList<>();
		while( resultSet.next() )
		{
			Paint p = new Paint();
			p.setId( resultSet.getInt( "id" ) );
			p.setRoom( resultSet.getString( "room" ) );
			p.setCode( resultSet.getString( "code" ) );
			paints.add( p );
		}
		return paints;
	}

	@Override
	public void insert( Paint item ) throws SQLException
	{
		preparedStatement = conn.prepareStatement( "INSERT INTO paints ( `room`, `code` ) VALUES ( ?, ? )" );
		preparedStatement.setString( 1, item.getRoom() );
		preparedStatement.setString( 2, item.getCode() );
		int r = preparedStatement.executeUpdate();
		if ( r < 1 )
			throw new SQLException();
		
	}
}
