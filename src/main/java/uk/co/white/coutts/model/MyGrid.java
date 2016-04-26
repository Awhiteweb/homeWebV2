package uk.co.white.coutts.model;

import com.vaadin.ui.CssLayout;

public class MyGrid extends CssLayout
{
	private static final long serialVersionUID = 7829025919724436664L;
	
	private String title;
	private MyGridRow row;
	
	public MyGrid(){}
	public MyGrid( String title )
	{
		this.setTitle( title );
	}

	public String getTitle()
	{
		return title;
	}
	public void setTitle( String title )
	{
		this.title = title;
	}
	public MyGridRow getRow()
	{
		return row;
	}
	public void addRow( MyGridRow row )
	{
		this.row = row;
	}
	
}
