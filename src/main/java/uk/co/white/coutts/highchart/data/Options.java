package uk.co.white.coutts.highchart.data;

import java.util.List;

public class Options
{
	private Title title;
	private List<Series> series;
	
	public Title getTitle()
	{
		return title;
	}

	public void setTitle( Title title )
	{
		this.title = title;
	}

	public List<Series> getSeries()
	{
		return series;
	}

	public void setSeries( List<Series> series )
	{
		this.series = series;
	}

	@Override
	public String toString()
	{
		return "[ title: " + 
				( title.getText() != null ? title.getText() : "no title" ) + 
				" series size: " + series.size() + " ]";
	}
}
