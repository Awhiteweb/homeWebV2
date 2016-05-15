package uk.co.white.coutts.ui.views;

import java.util.Date;
import java.util.Locale;

import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Grid.SingleSelectionModel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import uk.co.white.coutts.controllers.DataController;
import uk.co.white.coutts.highchart.HighChart;
import uk.co.white.coutts.model.data.ElectricityReading;

public class ElectricityView extends CssLayout
{
	private static final long serialVersionUID = 8308545397180135786L;
	private Grid dataTable;
	private DataController controller;
	private BeanContainer<Date, ElectricityReading> container;
	// high charts graph
	
	public ElectricityView()
	{
		setStyleName( "electricity" );
//		setSizeFull();
		setSizeUndefined();
		setWidth( "100%" );
		controller = new DataController();
	}
	
	public ElectricityView loadView()
	{
		createGrid();
		addComponent( dataTable );
		addComponent( addButton() );
		addComponent( addSpacer() );
		addComponent( addChart() );
		return this;
	}
	
	private void createGrid()
	{
		container = controller.getElectricityData();
		dataTable = new Grid( container );
		int rows = container.size() > 10 ? 10 : container.size();
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
		dataTable.setHeightMode( HeightMode.ROW );
		dataTable.setHeightByRows( rows );
	}
	
	private Button addButton()
	{
		Button add = new Button( "Add new reading" );
		add.setStyleName( "add-btn" );
		add.addClickListener( e1 -> {
			Window w = new Window();
			w.setModal( true );
			w.setResizable( false );
			w.setClosable( false );
			
			VerticalLayout v = new VerticalLayout();
			v.setSpacing( true );
			
			DateField dateField = new DateField();
			dateField.setLocale( Locale.UK );
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
	
	private Label addSpacer()
	{
		Label label = new Label( "<p>&nbsp;<p>" );
		label.setCaptionAsHtml( true );
		label.addStyleName( "clearer" );
		return label;
	}
	
	private HighChart addChart()
	{
		HighChart hc = new HighChart();
		hc.setHcjs( hc.printJSFile( controller.getReadingsForJs() ) );
		hc.setWidth( "500px" );
		hc.setHeight( "350px" );
		return hc;
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
