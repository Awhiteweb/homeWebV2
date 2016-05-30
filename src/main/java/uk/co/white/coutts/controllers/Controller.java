package uk.co.white.coutts.controllers;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alex on 30/05/2016.
 */
public interface Controller<T>
{
    public List<T> getAll() throws SQLException;
    public T getId( int id ) throws SQLException;
    public List<T> getName( String name ) throws SQLException;
    public void addItem( T item ) throws SQLException;
}
