package uk.co.white.coutts.model;

import java.util.Calendar;

import com.vaadin.ui.Grid.AbstractRenderer;

import elemental.json.JsonValue;

public class CalendarRenderer extends AbstractRenderer<Calendar>
{
	private static final long serialVersionUID = -2951308310957773008L;
	private String formatString;
	
	public CalendarRenderer()
	{
		super( Calendar.class, "Null Value" );
	}
	
	public CalendarRenderer( String formatString ) 
			throws IllegalArgumentException
	{
		this();
		if (formatString == null) {
            throw new IllegalArgumentException("format string may not be null");
        }
		this.formatString = formatString;
	}
	
	public CalendarRenderer( String formatString, String nullRepresentation ) 
			throws IllegalArgumentException
	{
		super( Calendar.class, nullRepresentation );
		if (formatString == null) {
            throw new IllegalArgumentException("format string may not be null");
        }
		this.formatString = formatString;
	}
	
	@Override
	protected String getNullRepresentation ()
	{
		return super.getNullRepresentation();
	}

	@Override
	public JsonValue encode ( Calendar value )
	{
		final String dateString;
        if (value == null)
            dateString = getNullRepresentation();
        else
        	dateString = String.format( formatString, 
        			value );
		return encode( dateString, String.class );
	}

	@Override
	public String toString ()
	{
		return String.format( "%s [%s]", getClass().getSimpleName(), formatString );
	}

	

}
