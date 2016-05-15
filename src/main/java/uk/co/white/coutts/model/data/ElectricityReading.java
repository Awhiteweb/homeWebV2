package uk.co.white.coutts.model.data;

import java.util.Date;

public class ElectricityReading extends AbstractReading
{	
	public ElectricityReading(){}
	
	public ElectricityReading( Date date, Float reading )
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
