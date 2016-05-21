package uk.co.white.coutts.model.data;

import java.util.Calendar;

public class GasReading extends AbstractReading
{
	public GasReading()
	{
		super.type = 2;
	}

	public GasReading( Calendar date, Float reading )
	{
		super.date = date;
		super.reading = reading;
		super.type = 2;
	}
}
