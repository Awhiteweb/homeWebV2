package uk.co.white.coutts.model.data;

import java.util.Calendar;

public class ElectricityReading extends AbstractReading
{	
	public ElectricityReading(){}
	
	public ElectricityReading( Calendar date, Float reading )
	{
		super.date = date;
		super.reading = reading;
	}
		
	@Override
	public String toString()
	{
		return "[" + date + ", " + reading + "]";
	}
}
