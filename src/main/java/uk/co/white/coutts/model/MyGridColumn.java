package uk.co.white.coutts.model;

import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;

public class MyGridColumn extends CssLayout
{
	private static final long serialVersionUID = 8586650594483447636L;
	private String style;
	private Component title;
	
	public MyGridColumn(){}
	public MyGridColumn( String style, Component label )
	{
		this.style = style;
		this.title = label;
		setStyleName( style );
	}
	
	public String getStyle()
	{
		return style;
	}
	public void setStyle( String style )
	{
		this.style = style;
	}
	public Component getTitle()
	{
		return title;
	}
	public void setTitle( Component title )
	{
		this.title = title;
	}
	
}
