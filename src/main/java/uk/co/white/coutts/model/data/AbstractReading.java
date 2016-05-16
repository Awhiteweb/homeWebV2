package uk.co.white.coutts.model.data;

import java.util.Calendar;
import java.util.Date;

public abstract class AbstractReading
{
	protected Date date;
	protected Float reading;
		
	public Date getDate()
	{
		return date;
	}
	public void setDate( Date date )
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
	public String getJsString()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		return "[Date.UTC("
				+ cal.get( Calendar.YEAR )
				+ ","
				+ cal.get( Calendar.MONTH )
				+ ","
				+ cal.get( Calendar.DAY_OF_MONTH )
				+ "),"
				+ reading
				+ "]";
	}

}
