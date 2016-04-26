package uk.co.white.coutts.ui.views;

import uk.co.white.coutts.controllers.DataController;
import uk.co.white.coutts.model.data.Paint;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Grid.SingleSelectionModel;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class PaintView extends CssLayout
{
	private static final long serialVersionUID = 8615033040957756021L;
	private Grid dataTable;
	private BeanContainer<String, Paint> container;
	private DataController controller;
	private Paint selectedItem;
	private Button addEdit;
	private String addPaint = "Add Paint",
				  editPaint = "Edit Paint";

	public PaintView()
	{
		controller = new DataController();
		setStyleName( "paint-view" );
		setSizeFull();
	}
	
	
	public PaintView loadView()
	{
		createButton();
		createGrid();
		addComponents( dataTable, addEdit );
		return this;
	}
	
	private void createGrid()
	{
		container = controller.getPaintData();
		dataTable = new Grid( container );
		dataTable.setSelectionMode( SelectionMode.SINGLE );
		dataTable.addSelectionListener( selectionEvent -> {
		    Object selected = ( ( SingleSelectionModel )
		        dataTable.getSelectionModel() ).getSelectedRow();
		    if ( selected != null )
		    {
		    	addEdit.setCaption( editPaint );
		    	selectedItem = container.getItem( selected ).getBean();
		    }
		    else
		    {
		    	addEdit.setCaption( addPaint );
		    	selectedItem = null;
		    }
		});
		dataTable.setColumnOrder( "room", "code" );
		dataTable.setStyleName( "data-grid" );
		dataTable.setSizeUndefined();
	}
	
	private void createButton()
	{
		addEdit = new Button();
		addEdit.setCaption( addPaint );
		addEdit.addClickListener( e -> {
			Window w = new Window();
			w.setResizable( false );
			w.setClosable( false );
			w.center();
			w.setModal( true );

			VerticalLayout v = new VerticalLayout();
			v.setSpacing( true );
			v.setMargin( true );
			MarginInfo marginInfo = new MarginInfo( 50 );
			marginInfo.setMargins( true );
			v.setMargin( marginInfo );

			HorizontalLayout h = new HorizontalLayout();

			TextField r = new TextField( "Room" );
			TextField c = new TextField( "Colour code" );
			
			if( selectedItem != null )
			{
				r.setValue( selectedItem.getRoom() );
				c.setValue( selectedItem.getCode() );
			}

			Button cancel = new Button( "Cancel" );
			cancel.addClickListener( ei -> w.close() );
			Button b = new Button( "Save" );
			b.addClickListener( ce -> {
				if( selectedItem != null )
				{
					container.removeItem( selectedItem );
					selectedItem.setRoom( r.getValue() );
					selectedItem.setCode( c.getValue() );
					container.addBean( selectedItem );
					dataTable.select( null );
				}
				else
				{
					container.addBean( new Paint( r.getValue(), c.getValue() ) );
				}
				w.close();
			});
			h.addComponents( b, cancel );
			v.addComponents( r, c, h );
			w.setContent( v );
			UI.getCurrent().addWindow( w );
		});
	}
	
}