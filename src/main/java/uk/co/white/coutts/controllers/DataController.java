package uk.co.white.coutts.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import uk.co.white.coutts.model.MockData;
import uk.co.white.coutts.model.data.AbstractReading;
import uk.co.white.coutts.model.data.ElectricityReading;
import uk.co.white.coutts.model.data.Paint;

import com.vaadin.data.util.BeanContainer;

public class DataController
{
	
	public BeanContainer<String, Paint> getPaintData()
	{
		return newPaintData();
	}

	public BeanContainer<Date, AbstractReading> getElectricityData()
	{
		return newReadings();
	}

	public Map<String, List<String>> getReadingsForJs()
	{
		List<AbstractReading> readings = MockData.getMockData().newReadings();
		Map<String, List<String>> series = new HashMap<>();
		List<String> data = new LinkedList<>();
		for ( AbstractReading reading : readings )
		{
			data.add( dateNormaliser( reading ) );
		}
		series.put( readings.get( 0 ).getYear(), data );
		
		readings = MockData.getMockData().newReadings();
		List<String> y2Data = new LinkedList<>();
		for ( AbstractReading reading : readings )
		{
			reading.getDate().add( Calendar.YEAR, 1 );
			y2Data.add( dateNormaliser( reading ) );
		}
		series.put( readings.get( 0 ).getYear(), y2Data );
		return series;
	}

	private String dateNormaliser( AbstractReading input )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( input.getDate().getTimeInMillis() );
		cal.set( Calendar.YEAR, 2000 );
		return "[Date.UTC("
				+ cal.get( Calendar.YEAR )
				+ ","
				+ cal.get( Calendar.MONTH )
				+ ","
				+ cal.get( Calendar.DAY_OF_MONTH )
				+ "),"
				+ input.getReading()
				+ "]";
	}
	
	private BeanContainer<String, Paint> newPaintData()
	{
		BeanContainer<String, Paint> container = new BeanContainer<>( Paint.class );
		container.setBeanIdProperty( "room" );
		container.addBean( new Paint( "Kitchen", "#jhk3rew" ) );
		container.addBean( new Paint( "Hall", "#jhk3rew" ) );
		container.addBean( new Paint( "Livingroom", "#jhk3rew" ) );
		container.addBean( new Paint( "Master bedroom", "#jhk3rew" ) );
		container.addBean( new Paint( "Second bedroom", "#jhk3rew" ) );
		container.addBean( new Paint( "Archie's room", "#jhk3rew" ) );
		container.addBean( new Paint( "Bathroom", "#jhk3rew" ) );
		container.addBean( new Paint( "Kitchen", "#jhk3rew" ) );
		return container;
	}
	
	private BeanContainer<Date, AbstractReading> newReadings()
	{
		BeanContainer<Date, AbstractReading> container = new BeanContainer<>( AbstractReading.class );
		container.setBeanIdProperty( "date" );
		List<AbstractReading> list = MockData.getMockData().newReadings();
		for( AbstractReading r : list )
		{
			container.addBean( (AbstractReading) r );
		}
		return container;
	}
	
}
