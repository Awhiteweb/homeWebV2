package uk.co.white.coutts.model;

public enum MenuType
{
	PAINT ( "Paints", "Room Colours" ),
	TODO ( "Todos", "To Do Lists" ),
	ELECTRICITY ( "Electricity", "Electricity" ),
	GAS ( "Gas", "Gas" ),
	CALENDAR ( "Calendar", "Calendar" );
	
	private String name, label;
	
	private MenuType( String name, String label )
	{
		this.name = name;
		this.label = label;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
