package uk.co.white.coutts.controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import uk.co.white.coutts.model.data.Paint;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alex on 30/05/2016.
 */
public class PaintController implements Controller<Paint>
{
    private static PaintController controller;

    public static PaintController getController() throws SQLException
    {
        if( controller == null )
            controller = new PaintController();
        return controller;
    }

    private String dbString = "";
    private Dao<Paint, String> paintDao;

    public PaintController() throws SQLException
    {
        ConnectionSource connectionSource = new JdbcConnectionSource( dbString );
        paintDao = DaoManager.createDao( connectionSource, Paint.class );
        try
        {
            paintDao.queryForAll();
        }
        catch( SQLException e )
        {
            TableUtils.createTable( connectionSource, Paint.class );
        }
    }

    @Override
    public List<Paint> getAll() throws SQLException
    {
        return paintDao.queryForAll();
    }

    @Override
    public Paint getId( int id ) throws SQLException
    {
        return paintDao.queryForId( String.format( "%d", id ) );
    }

    @Override
    public List<Paint> getName( String name ) throws SQLException
    {
        return paintDao.queryForEq( "room", name );
    }

    @Override
    public void addItem( Paint item ) throws SQLException
    {
        paintDao.create( item );
    }

}
