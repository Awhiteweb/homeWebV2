package uk.co.white.coutts.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;

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
		try
		{
			return newReadings();
		}
		catch ( ParseException e )
		{
			return null;
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
	
	private BeanContainer<Date, ElectricityReading> newReadings() throws ParseException
	{
		Set<ElectricityReading> set = new TreeSet<>( (o1, o2) -> o1.getDate().compareTo( o2.getDate() ) );
		ElectricityReading r;
		SimpleDateFormat sdt = new SimpleDateFormat( "yyyy-M-dd" );
		Random rand = new Random();
		Float f = 1000f + ( rand.nextFloat() * 100 );
		BeanContainer<Date, ElectricityReading> container = new BeanContainer<>( ElectricityReading.class );
		container.setBeanIdProperty( "date" );
		for ( int i = 1; i < 13; i++ )
		{
			Date d = sdt.parse( String.format( "2015-%02d-01", i ) );
			f += ( rand.nextFloat() * 1000 );
			r = new ElectricityReading();
			r.setDate( d );
			r.setReading( f );
//			System.out.println( r.toString() );
			set.add( r );
			container.addBean( r );
		}
		return container;
	}
	
}
