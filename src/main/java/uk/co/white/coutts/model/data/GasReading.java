package uk.co.white.coutts.model.data;

import java.util.Date;

public class GasReading extends AbstractReading
{
	public GasReading(){}

	public GasReading( Date date, Float reading )
	{
		super.date = date;
		super.reading = reading;
	}
}
