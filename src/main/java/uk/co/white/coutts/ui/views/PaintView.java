package uk.co.white.coutts.ui.views;

import java.util.LinkedList;
import java.util.List;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Grid.SingleSelectionModel;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import uk.co.white.coutts.controllers.DataController;
import uk.co.white.coutts.model.MyGrid;
import uk.co.white.coutts.model.data.Paint;

public class PaintView extends CssLayout
{
	private static final long serialVersionUID = 8615033040957756021L;
	private MyGrid grid;
	private Grid dataTable;
	private BeanContainer<String, Paint> container;
	private DataController controller;

	public PaintView()
	{
		controller = new DataController();
		setStyleName( "paint-view" );
		setSizeFull();
		createGrid();
		addComponent( dataTable );
		addComponent( addButton() );
	}
	
	private void createGrid()
	{
		container = controller.getPaintData();
		dataTable = new Grid( container );
		int rows = container.size() > 10 ? 10 : container.size();
		dataTable.setSelectionMode( SelectionMode.SINGLE );
		dataTable.addSelectionListener(selectionEvent -> {
		    Object selected = ( (SingleSelectionModel)
		        dataTable.getSelectionModel() ).getSelectedRow();

		    if ( selected != null )
		        Notification.show( "Selected " +
		        		dataTable.getContainerDataSource().getItem( selected )
		                .getItemProperty( "room" ) );
		    else
		        Notification.show( "Nothing selected" );
		});
		dataTable.setColumnOrder( "room", "code" );
		dataTable.setStyleName( "data-grid" );
		dataTable.setHeightMode( HeightMode.ROW );
		dataTable.setHeightByRows( rows );
		dataTable.setEditorEnabled( true );
	}
	
	private Button addButton()
	{
		Button btn = new Button( "Add" );
		btn.addClickListener( e -> {
			List<Label> labels = new LinkedList<>();
			for( String id : container.getItemIds() )
				labels.add( new Label( container.getItem( id ).getBean().toString() ) );

			Window w = new Window();
			VerticalLayout v = new VerticalLayout();
			v.setMargin(  true );
			v.setMargin( new MarginInfo( true ) );
			v.setSpacing( true );
			for( Label l : labels )
				v.addComponent( l );
			w.setContent( v );
			UI.getCurrent().addWindow( w );
		});
		return btn;
	}
	
}
