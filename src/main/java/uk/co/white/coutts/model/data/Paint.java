package uk.co.white.coutts.model.data;

import java.beans.PropertyChangeEvent;

import uk.co.white.coutts.model.EntityChangeListener;

public class Paint
{
	private int id;
	private String room;
	private String code;
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

