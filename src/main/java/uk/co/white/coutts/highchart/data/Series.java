package uk.co.white.coutts.highchart.data;

import java.util.List;

public class Series
{
	private String name;
	private List<Float> data;
	
	public String getName()
	{
		return name;
	}
	public void setName( String name )
	{
		this.name = name;
	}
	public List<Float> getData()
	{
		return data;
	}
	public void setData( List<Float> data )
	{
		this.data = data;
	}
	@Override
	public String toString()
	{
		String d = data.toArray( new Float[ data.size() ] ).toString();
		return "[ name: " + name + ", data: " + d + " ]";
	}	
}