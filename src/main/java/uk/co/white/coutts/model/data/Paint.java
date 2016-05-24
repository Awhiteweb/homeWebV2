package uk.co.white.coutts.model.data;

import java.beans.PropertyChangeEvent;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import uk.co.white.coutts.model.EntityChangeListener;

@DatabaseTable( tableName = "paints" )
public class Paint
{
	@DatabaseField( id = true )
	private int id;
	@DatabaseField
	private String room;
	@DatabaseField
	private String code;
	@DatabaseField( persisted = false )
	private EntityChangeListener listener;
	
	public Paint()
	{
		listener = new EntityChangeListener();
	}
	
	public Paint( String room, String code )
	{
		this();
		this.room = room;
		this.code = code;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId( int id )
	{
		this.id = id;
	}
	
	public String getRoom()
	{
		return room;
	}
	public void setRoom( String room )
	{
		if( this.room != null && !this.room.equals( room ) )
			listener.propertyChange( new PropertyChangeEvent( this, "room", this.room, room ) );;
		this.room = room;
	}
	
	public String getCode()
	{
		return code;
	}
	public void setCode( String code )
	{
		if( this.code != null && !this.code.equals( code ) )
			listener.propertyChange( new PropertyChangeEvent( this, "code", this.code, code ) );;
		this.code = code;
	}

	@Override
	public String toString()
	{
		return "[ " + room + ", " + code + " ]";
	}
}


