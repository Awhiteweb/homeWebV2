package uk.co.white.coutts.model.data;

import java.util.Calendar;

public abstract class AbstractReading
{
	protected Calendar date;
	protected Float reading;
		
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
