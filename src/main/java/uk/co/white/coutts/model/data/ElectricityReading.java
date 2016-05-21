package uk.co.white.coutts.model.data;

import java.util.Calendar;

public class ElectricityReading extends AbstractReading
{	
	public ElectricityReading()
	{
		super.type = 1;
	}
	
	public ElectricityReading( Calendar date, Float reading )
	{
		super.date = date;
		super.reading = reading;
		super.type = 1;
	}
		
	@Override
	public String toString()
	{
		return "[" + date + ", " + reading + "]";
	}
}
