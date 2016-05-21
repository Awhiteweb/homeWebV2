package uk.co.white.coutts.model.context;

import java.sql.SQLException;
import java.util.List;

public interface Context<T>
{
	public List<T> getAll() throws SQLException;
	public T getById( int id ) throws SQLException;
	public T getByName( String name ) throws SQLException;
	public void insert( T item ) throws SQLException;
}
