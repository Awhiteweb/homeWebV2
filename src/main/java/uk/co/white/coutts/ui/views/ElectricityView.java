package uk.co.white.coutts.ui.views;

import java.util.Date;

import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Grid.SelectionModel;
import com.vaadin.ui.Grid.SingleSelectionModel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import uk.co.white.coutts.controllers.DataController;
import uk.co.white.coutts.model.data.ElectricityReading;

public class ElectricityView extends CssLayout
{
	private static final long serialVersionUID = 8308545397180135786L;
	private String title;
	private Grid dataTable;
	private DataController controller;
	private BeanContainer<Date, ElectricityReading> container;
	// high charts graph
	
	public ElectricityView()
	{
		setStyleName( "electricity" );
		setSizeFull();
		Date today = new Date();
		title = String.format( "Electricity data up to today: %s", today.toString() );
		controller = new DataController();
	}
	
	public ElectricityView loadView()
	{
		Label header = new Label( title );
		header.setStyleName( "page-heading" );
		addComponent( header );
		
		createGrid();
		addComponent( dataTable );
		addComponent( addButton() );
		
		return this;
	}
	
	private void createGrid()
	{
		container = controller.getElectricityData();
		dataTable = new Grid( container );
		dataTable.setSelectionMode( SelectionMode.SINGLE );
		dataTable.addSelectionListener(selectionEvent -> {
		    Object selected = ( (SingleSelectionModel)
		        dataTable.getSelectionModel() ).getSelectedRow();

		    if ( selected != null )
		        Notification.show( "Selected " +
		        		dataTable.getContainerDataSource().getItem( selected )
		                .getItemProperty( "reading" ) );
		    else
		        Notification.show( "Nothing selected" );
		});
		dataTable.setColumnOrder( "date", "reading" );
		dataTable.setStyleName( "data-grid" );
	}
	
	private Button addButton()
	{
		Button add = new Button( "Add new reading" );
		add.setStyleName( "add-btn" );
		add.addClickListener( e1 -> {
			Window w = new Window();
			w.setModal( true );
			
			VerticalLayout v = new VerticalLayout();
			MarginInfo mi = new MarginInfo( 50 );
			mi.setMargins( true );
			v.setMargin( mi );
			v.setSpacing( true );
			
			DateField dateField = new DateField();
			dateField.setValue( new Date() );
			v.addComponent( dateField );
			
			TextField reading = new TextField();
			reading.setCaption( "Reading:" );
			reading.setConverter( Float.class );
			reading.setValue( "0" );
			reading.addValidator( new NumberValidator( "" ) );
			v.addComponent( reading );
			
			Button submit = new Button( "submit" );
			submit.addClickListener( e2 -> {
				String value = reading.getValue();
				try
				{
					Float number = (Float) reading.getConvertedValue();
					ElectricityReading er = new ElectricityReading();
					er.setReading( number );
					er.setDate( dateField.getValue() );
					container.addBean( er );
					w.close();
				}
				catch( ConversionException ex )
				{
					Notification.show( "could not convert value" );
				}
			});
			v.addComponent( submit );
			
			Button close = new Button( "close" );
			close.addClickListener( e3 -> w.close() );
			v.addComponent( close );
			w.setContent( v );
			
			UI.getCurrent().addWindow( w );
		});
		
		return add;
	}
	
	class NumberValidator implements Validator
	{
		private static final long serialVersionUID = -5148814912133241135L;

		private String errorMessage;
		
		public NumberValidator( String errorMessage )
		{
			this.errorMessage = errorMessage;
		}
		
		@Override
		public void validate( Object value ) throws InvalidValueException
		{
			if ( value == null )
				throw new Validator.InvalidValueException( "Please enter a number" );
			try
			{
				Float number = (Float) value;
				System.out.println( number.floatValue() );
			}
			catch( Exception e )
			{
				throw new Validator.InvalidValueException( "Please enter a number" );
			}
		}
	}
}
