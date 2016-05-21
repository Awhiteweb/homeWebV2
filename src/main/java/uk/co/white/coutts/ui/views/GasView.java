package uk.co.white.coutts.ui.views;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import uk.co.white.coutts.controllers.DataController;
import uk.co.white.coutts.highchart.HighChart;
import uk.co.white.coutts.model.CalendarRenderer;
import uk.co.white.coutts.model.data.AbstractReading;
import uk.co.white.coutts.model.data.ElectricityReading;

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
/**
 * Polar chart not loading
 * @author Alex
 *
 */
public class GasView extends CssLayout
{
	private static final long serialVersionUID = 2147862163429543466L;
	private DataController controller;
	private BeanContainer<Date, AbstractReading> container;
	private Grid dataTable;

	public GasView()
	{
		setStyleName( "gas" );
		setSizeUndefined();
		setWidth( "100%" );
		controller = new DataController();
	}
	
	public GasView loadView()
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
		dataTable.getColumn( "date" ).setRenderer( new CalendarRenderer( "%1$td %1$tb %1$tY" ) );
		dataTable.removeColumn( "id" );
		dataTable.removeColumn( "jsString" );
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
			reading.addValidator( value -> {
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
			});
			v.addComponent( reading );
			
			Button submit = new Button( "submit" );
			submit.addClickListener( e2 -> {
				try
				{
					Float number = (Float) reading.getConvertedValue();
					ElectricityReading er = new ElectricityReading();
					er.setReading( number );
					Calendar cal = Calendar.getInstance();
					cal.setTime( dateField.getValue() );
					er.setDate( cal );
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
		Label label = new Label();
		label.addStyleName( "clearer" );
		return label;
	}
	
	private HighChart addChart()
	{
		HighChart hc = new HighChart();
		hc.setHcjs( hc.getPolarChart( "Gas Readings", controller.getReadingsForJs() ) );
		hc.setWidth( "500px" );
		hc.setHeight( "500px" );
		return hc;
	}
	
}
