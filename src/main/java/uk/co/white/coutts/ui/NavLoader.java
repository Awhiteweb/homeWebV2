package uk.co.white.coutts.ui;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;

import uk.co.white.coutts.model.MenuType;
import uk.co.white.coutts.ui.views.ElectricityView;
import uk.co.white.coutts.ui.views.PaintView;
import uk.co.white.coutts.ui.views.GasView;

public class NavLoader extends CssLayout
{
	private static final long serialVersionUID = 2129354732348214658L;
	private Button paintButton;
	private Button todoButton;
	private Button electricityButton;
	private Button gasButton;
	private Button calendarButton;
	private MainContent content;
	
	public NavLoader()
	{
		setStyleName( "nav-bar" );
		setHeight( "100%" );
	}
	
	public NavLoader( MainContent content )
	{
		this();
		this.content = content;
	}
	
	public NavLoader loadMenuButtons()
	{
		createMenuButtons();
		addComponents( 
				paintButton, 
				todoButton, 
				electricityButton, 
				gasButton, 
				calendarButton );
		return this;
	}
	
	public void resetButtonStyles()
	{
		paintButton.setStyleName( "" );
		todoButton.setStyleName( "" );
		electricityButton.setStyleName( "" );
		gasButton.setStyleName( "" );
		calendarButton.setStyleName( "" );
	}
		
	private void createMenuButtons()
	{
		paintButton = new Button( MenuType.PAINT.getLabel() );
		todoButton = new Button( MenuType.TODO.getLabel() );
		electricityButton = new Button( MenuType.ELECTRICITY.getLabel() );
		gasButton = new Button( MenuType.GAS.getLabel() );
		calendarButton = new Button( MenuType.CALENDAR.getLabel() );
		
		paintButton.addClickListener( e -> {
			// set main content to paint view
			resetButtonStyles();
			paintButton.addStyleName( "active" );
			content.loadView( new PaintView().loadView() );
		});
		
		todoButton.addClickListener( e -> {
			// set main content to paint view
			resetButtonStyles();
			todoButton.addStyleName( "active" );
			content.loadView( new PaintView() );
		});
		
		electricityButton.addClickListener( e -> {
			// set main content to paint view
			resetButtonStyles();
			electricityButton.addStyleName( "active" );
			content.loadView( new ElectricityView().loadView() );
		});
		
		gasButton.addClickListener( e -> {
			// set main content to paint view
			resetButtonStyles();
			gasButton.addStyleName( "active" );
			content.loadView( new GasView().loadView() );
		});
		
		calendarButton.addClickListener( e -> {
			// set main content to paint view
			resetButtonStyles();
			calendarButton.addStyleName( "active" );
			content.loadView( new PaintView() );
		});
	}
	
	public Map<MenuType, Button> getButtons()
	{
		Map<MenuType, Button> buttonMap = new HashMap<>();
		buttonMap.put( MenuType.PAINT, paintButton );
		buttonMap.put( MenuType.TODO, todoButton );
		buttonMap.put( MenuType.ELECTRICITY, electricityButton );
		buttonMap.put( MenuType.GAS, gasButton );
		buttonMap.put( MenuType.CALENDAR, calendarButton );
		return buttonMap;
	}
	
	
}
