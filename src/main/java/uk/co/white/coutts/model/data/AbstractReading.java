package uk.co.white.coutts.model.data;

import java.util.Calendar;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable( tableName = "readings" )
public abstract class AbstractReading
{
	@DatabaseField( id = true )
	protected int id;
	@DatabaseField( columnName = "day" )
	protected Calendar date;
	@DatabaseField
	protected Float reading;
	@DatabaseField
	protected int type;
		
	public int getId()
	{
		return id;
	}
	public void setId( int id )
	{
		this.id = id;
	}
	public Calendar getDate()
	{
		return date;
	}
	public void setDate( Calendar date )
	{
		this.date = date;
	}
	public Float getReading()
	{
		return reading;
	}
	public void setReading( Float reading )
	{
		this.reading = reading;
	}
	public int getType()
	{
		return type;
	}
	public void setType( int type )
	{
		this.type = type;
	}
	public String getYear()
	{
		return String.format( "%d", date.get( Calendar.YEAR ) );
	}
	public String getJsString()
	{
		return "[Date.UTC("
				+ date.get( Calendar.YEAR )
				+ ","
				+ date.get( Calendar.MONTH )
				+ ","
				+ date.get( Calendar.DAY_OF_MONTH )
				+ "),"
				+ reading
				+ "]";
	}

}
