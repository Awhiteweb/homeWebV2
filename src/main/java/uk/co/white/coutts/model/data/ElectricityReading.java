package uk.co.white.coutts.model.data;

import java.util.Date;

public class ElectricityReading
{
	private Date date;
	private Float reading;
	
	public ElectricityReading(){}
	
	public ElectricityReading( Date date, Float reading )
	{
		this.date = date;
		this.reading = reading;
	}
	
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
	
	@Override
	public String toString()
	{
		return "[" + date + ", " + reading + "]";
	}
}
