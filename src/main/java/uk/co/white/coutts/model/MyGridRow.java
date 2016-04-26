package uk.co.white.coutts.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.vaadin.ui.CssLayout;

public class MyGridRow extends CssLayout
{
	private static final long serialVersionUID = -3474201601358535215L;
	private String style;
	private List<MyGridColumn> columns;
	
	public MyGridRow()
	{
		columns = new LinkedList<>();
	}
	public MyGridRow( String style )
	{
		this();
		this.style = style;
		setStyleName( style );
	}
	public MyGridRow( String style, MyGridColumn... columns )
	{
		this( style );
		for ( MyGridColumn col : columns )
		{
			this.columns.add( col );
		}
	}
	public MyGridRow( MyGridColumn... columns )
	{
		this();
		for ( MyGridColumn col : columns )
		{
			this.columns.add( col );
		}
	}

	
	public String getStyle()
	{
		return style;
	}
	public void setStyle( String style )
	{
		this.style = style;
		setStyleName( style );
	}
	public void addStyle( String style )
	{
		this.style += " " + style;
		addStyleName( style );
	}
	public List<MyGridColumn> getColumns()
	{
		return columns;
	}
	public void setColumns( List<MyGridColumn> columns )
	{
		this.columns = columns;
	}
	public void addColumn( MyGridColumn column )
	{
		this.columns.add( column );
	}
	public void addColumns( MyGridColumn... columns )
	{
		this.columns.addAll( Arrays.asList( columns ) );
	}
	
	
}
