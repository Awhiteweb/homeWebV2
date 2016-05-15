package uk.co.white.coutts.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.vaadin.data.Container.PropertySetChangeEvent;
import com.vaadin.data.Item.PropertySetChangeListener;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;

import uk.co.white.coutts.model.MockData;
import uk.co.white.coutts.model.data.AbstractReading;
import uk.co.white.coutts.model.data.ElectricityReading;
import uk.co.white.coutts.model.data.Paint;

public class DataController
{
	
	public BeanContainer<String, Paint> getPaintData()
	{
		return newPaintData();
	}

	public BeanContainer<Date, ElectricityReading> getElectricityData()
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
			data.add( reading.getJsString() );
		}
		series.put( "Year 1", data );
		return series;
	}

	// possibly move to high charts class
	public void getReadingsForGraph( List<AbstractReading> readings )
	{
		List<AbstractReading> nReadings = new LinkedList<>();
		int total = readings.size();
		for( int i = 1; i < total; i++ )
		{
			int h = i - 1;
			Date d1 = readings.get( h ).getDate();
			Float r1 = readings.get( h ).getReading();
			Date d2 = readings.get( i ).getDate();
			Float r2 = readings.get( i ).getReading();
			// calculate difference in days
			
			// calculate average reading per day
			
			// calculate estimate reading for first day of month
			
			// add reading to nReadings
		}
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
	
	private BeanContainer<Date, ElectricityReading> newReadings()
	{
//		Set<ElectricityReading> set = new TreeSet<>( (o1, o2) -> o1.getDate().compareTo( o2.getDate() ) );
//		ElectricityReading r;
//		SimpleDateFormat sdt = new SimpleDateFormat( "yyyy-M-dd" );
//		Random rand = new Random();
//		Float f = 1000f + ( rand.nextFloat() * 100 );
//		BeanContainer<Date, ElectricityReading> container = new BeanContainer<>( ElectricityReading.class );
//		container.setBeanIdProperty( "date" );
//		for ( int i = 1; i < 13; i++ )
//		{
//			Date d = sdt.parse( String.format( "2015-%02d-01", i ) );
//			f += ( rand.nextFloat() * 1000 );
//			r = new ElectricityReading();
//			r.setDate( d );
//			r.setReading( f );
//			System.out.println( r.toString() );
//			set.add( r );
//			container.addBean( r );
//		}

		BeanContainer<Date, ElectricityReading> container = new BeanContainer<>( ElectricityReading.class );
		container.setBeanIdProperty( "date" );
		List<AbstractReading> list = MockData.getMockData().newReadings();
		for( AbstractReading r : list )
		{
			container.addBean( (ElectricityReading) r );
		}
		return container;
	}
	
}
